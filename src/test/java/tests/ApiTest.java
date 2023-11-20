package tests;

import core.LoggerSingleton;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.slf4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest extends BaseApiClient {

    private final Logger log = LoggerSingleton.getLogger();
    private String bearerToken;
    private String projectName = "serhii_veselov_personal";
    private int launchId = 6074506;
    private int incorrectLaunchId = 666;
    private String latestUuid;
    private String latestLaunchId;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String currentDate = ZonedDateTime.now().format(formatter);

    @BeforeTest
    public void setUp() {
        bearerToken = getBearerToken("password","pungvun4uk", "Welcome!123");
    }

    @Test(priority = 1, testName = "GET Positive - Get launch by id")
    @Description("API Test GET - Get launch by its Id")
    public void getLaunchById() {
        given().auth().oauth2(bearerToken).
        when().get("https://reportportal.epam.com/api/v1/" + projectName + "/launch?filter.eq.id=" + launchId).
        then().log().body().assertThat().statusCode(200).body("content[0].id", equalTo(launchId));
    }

    @Test(priority = 2, testName = "GET Negative - Get launch by id")
    @Description("API Test GET - Get launch that doesn't exist")
    public void getLaunchByIncorrectId() {
        given().auth().oauth2(bearerToken)
                .when()
                .get("https://reportportal.epam.com/api/v1/" + projectName + "/launch?filter.eq.id=" + incorrectLaunchId)
                .then()
                .log().body()
                .assertThat().statusCode(200).body("page[0].totalElements", equalTo(null));
    }

    @Test(priority = 3, testName = "POST Positive - Start analyzer")
    @Description("API Test POST - Start launch auto analyzer")
    public void startLaunchAutoAnalyzer() {
        String requestBody = "{\"analyzeItemsMode\":[\"TO_INVESTIGATE\"],\"analyzerMode\":\"ALL\",\"analyzerTypeName\":\"autoAnalyzer\",\"launchId\":"+ launchId + "}";

        given()
                .auth().oauth2(bearerToken)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("https://reportportal.epam.com/api/v1/" + projectName + "/launch/analyze")
                .then()
                .log().body()
                .assertThat().statusCode(200).body("message", equalTo("autoAnalyzer analysis for launch with ID='" + launchId + "' started."));
    }

    @Test(priority = 4, testName = "POST Negative - Start analyzer")
    @Description("API Test POST - Start auto analyzer for launch that doesn't exist")
    public void startAutoAnalyzerForIncorrectLaunch() {
        String requestBody = "{\"analyzeItemsMode\":[\"TO_INVESTIGATE\"],\"analyzerMode\":\"ALL\",\"analyzerTypeName\":\"autoAnalyzer\",\"launchId\":"+ incorrectLaunchId + "}";

        given()
                .auth().oauth2(bearerToken)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("https://reportportal.epam.com/api/v1/" + projectName + "/launch/analyze")
                .then()
                .log().body()
                .assertThat().statusCode(404).body("message", equalTo("Launch '" + incorrectLaunchId + "' not found. Did you use correct Launch ID?"));
    }

    @Test(priority = 5, testName = "PUT Positive - Edit launch description")
    @Description("API Test PUT - Edit launch description")
    public void editLaunchDescription() {
        String requestBody = "{\"attributes\":[{\"key\":\"string\",\"value\":\"string\"}],\"description\":\"Automated test description\",\"mode\":\"DEFAULT\"}";

        given()
                .auth().oauth2(bearerToken)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("https://reportportal.epam.com/api/v1/" + projectName + "/launch/" + launchId + "/update")
                .then()
                .log().body()
                .assertThat().statusCode(200).body("message", equalTo("Launch with ID = '" + launchId + "' successfully updated."));
    }

    @Test(priority = 6, testName = "PUT Negative - Edit launch description")
    @Description("API Test PUT - Incorrect body request")
    public void putIncorrectDataType() {
        String requestBody = "{[{\"key\":\"string\",\"value\":\"string\"}],\"description\":\"Automated test description\",\"mode\":\"DEFAULT\"}";

        given()
                .auth().oauth2(bearerToken)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("https://reportportal.epam.com/api/v1/" + projectName + "/launch/" + launchId + "/update")
                .then()
                .log().body()
                .assertThat().statusCode(400).body("message", containsString("Incorrect Request."));
    }

    @Test(priority = 7, testName = "PUT Negative - Edit launch description")
    @Description("API Test PUT - Edit description of launch that doesn't exist")
    public void editDescriptionOfIncorrectLaunch() {
        String requestBody = "{\"attributes\":[{\"key\":\"string\",\"value\":\"string\"}],\"description\":\"Automated test description\",\"mode\":\"DEFAULT\"}";

        given()
                .auth().oauth2(bearerToken)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("https://reportportal.epam.com/api/v1/" + projectName + "/launch/" + incorrectLaunchId + "/update")
                .then()
                .log().body()
                .assertThat().statusCode(404).body("message", equalTo("Launch '" + incorrectLaunchId + "' not found. Did you use correct Launch ID?"));
    }

    @Test(priority = 8, testName = "POST Positive - Create new launch")
    @Description("API Test POST - Create a new launch")
    public void createNewLaunch() {
        String requestBody = "{\"description\":\"Created by automated test\",\"name\":\"Launch automated test\",\"startTime\":\"" + currentDate + "\"}";

        Response response = given()
                .auth().oauth2(bearerToken)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("https://reportportal.epam.com/api/v1/" + projectName + "/launch")
                .then()
                .log().body()
                .assertThat().statusCode(201)
                .extract().response();

        latestUuid = response.path("id");
        System.out.println("The latest uuid is " + latestUuid);
    }

    @Test(priority = 9, testName = "DELETE Positive - Delete latest launch")
    @Description("API Test DELETE - Delete latest launch")
    public void deleteLatestLaunch() {
        log.info("Getting Id of latest created launch using uuid");
        int latestLaunchIdInt = getLatestLaunchId(bearerToken, projectName, latestUuid);

        latestLaunchId = Integer.toString(latestLaunchIdInt);
        System.out.println(latestLaunchId);

        log.info("Stopping the launch execution");
        stopLaunchExecution(currentDate, latestLaunchId, bearerToken, projectName);

        log.info("Deleting the latest launch");
        given().
                auth().oauth2(bearerToken)
                .contentType(ContentType.JSON)
                .when()
                .delete("https://reportportal.epam.com/api/v1/" + projectName + "/launch/" + latestLaunchId)
                .then()
                .log().body()
                .assertThat().statusCode(200).body("message", equalTo("Launch with ID = '" + latestLaunchId + "' successfully deleted."));

    }

    @Test(priority = 10, testName = "DELETE Negative - Delete launch that doesn't exist")
    @Description("API Test DELETE - Delete launch that doesn't exist")
    public void deleteLaunchWithIncorrectId() {
        log.info("Deleting the launch using incorrect id");
        given().
                auth().oauth2(bearerToken)
                .contentType(ContentType.JSON)
                .when()
                .delete("https://reportportal.epam.com/api/v1/" + projectName + "/launch/" + incorrectLaunchId)
                .then()
                .log().body()
                .assertThat().statusCode(404).body("message", equalTo("Launch '" + incorrectLaunchId + "' not found. Did you use correct Launch ID?"));

    }
}
