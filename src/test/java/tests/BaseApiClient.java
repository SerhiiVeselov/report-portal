package tests;

import core.LoggerSingleton;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.slf4j.Logger;
import static io.restassured.RestAssured.given;

public class BaseApiClient {

    private final Logger log = LoggerSingleton.getLogger();
    protected String loginUri = "https://reportportal.epam.com/uat/sso/oauth/token";

    public String getBearerToken(String grantType, String username, String password) {
        log.info("Getting bearer token for the user");

        String basicAuthUsername = "ui";
        String basicAuthPassword = "uiman";

        Response response = given()
                .auth()
                .preemptive()
                .basic(basicAuthUsername, basicAuthPassword)
                .formParam("grant_type", grantType)
                .formParam("username", username)
                .formParam("password", password)
                .post(loginUri);

        String bearerToken = null;
        if (response.getStatusCode() == 200) {
            bearerToken = response.getBody().jsonPath().getString("access_token");
            log.info("Bearer token successfully generated");
        } else {
            log.info("Invalid response. Status code: " + response.getStatusCode());
        }

        return bearerToken;
    }

    public int getLatestLaunchId(String bearerToken, String projectName, String latestUuid) {
        Response response =  given()
                .auth().oauth2(bearerToken)
                .contentType(ContentType.JSON)
                .when()
                .get("https://reportportal.epam.com/api/v1/"+ projectName + "/launch/latest?filter.eq.uuid=" + latestUuid)
                .then()
                .log().body()
                .assertThat().statusCode(200).extract().response();

        int latestLaunchIdInt = response.path("content[0].id");

        return latestLaunchIdInt;
    }

    public void stopLaunchExecution(String currentDate, String latestLaunchId, String bearerToken, String projectName) {
        JSONObject innerObject = new JSONObject();
        innerObject.put("description", "Stopped by automated test");
        innerObject.put("endTime", currentDate);
        innerObject.put("status", "PASSED");

        JSONObject entities = new JSONObject();
        entities.put(latestLaunchId, innerObject);

        JSONObject requestBodyJson = new JSONObject();
        requestBodyJson.put("entities", entities);

        String requestBody = requestBodyJson.toString();

        given().
                auth().oauth2(bearerToken)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("https://reportportal.epam.com/api/v1/" + projectName + "/launch/stop")
                .then()
                .log().body()
                .assertThat().statusCode(200);
    }
}
