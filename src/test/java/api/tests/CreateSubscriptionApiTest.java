package api.tests;

import api.client.SkyrexioApiClient;
import api.dto.CreateSubscriptionRequest;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class CreateSubscriptionApiTest {

    private SkyrexioApiClient apiClient;

    // Тестовые данные
    private static final String VALID_UUID = "397f9c81-7079-4838-8bfa-f3d969013778";
    private static final String VALID_SOURCE_UUID = "fb6d4b85-8f2e-4ad1-b151-e3228090b41c";
    private static final String ANOTHER_UUID = "3297715c-6a92-4bff-9f2f-9d7da2abda1d";
    private static final String NON_EXISTENT_UUID = "00000000-0000-0000-0000-000000000000";
    private static final String INVALID_UUID = "неправильный-id";
    private static final String VALID_TOKEN = "your-valid-token-here";
    private static final String EXPIRED_TOKEN = "expired-token-here";

    @BeforeClass
    public void setup() {
        apiClient = new SkyrexioApiClient(VALID_TOKEN);
    }

    // ==================== ПОЗИТИВНЫЕ ТЕСТЫ ====================

    @Test(description = "Успешное создание подписки с валидацией схемы")
    public void testCreateBotFlatSubscriptionSuccess() {
        CreateSubscriptionRequest request = new CreateSubscriptionRequest(
                VALID_UUID, VALID_SOURCE_UUID, "stripe", "USD");

        Response response = apiClient.createBotFlatSubscription(request);

        response.then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/create-subscription-success.json"))
                .body("subscriptionId", notNullValue())
                .body("status", anyOf(is("active"), is("pending")))
                .body("createdAt", notNullValue());
    }

    // ==================== НЕГАТИВНЫЕ ТЕСТЫ ====================

    @Test(description = "Другой способ оплаты (crypto) - 409 Conflict")
    public void testCreateBotFlatSubscriptionWithCryptoPayment() {
        CreateSubscriptionRequest request = new CreateSubscriptionRequest(
                VALID_UUID, VALID_SOURCE_UUID, "crypto", "USD");

        Response response = apiClient.createBotFlatSubscription(request);

        response.then()
                .statusCode(409)
                .body(matchesJsonSchemaInClasspath("schemas/error-response.json"))
                .body("message", containsString("already active"));
    }

    @Test(description = "Отсутствует skyrexUserUuid")
    public void testCreateBotFlatSubscriptionMissingUserUuid() {
        CreateSubscriptionRequest request = new CreateSubscriptionRequest(
                null, VALID_SOURCE_UUID, "stripe", "USD");

        Response response = apiClient.createBotFlatSubscription(request);

        response.then()
                .statusCode(400)
                .body(matchesJsonSchemaInClasspath("schemas/error-response.json"))
                .body("message", containsString("skyrexUserUuid"))
                .body("status", is(400));
    }

    @Test(description = "Отсутствует sourceUuid")
    public void testCreateBotFlatSubscriptionMissingSourceUuid() {
        CreateSubscriptionRequest request = new CreateSubscriptionRequest(
                VALID_UUID, null, "stripe", "USD");

        Response response = apiClient.createBotFlatSubscription(request);

        response.then()
                .statusCode(400)
                .body(matchesJsonSchemaInClasspath("schemas/error-response.json"))
                .body("message", containsString("sourceUuid"));
    }

    @Test(description = "Неверный формат UUID")
    public void testCreateBotFlatSubscriptionInvalidUuidFormat() {
        CreateSubscriptionRequest request = new CreateSubscriptionRequest(
                INVALID_UUID, VALID_SOURCE_UUID, "stripe", "USD");

        Response response = apiClient.createBotFlatSubscription(request);

        response.then()
                .statusCode(400)
                .body(matchesJsonSchemaInClasspath("schemas/error-response.json"))
                .body("message", containsString("UUID"));
    }

    @Test(description = "Неподдерживаемый paymentMethod")
    public void testCreateBotFlatSubscriptionUnsupportedPaymentMethod() {
        CreateSubscriptionRequest request = new CreateSubscriptionRequest(
                VALID_UUID, VALID_SOURCE_UUID, "paypal", "USD");

        Response response = apiClient.createBotFlatSubscription(request);

        response.then()
                .statusCode(400)
                .body(matchesJsonSchemaInClasspath("schemas/error-response.json"))
                .body("message", containsString("paymentMethod"));
    }

    @Test(description = "Несуществующий пользователь")
    public void testCreateBotFlatSubscriptionNonExistentUser() {
        CreateSubscriptionRequest request = new CreateSubscriptionRequest(
                NON_EXISTENT_UUID, VALID_SOURCE_UUID, "stripe", "USD");

        Response response = apiClient.createBotFlatSubscription(request);

        response.then()
                .statusCode(404)
                .body(matchesJsonSchemaInClasspath("schemas/error-response.json"))
                .body("message", containsString("not found"));
    }

    @Test(description = "Двойная подписка (уже существует)")
    public void testCreateBotFlatSubscriptionDuplicate() {
        CreateSubscriptionRequest request = new CreateSubscriptionRequest(
                VALID_UUID, VALID_SOURCE_UUID, "stripe", "USD");

        // Пытаемся создать повторно
        Response response = apiClient.createBotFlatSubscription(request);

        response.then()
                .statusCode(409)
                .body(matchesJsonSchemaInClasspath("schemas/error-response.json"))
                .body("message", containsString("already exists"));
    }

    @Test(description = "Без токена - 401 Unauthorized")
    public void testCreateBotFlatSubscriptionWithoutToken() {
        SkyrexioApiClient clientWithoutToken = new SkyrexioApiClient();
        CreateSubscriptionRequest request = new CreateSubscriptionRequest(
                VALID_UUID, VALID_SOURCE_UUID, "stripe", "USD");

        Response response = clientWithoutToken.createBotFlatSubscription(request);

        response.then()
                .statusCode(401)
                .body(matchesJsonSchemaInClasspath("schemas/error-response.json"))
                .body("error", containsString("unauthorized"));
    }

    @Test(description = "Токен истек - 401 Unauthorized")
    public void testCreateBotFlatSubscriptionWithExpiredToken() {
        SkyrexioApiClient clientWithExpiredToken = new SkyrexioApiClient(EXPIRED_TOKEN);
        CreateSubscriptionRequest request = new CreateSubscriptionRequest(
                VALID_UUID, VALID_SOURCE_UUID, "stripe", "USD");

        Response response = clientWithExpiredToken.createBotFlatSubscription(request);

        response.then()
                .statusCode(401)
                .body(matchesJsonSchemaInClasspath("schemas/error-response.json"));
    }
}
