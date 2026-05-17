package api.tests;

import api.client.SkyrexioApiClient;
import api.config.TestConfig;
import api.dto.CreateSubscriptionRequest;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class CreateSubscriptionApiTest {

    private SkyrexioApiClient apiClient;

    @BeforeClass
    public void setup() {
        apiClient = new SkyrexioApiClient(TestConfig.getExpiredToken());
    }

    // ==================== ПОЗИТИВНЫЕ ТЕСТЫ ====================

    @Test(description = "Успешное создание подписки с валидацией схемы")
    public void testCreateBotFlatSubscriptionSuccess() {
        CreateSubscriptionRequest request = new CreateSubscriptionRequest(TestConfig.getValidUserUuid(), TestConfig.getValidSourceUuid(), TestConfig.getPaymentMethod(), TestConfig.getPaymentAsset());

        Response response = apiClient.createBotFlatSubscription(request);

        response.then()
                .statusCode(200)
                .contentType("application/json")
                .body(matchesJsonSchemaInClasspath("schemas/create-subscription-success.json"))
                .body("subscriptionId", notNullValue())
                .body("status", anyOf(is("active"), is("pending")))
                .body("createdAt", notNullValue());
    }

    // ==================== НЕГАТИВНЫЕ ТЕСТЫ ====================

    @Test(description = "Другой способ оплаты (crypto) - 409 Conflict")
    public void testCreateBotFlatSubscriptionWithCryptoPayment() {
        CreateSubscriptionRequest request = new CreateSubscriptionRequest(TestConfig.getValidUserUuid(), TestConfig.getValidSourceUuid(), TestConfig.getPaymentMethodCrypto(), TestConfig.getPaymentAsset());

        Response response = apiClient.createBotFlatSubscription(request);

        response.then()
                .statusCode(409)
                .contentType("application/json")
                .body(matchesJsonSchemaInClasspath("schemas/error-response.json"))
                .body("message", containsString("already active"));
    }

    @Test(description = "Отсутствует skyrexUserUuid")
    public void testCreateBotFlatSubscriptionMissingUserUuid() {
        CreateSubscriptionRequest request = new CreateSubscriptionRequest(
                null, TestConfig.getValidSourceUuid(), TestConfig.getPaymentMethod(), TestConfig.getPaymentAsset());

        Response response = apiClient.createBotFlatSubscription(request);

        response.then()
                .statusCode(400)
                .contentType("application/json")
                .body(matchesJsonSchemaInClasspath("schemas/error-response.json"))
                .body("message", containsString("skyrexUserUuid"))
                .body("status", is(400));
    }

    @Test(description = "Отсутствует sourceUuid")
    public void testCreateBotFlatSubscriptionMissingSourceUuid() {
        CreateSubscriptionRequest request = new CreateSubscriptionRequest(TestConfig.getValidUserUuid(), null, TestConfig.getPaymentMethod(), TestConfig.getPaymentAsset());

        Response response = apiClient.createBotFlatSubscription(request);

        response.then()
                .statusCode(400)
                .contentType("application/json")
                .body(matchesJsonSchemaInClasspath("schemas/error-response.json"))
                .body("message", containsString("sourceUuid"));
    }

    @Test(description = "Неверный формат UUID")
    public void testCreateBotFlatSubscriptionInvalidUuidFormat() {
        CreateSubscriptionRequest request = new CreateSubscriptionRequest(TestConfig.getInvalidUuid(), TestConfig.getValidSourceUuid(), TestConfig.getPaymentMethod(), TestConfig.getPaymentAsset());

        Response response = apiClient.createBotFlatSubscription(request);

        response.then()
                .statusCode(400)
                .contentType("application/json")
                .body(matchesJsonSchemaInClasspath("schemas/error-response.json"))
                .body("message", containsString("UUID"));
    }

    @Test(description = "Неподдерживаемый paymentMethod")
    public void testCreateBotFlatSubscriptionUnsupportedPaymentMethod() {
        CreateSubscriptionRequest request = new CreateSubscriptionRequest(TestConfig.getValidUserUuid(), TestConfig.getValidSourceUuid(), TestConfig.getPaymentMethodUnsupported(), TestConfig.getPaymentAsset());

        Response response = apiClient.createBotFlatSubscription(request);

        response.then()
                .statusCode(400)
                .contentType("application/json")
                .body(matchesJsonSchemaInClasspath("schemas/error-response.json"))
                .body("message", containsString("paymentMethod"));
    }

    @Test(description = "Несуществующий пользователь")
    public void testCreateBotFlatSubscriptionNonExistentUser() {
        CreateSubscriptionRequest request = new CreateSubscriptionRequest(TestConfig.getNonExistentUuid(), TestConfig.getValidSourceUuid(), TestConfig.getPaymentMethod(), TestConfig.getPaymentAsset());

        Response response = apiClient.createBotFlatSubscription(request);

        response.then()
                .statusCode(404)
                .contentType("application/json")
                .body(matchesJsonSchemaInClasspath("schemas/error-response.json"))
                .body("message", containsString("not found"));
    }

    @Test(description = "Двойная подписка (уже существует)")
    public void testCreateBotFlatSubscriptionDuplicate() {
        CreateSubscriptionRequest request = new CreateSubscriptionRequest(TestConfig.getValidUserUuid(), TestConfig.getValidSourceUuid(), TestConfig.getPaymentMethod(), TestConfig.getPaymentAsset());

        // Пытаемся создать повторно
        Response response = apiClient.createBotFlatSubscription(request);

        response.then()
                .statusCode(409)
                .contentType("application/json")
                .body(matchesJsonSchemaInClasspath("schemas/error-response.json"))
                .body("message", containsString("already exists"));
    }

    @Test(description = "Без токена - 401 Unauthorized")
    public void testCreateBotFlatSubscriptionWithoutToken() {
        SkyrexioApiClient clientWithoutToken = new SkyrexioApiClient();
        CreateSubscriptionRequest request = new CreateSubscriptionRequest(TestConfig.getValidUserUuid(), TestConfig.getValidSourceUuid(), TestConfig.getPaymentMethod(), TestConfig.getPaymentAsset());

        Response response = clientWithoutToken.createBotFlatSubscription(request);

        response.then()
                .statusCode(401)
                .contentType("application/json")
                .body(matchesJsonSchemaInClasspath("schemas/error-response.json"))
                .body("error", containsString("unauthorized"));
    }

    @Test(description = "Токен истек - 401 Unauthorized")
    public void testCreateBotFlatSubscriptionWithExpiredToken() {
        SkyrexioApiClient clientWithExpiredToken = new SkyrexioApiClient(TestConfig.getExpiredToken());
        CreateSubscriptionRequest request = new CreateSubscriptionRequest(TestConfig.getValidUserUuid(), TestConfig.getValidSourceUuid(), TestConfig.getPaymentMethod(), TestConfig.getPaymentAsset());

        Response response = clientWithExpiredToken.createBotFlatSubscription(request);

        response.then()
                .statusCode(401)
                .contentType("application/json")
                .body(matchesJsonSchemaInClasspath("schemas/error-response.json"));
    }
}
