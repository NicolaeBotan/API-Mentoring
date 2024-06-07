package get;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pojo.CompetitionsFootBallPojo;
import pojo.FootballPojo;

import java.util.ArrayList;
import java.util.List;

public class Football {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://api.football-data.org";
        RestAssured.basePath = "v2/competitions";
    }

    @Test
    public void competitionTest() {

        RestAssured.given().accept(ContentType.JSON).when().get().then().statusCode(200)
                .contentType(ContentType.JSON).body("count", Matchers.equalTo(177)).and().
                body("competitions[0].name", Matchers.equalTo("AFC Champions League"));
        //THIS IS ANOTHER WAY OF DOING ASSERTION USING 'MATCHERS' CLASS(Library)
        //so we are doing Validation in the GET Call.
    }

    @Test
    public void competitionSearchTest() {
        //GET http://api.football-data.org/v2/competitions
        // Parse the response
        //Search for MLS Competition
        //Validate that MLS competition id = 2145

        Response response = RestAssured.given().accept(ContentType.JSON).get().then().statusCode(200)
                .extract().response();

        FootballPojo parsedResponse = response.as(FootballPojo.class);
        List<CompetitionsFootBallPojo> competitions = parsedResponse.getCompetitions();
        //we have to iterate through each element/object in the List to get their name and id
        //we will use for each loop

        for (CompetitionsFootBallPojo competition : competitions) {
            if (competition.getName().equals("MLS")) {
                Assert.assertEquals(2145, competition.getId());
            }
        }
        //in this loop we will find the name by id

        for (CompetitionsFootBallPojo competition : competitions) {
            if (competition.getId() == 2145) {
                Assert.assertEquals("MLS", competition.getName());
                System.out.println(competition.getName());
            }
        }

    }

    @Test    //ADVANCED REST ASSURED WAY
    public void test2() {
        //HERE WE DO THE ASSERTION INSIDE THE SETUP
        //THIS IS A METHOD TO FIND THE OBJECT FROM LIST USING GROOVY LANGUAGE(CHILD OF JAVA LANGUAGE)
        //SO THE GROOVY METHOD IS LOOPING THROUGH THE LIST UNTIL WILL FIND THE ELEMENT/OBJECT
        //WITH THE NAME THAT EQUALS 'MLS' AND IT WILL MATCH(MATCHER) WITH ID VALUE THAT WE PROVIDE 2145
        //YOU CAN APPLY THE GROOVY 'FIND' METHOD ONLY WHEN YOU HAVE TO ITERATE THROUGH A LIST OF OBJECTS
        // 'it' REFERS TO EVERY JSON OBJECT (like 'this' in Java)

        Response response = RestAssured.given().accept(ContentType.JSON).get().then().statusCode(200)
                .body("competitions.find { it.name == 'MLS' }.id", Matchers.equalTo(2145)).
                extract().response();
    }

    @Test
    public void test3() {
        //we will collect all the names from List of Json Object and store them in a List of Strings
        // 'collect' Groovy method will collect all the names from the List of Json Object
        // 'it' will get the name from every Json Object
        //then we have a List of competition names we will do the Assertion/Validation
        //will Validate if the List of Names contains name "Supercopa Argentina"
        //'hasItem' it's a Matchers Class method and will check if our name is in the List of names.

        Response response = RestAssured.given().accept(ContentType.JSON).get().then().statusCode(200)
                .body("competitions.collect { it.name } ", Matchers.hasItem("Supercopa Argentina"))
                .extract().response();

        List<String> results = response.path("competitions.collect { it.name }");
        //we use Groovy methods 'collect' and 'it'
        //System.out.println(results);
        System.out.println(results.size());//177 names
        Assert.assertEquals(177, results.size());

        //SUM ALL THE ID FOR ALL THE COMPETITIONS
        int sum = response.path("competitions.collect { it.id }.sum()");
        System.out.println(sum);


        //here we will get all Country names that the competitions id is greater than 2006
        //'findAll' groovy language method will return all the Elements
        // will return us a List Of strings
        List<String> countryNames = response.path("competitions.findAll { it.id > 2006 }.area.name");
        System.out.println(countryNames.size());//170
        //System.out.println(countryNames);
        Assert.assertEquals(170, countryNames.size());

        //here I will implement a method to iterate through the List of countryNames and store them
        // in another List that will not repeat the same countryName
        List<String> uniqueCountryNames = new ArrayList<>();
        for (String country : countryNames) {
            if (!uniqueCountryNames.contains(country)) {
                uniqueCountryNames.add(country);
            }
        }
        System.out.println(uniqueCountryNames);
        System.out.println(uniqueCountryNames.size());
    }
}
