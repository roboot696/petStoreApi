package features.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PetSteps {
    private Response response;
    private static final String BASE_URL = "http://localhost:8080/api/v3/pet/";
    @Given("the user wants to add a new pet to the store")
    public void theUserWantsToAddANewPetToTheStore() {
    }

    @When("the user submits the correct information of the new pet")
    public void theUserSubmitsTheCorrectInformationOfTheNewPet() {
        String petInfo = """
                {
                  "id": 10,
                  "name": "doggie",
                  "category": {
                    "id": 1,
                    "name": "Dogs"
                  },
                  "photoUrls": [
                    "string"
                  ],
                  "tags": [
                    {
                      "id": 0,
                      "name": "string"
                    }
                  ],
                  "status": "available"
                }
                """;

        response = given()
                .contentType(ContentType.JSON)
                .body(petInfo)
                .post(BASE_URL);
    }




    @When("^the user find a pet by status (.*)")
    public void theUserFindAPetByStatus(String status) {

            response = RestAssured.given()
                    .queryParam("status", status).get(BASE_URL+"findByStatus");
        }

    @When("^the user find a pet by tag (.*)")
    public void theUserFindAPetByTagTag(String tag) {

         response = RestAssured.given()
                .queryParam("tags", tag)
                .get(BASE_URL+"findByTags");
    }

    @Then("the request is successfully")
    public void theRequestIsSuccessfully() {
        assertEquals(200, response.getStatusCode());
    }

    @Then("the system returns invalid status value")
    public void theSystemReturnsInvalidStatusValue() {
        assertEquals(400, response.getStatusCode());
    }

    @When("^the user find a pet by Id (.*)")
    public void theUserFindAPetByIdID(int ID) {
        response = RestAssured.given()
                .get(BASE_URL+ID);
    }

    @Then("pet not found message")
    public void petNotFoundMessage() {
        assertEquals(404, response.getStatusCode());
        assertTrue(response.getBody().asString().contains("Pet not found"));
    }

    @When("^the user wants to delete a pet by (.*)")
    public void theUserWantsToDeleteAPetByPetID(int petID) {
        //RestAssured.baseURI = "http://localhost:8080/api/v3/user/";
        response = given().delete(BASE_URL+petID);
    }

    @When("^the user want to update a pet by ID (.*)")
    public void theUserWantToUpdateAPetByID(int ID) {
        String petInfo = "{\n" +
                "  \"id\": " + ID + ",\n" +
                "  \"name\": \"doggie\",\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Dogs\"\n" +
                "  },\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(petInfo)
                .put(BASE_URL);

    }

    @When("^the user want to update a pet by ID, name and status (.*) (.*) (.*)")
    public void theUserWantToUpdateAPetByIDNameAndStatusPetIDNewNameStatus(int petID, String name, String status) {
        response = RestAssured.given().queryParam("id", petID).queryParam("name",name).queryParam("status",status)
                .contentType(ContentType.JSON)
                .post(BASE_URL+petID);

    }

}
