package post;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;
import pojo.PetPojo;

import java.io.File;
import java.io.IOException;

public class Serialization {

    @Test
    public void serializationTest() throws IOException { //throws IOException -- means Declaring an Exception
        // and when we use try and catch block it means -- Handling an Exception

        PetPojo pet = new PetPojo();
        //we are creating an Object from PetPojo Class

        pet.setName("Hutch"); //we assign pet name
        pet.setStatus("serving"); // assign pet status
        pet.setId(893421); //assign pet Id

        //OBJECT MAPPER WILL BE THE CLASS THAT WILL HELP US TO DO THE SERIALIZATION
        //WE ARE ALSO CREATING A NEW FILE OBJECT FROM FILE Class
        //in the parenthesis we pass the location(content root) of the resources directory
        // and at the end /pet.json
        File jsonFile = new File("src/test/resources/pet.json");
        ObjectMapper objectMapper = new ObjectMapper(); //importing from "com.fasterxml.jackson.databind"
        objectMapper.writeValue(jsonFile, pet);
        //writeValue() method takes 2 parameters -- 1-jsonFile and 2- Pet Object
        //this method will convert Pet Java Object to Json Object
        //this process is called "SERIALIZATION"
    }

    @Test
    public void serializationTest2() {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";

        File jsonFile = new File("src/test/resources/pet.json");
        RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).body(jsonFile).when()
                .post().then().statusCode(200).and().body("name", Matchers.is("Hutch")).log().body();
        //.log().body() -- we want to see in the console the print-out response of the Json Object
        // Using Matchers Class we are doing the assertion/validation in the setup, in the request.
        //HERE WE HAVE PASSED THE REQUEST BODY AS A FILE
    }

    @Test
    public void serializationTest3() {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";

        PetPojo pet = new PetPojo();
        pet.setId(989323);
        pet.setName("Richard");
        pet.setStatus("eating");

        RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON).body(pet).when().post()
                .then().statusCode(200).and().body("name", Matchers.is("Richard")).and().
                body("status", Matchers.is("eating")).log().body();
        //HERE WE HAVE PASSED THE REQUEST BODY AS A PetPojo Object
        //you don't have to save all this implementation in a response variable if you are not
        // planning to do any future validations. 
    }
}
