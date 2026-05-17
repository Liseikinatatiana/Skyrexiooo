package api.client;

import api.config.TestConfig;
import api.dto.CreateSubscriptionRequest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class SkyrexioApiClient {
    private String authToken;

    public SkyrexioApiClient(String authToken) {
        this.authToken = authToken;
    }

    public SkyrexioApiClient() {
    }

    private RequestSpecification getBaseSpec() {
        RequestSpecification spec = given()
                .baseUri(TestConfig.getBaseUrl())
                .header("Content-Type", "application/json")
                .log().all();

        if (authToken != null) {
            spec.header("Authorization", "Bearer " + authToken);
        }

        return spec;
    }

    public Response createBotFlatSubscription(CreateSubscriptionRequest request) {
        return getBaseSpec()
                .body(request)
                .when()
                .post("/subscription/createBotFlatSubscription")
                .then()
                .log().all()
                .extract()
                .response();
    }

    public void setAuthToken(String token) {
        this.authToken = token;
    }
}
