package delete;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class PetStore {
    @Test
    public void deletePetTest() {
        //HERE WE CREATE AN OBJECT FROM PET STORE CLASS FROM 'PUT' PACKAGE AND CALL THE METHOD FROM THAT CLASS
        //WHERE WE CREATED A PET AND UPDATED IT
        //BY CALLING THAT METHOD HERE IN THIS CLASS WE WILL 100% HAVE A NEW PET CREATED SO WE WILL JUST
        //DELETE THE NEW CREATED PET
        put.PetStore petStore = new put.PetStore();
        petStore.updatePetTest();

        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet/289049"; //here we put the petId as a path parameter

        Response response = RestAssured.given().accept(ContentType.JSON).when().delete().then().
                statusCode(200).extract().response();//here we delete our pet

        //HERE WE WILL IMPLEMENT 3rd TYPE OF DESERIALIZATION
        // jsonPath()
        //BY USING JSON PATH WE CAN DIRECTLY GO TO JSON OBJECT AND GET THE VALUE OF THE KEY THAT WE NEED
        // WE PROVIDE THE KEY IN THE PARENTHESIS AS A STRING BECAUSE KEY IS ALWAYS STRING
        //AND WE USE getString() method BECAUSE THE VALUE OF 'message' is String as well
        JsonPath jsonPath = response.jsonPath(); //return type is JsonPath
        String responseMessage = jsonPath.getString("message");

        Assert.assertEquals("289049", responseMessage);//WE VALIDATE IF THE MESSAGE VALUE IS OUR PET ID

    }
}
