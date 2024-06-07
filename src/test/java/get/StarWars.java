package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.ResultPojo;
import pojo.StarWarsPojo;

import java.util.List;
import java.util.Map;

public class StarWars {

    // 1-Define the EndPoint
    // 2-Add query string parameters
    // 3-Define HTTP Method
    // 4-Send
    // 5-Validate Status Code

    @Test
    public void getStarWarsCharacters() {

        RestAssured.given().when().get("https://swapi.dev/api/people").then().statusCode(200);
        //here we tell java to get(read) this URL and then check the Status Code if its 200 (Success)

        //if you want to print all the info that you have in Postman, you add .log().body(); at the end
        RestAssured.given().when().get("https://swapi.dev/api/people").then().statusCode(200).log().body();
    }

    @Test
    public void getGameOfThronesHouses() {
        RestAssured.given().when().get("https://www.anapioficeandfire.com/api/houses")
                .then().statusCode(200).log().body();
    }

    @Test
    public void getStarWarsCharsDeserialized() {
        //Everything starts with RestAssured
        Response response = RestAssured.given().header("Accept", "application/json").when().get
                ("http://swapi.dev/api/people").then().statusCode(200).extract().response();
        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        int count = (int) deserializedResponse.get("count"); //we explicitly cast An Object into int
        Assert.assertEquals(82, count);

        //Array of JSON objects[{},{},{}]
        List<Map<String, Object>> results = (List<Map<String, Object>>) deserializedResponse.get("results");
        //System.out.println(results);
        int num = results.size();
        // System.out.println(num);
        Response response2 = RestAssured.given().header("Accept", "application/json").when().get
                ("https://swapi.dev/api/people/?page=2").then().statusCode(200).extract().response();
        Map<String, Object> deserializedResponse2 = response2.as(new TypeRef<Map<String, Object>>() {
        });
        List<Map<String, Object>> results2 = (List<Map<String, Object>>) deserializedResponse2.get("results");
        //System.out.println(results2);
        num += results2.size();
        //System.out.println(num);
        Response response3 = RestAssured.given().header("Accept", "application/json").when().get
                ("https://swapi.dev/api/people/?page=3").then().statusCode(200).extract().response();
        Map<String, Object> deserializedResponse3 = response3.as(new TypeRef<Map<String, Object>>() {
        });
        List<Map<String, Object>> results3 = (List<Map<String, Object>>) deserializedResponse3.get("results");
        //System.out.println(results3);
        num += results3.size();
        //System.out.println(num);

        Response response4 = RestAssured.given().header("Accept", "application/json").when().get
                ("https://swapi.dev/api/people/?page=4").then().statusCode(200).extract().response();
        Map<String, Object> deserializedResponse4 = response4.as(new TypeRef<Map<String, Object>>() {
        });
        List<Map<String, Object>> results4 = (List<Map<String, Object>>) deserializedResponse4.get("results");
        //System.out.println(results4);
        num += results4.size();
        //System.out.println(num);

        Response response5 = RestAssured.given().header("Accept", "application/json").when().get
                ("https://swapi.dev/api/people/?page=5").then().statusCode(200).extract().response();
        Map<String, Object> deserializedResponse5 = response5.as(new TypeRef<Map<String, Object>>() {
        });
        List<Map<String, Object>> results5 = (List<Map<String, Object>>) deserializedResponse5.get("results");
        //System.out.println(results5);
        num += results5.size();
        //System.out.println(num);

        Response response6 = RestAssured.given().header("Accept", "application/json").when().get
                ("https://swapi.dev/api/people/?page=6").then().statusCode(200).extract().response();
        Map<String, Object> deserializedResponse6 = response6.as(new TypeRef<Map<String, Object>>() {
        });
        List<Map<String, Object>> results6 = (List<Map<String, Object>>) deserializedResponse6.get("results");
        //System.out.println(results6);
        num += results6.size();
        //System.out.println(num);

        Response response7 = RestAssured.given().header("Accept", "application/json").when().get
                ("https://swapi.dev/api/people/?page=7").then().statusCode(200).extract().response();
        Map<String, Object> deserializedResponse7 = response7.as(new TypeRef<Map<String, Object>>() {
        });
        List<Map<String, Object>> results7 = (List<Map<String, Object>>) deserializedResponse7.get("results");
        //System.out.println(results7);
        num += results7.size();
        //System.out.println(num);

        Response response8 = RestAssured.given().header("Accept", "application/json").when().get
                ("https://swapi.dev/api/people/?page=8").then().statusCode(200).extract().response();
        Map<String, Object> deserializedResponse8 = response8.as(new TypeRef<Map<String, Object>>() {
        });
        List<Map<String, Object>> results8 = (List<Map<String, Object>>) deserializedResponse8.get("results");
        //System.out.println(results8);
        num += results8.size();
        //System.out.println(num);

        Response response9 = RestAssured.given().header("Accept", "application/json").when().get
                ("https://swapi.dev/api/people/?page=9").then().statusCode(200).extract().response();
        Map<String, Object> deserializedResponse9 = response9.as(new TypeRef<Map<String, Object>>() {
        });
        List<Map<String, Object>> results9 = (List<Map<String, Object>>) deserializedResponse9.get("results");
        //System.out.println(results9);
        num += results9.size();
        System.out.println(num);
        Assert.assertEquals(count, num);
    }

    @Test
    public void starWarsApiGetWithPojo() {
        //Response is data type
        //get() in RestAssured means READ, so we provide the URL
        Response response = RestAssured.given().header("Accept", "application/json").when().get
                ("https://swapi.dev/api/people").then().statusCode(200).extract().response();
        //we have to use extract() method because this method coverts ValidatableResponse to response
        // we do log.body() to print something in the console

        StarWarsPojo deserializedResponse = response.as(StarWarsPojo.class);
        //here we do the 'Deserialization' by calling the POJO class that we created
        //data type will be StarWarsPojo
        int actualCount = deserializedResponse.getCount();//this will get us the count key in the response
        int expectedCount = 82;
        Assert.assertEquals(expectedCount, actualCount);

        //WHEN YOU MISS SOMETHING,ANY KEYS FROM JSON OBJECT AND YOU DID NOT STORE THE PROPER KEY IN
        // POJO CLASS, THEN WHILE RUNNING THE TEST YOU WILL GET 'UNRECOGNIZED PROPERTY EXCEPTION'
        //'UNRECOGNIZED PROPERTY EXCEPTION' -- means the field in JSON is missing in your POJO Class
        //                                  -- one key in JSON is missing in POJO class
        // Always when you get an exception look for "CAUSED BY" Section

        List<ResultPojo> results = deserializedResponse.getResults();
        //return type of getResults() is a List<ResultPojo>
        //to generate automatic suggested return type, we do ALT + ENTER

        //we iterate through the List Of ResultPojo to get each result
        for (ResultPojo result : results) {
            System.out.println(result.getName() + " and mass = " + result.getMass() +
                    " and birth_year is " + result.getBirth_year());
            //to print out each name and mass and birth_year of the elements from List
        }
    }

    @Test
    public void starWarsTest() {
        //http://swapi.dev/api/people
        // POJO Type Deserialization

        RestAssured.baseURI = "http://swapi.dev"; //url or uri host
        RestAssured.basePath = "api/people"; // url or uri path -- this way we make the path more dynamic
        //the reason we are separating them is that we will use the same host but the path will change a lot

        // there is a short-cut for this "RestAssured.given().header("Accept", "application/json")"
        // here we are defining our header without hardcoded parameters,just using already implemented
        // RestAssured methods
        // THIS IS THE REQUEST THAT WE ARE CREATING AND THE RETURN WE STORE IS IN "response" VARIABLE
        //.log().body() -- is to print out the field , the response or the request.
        // to print out the request we put .log().body() before 'when().get()' request.
        // to print out the response we put .log().body() after statusCode()
        // ALSO .log() has some more methods like .all(), .headers(), .everything() etc...
        // .body() means Json Object body from Postman what we see there when we manually test it
        // we do -- .extract().response() to be able to store the response in a local variable
        //RestAssured.given().accept(ContentType.JSON).log().body().when().get().then().statusCode(200);

        Response response = RestAssured.given().accept(ContentType.JSON).when().get().then().
                statusCode(200).extract().response();

        StarWarsPojo parsedResponse = response.as(StarWarsPojo.class);
        //parse or deserialize is kind of the same

        int actualCharactersCount = parsedResponse.getResults().size();
        //this will collect each character in a List Of Objects and give us the size of the
        // List for specific page (first page)

        //here we will implement a method that will iterate through pages until 'next' variable is null
        String nextUrl = parsedResponse.getNext(); //getNext() is the method we created in Pojo Class
        //will get the value of the next field from Json Object

        //NOW WE WILL LOOP THROUGH THE VALUES OF 'next' variable

        while (nextUrl != null) {

            //1- make a GET request to nextUrl
            //we reuse/reassign our variables, not creating new ones
            response = RestAssured.given().accept(ContentType.JSON).when().get(nextUrl).then().statusCode(200)
                    .contentType(ContentType.JSON).extract().response();
            // accept(ContentType.JSON) -- before .when() means we request Json format
            // contentType(ContentType.JSON) -- after .then() will make sure that the response is Json Object
            //behind the scene there is an Assertion that will verify if the response is in Json data type,
            //if it will not be Json the test will fail here and stop the execution
            // WHY WE ARE doing this validation after .then() is BECAUSE WE CAN NOT DESERIALIZE OTHER
            // FORMAT THEN JSON, so we have to make sure the response will be in Json Format
            parsedResponse = response.as(StarWarsPojo.class); //reusing/reassigning the parsedResponse variable

            //2- count characters from next page
            actualCharactersCount += parsedResponse.getResults().size(); //we add to our Count

            //3- get nextUrl for the next page
            nextUrl = parsedResponse.getNext();
            //we reassign the new Url to the nextUrl variable, so it will keep looping
        }
        Assert.assertEquals(parsedResponse.getCount(),actualCharactersCount);
        //validating if the expected number of characters is matching the actual

    }
}
