package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import pojo.PetPojo;

import java.util.ArrayList;
import java.util.List;

public class PetStoreStepDef {

    Response response; //we created a 'response' instance to be able to call it in every step

    @Given("User has PetStore endpoint")
    public void user_has_pet_store_endpoint() {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";
    }

    @When("User sends Get request to list Pets")
    public void user_sends_get_request_to_list_pets() {
        response = RestAssured.given().accept(ContentType.JSON).queryParam("status", "sold")
                .when().get("findByStatus");//to find all pets by status that are "sold"
    }

    @Then("Status Code is {int}")
    public void status_code_is(int expectedStatusCode) {
        //response.prettyPrint(); //prints response body in a nice way
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Then("Response contains List of Pets")
    public void response_contains_list_of_pets() {

        List<PetPojo> petPojoList = new ArrayList<>(); //we create this List because we have a list
        //of Json Objects in Postman when we do manual GET request of 'sold' pets.
        petPojoList = response.as(petPojoList.getClass()); // we do list. getClass because it's an Array

        //we will validate if our List contains any pets(is greater than 0)
        Assert.assertTrue(petPojoList.size() > 0);
    }

    //here we validate a different Scenario
    @When("^User sends GET request to list of (non-existing|invalid) pet by id$") // ^ begging and $ at the end
    //this StepDef can serve 2 Scenarios
    // so here we parametrized this StepDef and we can use 2 different parameters for 2 Scenarios
    //but the same StepDef. So we can use one time 'non-existing' parameter and 'invalid' parameter also
    public void user_sends_get_request_to_list_of_non_existing_pet_by_id(String petIdType) {
        String petId;
        //we created a local variable to use it for if condition and pass it as parameter for get() call
        if (petIdType.equals("non-existing")) {
            petId = "47783945"; //non-existing petId
        } else {
            petId = "477839e45";// invalid petId
        }
        response = RestAssured.given().accept(ContentType.JSON).when().get(petId);
        //we provided 'petId' as id parameter in the get() method
    }

    @Then("Error message is {string}")
    public void error_message_is(String errorMessage) {
        response.then().body("message", Matchers.is(errorMessage));
        //here we validated the message in the setup using Matchers Class
    }

}
