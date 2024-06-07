package post;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.SlackPojo;
import utils.PayloadUtils;

public class Slack {
    @Test
    public void sendSlackMessageText() {
        RestAssured.baseURI = "http://slack.com";
        RestAssured.basePath = "api/chat.postMessage";

        String message = "Mihaela: Hello from Java";
        Response response = RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON).
                header("Authorization", "Bearer xoxb-4349924244708-514005915").body(PayloadUtils.
                        getSlackMessagePayload(message)).when().post().then().statusCode(200).extract()
                .response();
        SlackPojo parsedResponse = response.as(SlackPojo.class);
        boolean isOk = parsedResponse.isOk();
        String actualMsg = parsedResponse.getMessage().getText();
        Assert.assertTrue("Failed to send Slack message", isOk);
        //the message will appear in case it fails //message is at the beginning before the validation

        Assert.assertEquals("Failed to Validate Slack Message", message, actualMsg);
        //the message will show in the console in case it fails
        // message is at the beginning before the validation
        //UNTIL HERE WE JUST VALIDATED THAT SLACK SENT THE EXACT MESSAGE THAT A REQUESTED, SO WE ARE VALIDATING
        //THE REQUEST NOT THE RESPONSE YET

        JsonPath jsonPath = response.jsonPath();
        boolean isMessageSent = jsonPath.getBoolean("ok");
        Assert.assertTrue(isMessageSent);//true

        //here we will get to the parent-child key value
        String text = jsonPath.getString("message.text"); //'text' is another key inside 'message' key
        Assert.assertEquals(actualMsg, text);

        String type = jsonPath.getString("message.blocks[0].type");//here we have inside 'message' key
        //another key 'blocks' that stores array of Jason objects, so we use [] to pass the index number
        // of our object and then .type the last key that we are looking for the value
        Assert.assertEquals("rich_text", type);
        //JSON PATH is RestAssured Specific
    }
}
