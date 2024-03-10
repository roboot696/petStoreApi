package features.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StoreSteps {
    private Response response;
    private static final String BASE_URL = "http://localhost:8080/api/v3/store/";

    @Given("As a System user")
    public void asASystemUser() {
    }

    @When("the user sends a request to place the order")
    public void theUserSendsARequestToPlaceTheOrder() {
        String requestBody = """
                {
                  "id": 10,
                  "petId": 198772,
                  "quantity": 7,
                  "shipDate": "2024-03-09T23:53:13.318Z",
                  "status": "approved",
                  "complete": true
                }
                """;


        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(BASE_URL+ "order");
    }

    @Then("the order for the pet is successfully placed")
    public void theOrderForThePetIsSuccessfullyPlaced() {
        assertEquals(200, response.getStatusCode());
    }

    @When("^the user requests to find the purchase order by (.*)")
    public void theUserRequestsToFindThePurchaseOrderByID(int ID) {
        response = RestAssured.get(BASE_URL +"order/"+ ID);

    }

    @Then("the system returns the purchase order details")
    public void theSystemReturnsThePurchaseOrderDetails() {
        assertEquals(200, response.getStatusCode());
        assertEquals(10, response.getBody().jsonPath().getInt("id"));
    }
    @Then("the system returns error message")
    public void theSystemReturnsErrorMessage() {
        assertEquals(404, response.getStatusCode());
        assertTrue(response.getBody().asString().contains("Order not found"));
    }
    @When("^the user requests send a request to delete the purchase order by (.*)")
    public void theUserRequestsSendARequestToDeleteThePurchaseOrderByID(Integer ID) {

        response = RestAssured.delete(BASE_URL +"order/"+ ID);

    }

    @Then("the purchase order was deleted")
    public void thePurchaseOrderWasDeleted() {
        assertEquals(200, response.getStatusCode());
    }
}
