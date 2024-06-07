package post;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.PetPojo;
import utils.PayloadUtils;

public class PetStore {
    @Test
    public void createPetTest() {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";

        //POST "https://petstore.swagger.io/v2/pet
        //GET "https://petstore.swagger.io/v2/pet/12390

        /*
        1- WE created a Pet
        2- Validated POST call response body status code
        3- Sent get request with the new petId
        4- Validated GET call response body and status code
         */

        int petId = 12390;
        String petName = "Jerry";
        String petStatus = "waiting";

        Response response = RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON).
                body(PayloadUtils.getPetPayload(petId, petName, petStatus)).when().
                post().then().statusCode(200).extract().response();

        PetPojo parsedResponse = response.as(PetPojo.class);
        //deserializing the Json Object(POST response) to PetPojo data type

        Assert.assertEquals(petName, parsedResponse.getName());
        Assert.assertEquals(petId, parsedResponse.getId());
        Assert.assertEquals(petStatus, parsedResponse.getStatus());

        //HERE WE ARE VALIDATING IF THE PET WE CREATED IS REALLY THE SAME PET WHEN WE DO A GET REQUEST OF THE
        // SPECIFIC PET ID
        Response response2 = RestAssured.given().accept(ContentType.JSON).when().
                get("https://petstore.swagger.io/v2/pet/" + petId).then()
                .statusCode(200).extract().response();
        PetPojo parsedResponse2 = response2.as(PetPojo.class);
        //deserializing the Json Object (GET response) to PetPojo data type

        Assert.assertEquals(petName, parsedResponse2.getName());
        Assert.assertEquals(petId, parsedResponse2.getId());
        Assert.assertEquals(petStatus, parsedResponse2.getStatus());
    }
}

