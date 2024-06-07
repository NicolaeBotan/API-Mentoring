package pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
//@Setter //from lombok -- it will automatically generate setters for us
//@Getter //from lombok -- it will automatically generate getters for us
public class StarWarsPojo {
    //IN POJO CLASS WE CREATE PRIVATE VARIABLES OF THE KEYS THAT WE HAVE IN OUR POSTMAN
    //TO BE ABLE TO MANIPULATE EACH SECTION/KEY SEPARATELY
    //THE DOWNSIDE OF POJO CLASSES IS THAT YOU HAVE O DEFINE ALL THE VARIABLE/KEYS FROM JSON OBJECT
    //even if, don't need one of the variables/key from Json Object
    //but, you have to define them all
    // The reason we are creating our Variables Private is that this is the definition of POJO Class,
    //variables have to be private
    private int count;
    private String next;
    private String previous;
    private List<Map<String, Object>> maps; //this will not be used, it just helps us at the begging
    //this field will be ignored because we don't have a keys called
    // maps in our JSON Object
    //In order to get access to the ResultPojo Class where we defined all the keys/variables from the
    // List<Map<String,Object>> we create results variable and will be a List<ResulPojo>
    private List<ResultPojo> results;
    //same we have an inner Json Object inside another Json Object, here we have one
    //POjo Class inside another POJO Class

    //NOW WE HAVE THE VARIABLES AND WE NEED TO GENERATE THE GETTERS AND SETTERS BECAUSE OF PRIVATE VARIABLES
    // to have access to these private variables
    //right click 'generate', getters and setters and select all variables that you need
}
