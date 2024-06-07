package pokemon.get;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pokemon.pojo.EachPokemonPojo;
import pokemon.pojo.PokemonAbilitiesPojo;
import pokemon.pojo.PokemonPojo;
import pokemon.pojo.ResultPokemonPojo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Pokemon {
    @Test
    public void pokemonApiGetWithPojo() {
        RestAssured.baseURI = "https://pokeapi.co/api/v2/pokemon";
        //?limit=1302 queryString parameter (to have a limit of 1302 pokemons)
        //we put this query String parameter inside the queryParam() method as key and value

        Response response = RestAssured.given().header("Accept", "application/json").queryParam("limit",
                "1302").when().get().then().statusCode(200).extract().response();
        //we create the 'response' variable where we read all the data from the given url

        PokemonPojo deserializedResponse = response.as(PokemonPojo.class);
        //we deserialize the json object to java data type(Object) to be able to manipulate in java

        int actualCount = deserializedResponse.getCount();
        int expectedCount = 1302;
        Assert.assertEquals(expectedCount, actualCount);
        //we compare the pokemon count the expected and the actual

        List<ResultPokemonPojo> results = deserializedResponse.getResults();
        Assert.assertEquals(1302, results.size());
        //we validate if we have stored in our list 1302 pokemons as given in expected

        //in this for loop we will iterate through the list and find the name 'pikachu'
        //and print out the url of pikachu
        for (ResultPokemonPojo result : results) {
            if (result.getName().equalsIgnoreCase("pikachu")) {
                System.out.println(result.getName() + " --> url is " + result.getUrl());
                //https://pokeapi.co/api/v2/pokemon/25/
            }
        }
        for (ResultPokemonPojo result2 : results) {
            if (result2.getName().equalsIgnoreCase("glastrier")) {
                System.out.println(result2.getName() + " --> url is " + result2.getUrl());
                //https://pokeapi.co/api/v2/pokemon/896/
            }
        }
    }

    @Test
    public void homeWorkPokemon() {
        Response response = RestAssured.given().accept(ContentType.JSON).when().
                get("https://pokeapi.co/api/v2/pokemon").then().statusCode(200).extract().response();

        PokemonPojo parsedResponse = response.as(PokemonPojo.class);
        List<ResultPokemonPojo> results = parsedResponse.getResults();
        Assert.assertEquals(20, results.size());

        Map<String, List<String>> res = new LinkedHashMap<>();

        for (int i = 0; i < results.size(); i++) {
            Response response1 = RestAssured.given().accept(ContentType.JSON).when().
                    get(results.get(i).getUrl()).then().statusCode(200).extract().response();

            EachPokemonPojo parsedResponse1 = response1.as(EachPokemonPojo.class);
            List<PokemonAbilitiesPojo> abilities = parsedResponse1.getAbilities();
            List<String> eachAbility = new ArrayList<>();
            String pokemonName = parsedResponse.getResults().get(i).getName();

            for (int j = 0; j < abilities.size(); j++) {
                eachAbility.add(parsedResponse1.getAbilities().get(j).getAbility().getName());
            }
            res.put(pokemonName, eachAbility);
        }
        System.out.println(res);

    }
}
