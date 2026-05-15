package api.client;

import api.dto.CreateSubscriptionRequest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class SkyrexioApiClient {
    private static final String BASE_URL = "https://test.skyrexio.com";
    private String authToken;

    public SkyrexioApiClient(String authToken) {
        this.authToken = authToken;
    }

    public SkyrexioApiClient() {
    }

    private RequestSpecification getBaseSpec() {
        RequestSpecification spec = given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json");

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
                .extract()
                .response();
    }

    public void setAuthToken(String token) {
        this.authToken = token;
    }
}
