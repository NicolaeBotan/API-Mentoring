package put;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.Request;
import pojo.PetPojo;
import utils.PayloadUtils;

public class PetStore {
    /*
    1- POST call to create a Pet
    2- Deserialize and validate POST response
    3- PUT call to update an existing pet
    4- Deserialize and validate PUT response
    5- GET call to search for our pet
    6- Deserialize and validate GET response
     */
    @Test
    public void updatePetTest() {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";
        int petId = 289049;
        String petName = "Domino";
        String petStatus = "playing";

        //HERE WE ARE CREATING A LOCAL VARIABLE TO STORE THE HEADERS( ACCEPT-TYPE AND CONTENT-TYPE)
        //RequestSpecification data type
        RequestSpecification reqSpec = RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON);

        // 1- POST call to create a Pet
        Response response = reqSpec.body(PayloadUtils.getPetPayload(petId, petName, petStatus)).when().post()
                .then().statusCode(200).extract().response();

        //2- Deserialize and validate POST response
        PetPojo parsedResponse = response.as(PetPojo.class);//Deserialized the response
        Assert.assertEquals(petId, parsedResponse.getId());
        Assert.assertEquals(petName, parsedResponse.getName());
        Assert.assertEquals(petStatus, parsedResponse.getStatus());

        //3- PUT call to update an existing pet
        String newStatus = "sleeping";
        //WE REASSIGN THE 'response' and 'parsedResponse' ALL THE TIME FOR EACH REQUEST CALL (POST,PUT,GET)
        response = reqSpec.body(PayloadUtils.getPetPayload(petId, petName, newStatus)).when().put().then().
                statusCode(200).extract().response();

        //4- Deserialize and validate PUT response
        //WE REASSIGN THE 'response' and 'parsedResponse' ALL THE TIME FOR EACH REQUEST CALL (POST,PUT,GET)
        parsedResponse = response.as(PetPojo.class);
        Assert.assertEquals(petId, parsedResponse.getId());
        Assert.assertEquals(petName, parsedResponse.getName());
        Assert.assertEquals(newStatus, parsedResponse.getStatus());

        //5- GET call to search for our pet
        // POST URL https://petstore.swagger.io/v2/pet
        // GET URL https://petstore.swagger.io/v2/pet/{petId}
        //so get() method will take the BaseURI then concatenate BasePath then concatenate the petId that we
        //provide in the parenthesis, but petId is int, and get() takes String as parameter, so we will
        // parse, or we will do String.valueOf(petId)
        //WE REASSIGN THE 'response' and 'parsedResponse' ALL THE TIME FOR EACH REQUEST CALL (POST,PUT,GET)
        response = RestAssured.given().accept(ContentType.JSON).when().get(String.valueOf(petId)).then().
                statusCode(200).extract().response();

        //6- Deserialize and validate GET response
        //WE REASSIGN THE 'response' and 'parsedResponse' ALL THE TIME FOR EACH REQUEST CALL (POST,PUT,GET)
        parsedResponse = response.as(PetPojo.class);
        Assert.assertEquals(petId, parsedResponse.getId());
        Assert.assertEquals(petName, parsedResponse.getName());
        Assert.assertEquals(newStatus, parsedResponse.getStatus());

    }
}

