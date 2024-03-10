package features.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserSteps {
    private Response response;
    private String username;
    private static final String BASE_URL = "http://localhost:8080/api/v3/user/";

    @When("the user send the information with a POST request")
    public void the_user_send_the_information_with_a_post_request() {
        String registerUserInfo = """
                {
                  "id": 10,
                  "username": "theUser",
                  "firstName": "John",
                  "lastName": "James",
                  "email": "john@email.com",
                  "password": "12345",
                  "phone": "12345",
                  "userStatus": 1
                }
                
                """;

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(registerUserInfo)
                .post(BASE_URL);
    }

    @When("^the user sends his correct (.*) and (.*) information")
    public void theUserSendsHisCorrectUsernameAndPasswordInformation(String username, Integer password) throws Throwable{

        response = RestAssured.given()
                .queryParam("username", username)
                .queryParam("password", password)
                .get(BASE_URL + "login");
    }


    @Given("user that is logged in the system")
    public void userThatIsLoggedInTheSystem() {
    }
    @When("the user logs out")
    public void theUserLogsOut() {
        response = given()
                .when()
                .get(BASE_URL + "logout");


    }
    @Then("the system confirm the user is logged out")
    public void theSystemConfirmTheUserIsLoggedOut() {

        response.then()
                .assertThat()
                .statusCode(200).and().assertThat().body(equalTo("User logged out"));
    }

    @Given("a system user")
    public void aSystemUser() {
    }
    @When("^a request is made to get the user with an (.*)")
    public void aRequestIsMadeToGetTheUserWithAnUsername(String username) {

        response = given()
                .when()
                .get(BASE_URL + username)
                .then()
                .extract()
                .response();
    }
    @Then("the request is sent successfully")
    public void requestSendSuccessfully() {
        assertEquals(200, response.getStatusCode());

    }


    @Then("the system returns user error message")
    public void theSystemReturnsUserErrorMessage() {
        assertEquals(404, response.getStatusCode());
        assertTrue(response.getBody().asString().contains("User not found"));
    }

    @When("^a request is made to delete the user with (.*)")
    public void aRequestIsMadeToDeleteTheUserWithUsername(String username) {
       // RestAssured.baseURI = "http://localhost:8080/api/v3/user/";
        response = given().delete(BASE_URL+username);
    }

}
