package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter //from lombok -- it will automatically generate setters for us
@Getter //from lombok -- it will automatically generate getters for us
@JsonIgnoreProperties(ignoreUnknown = true)
//this is imported for making the class not to fail when you are not defining all the keys as variables from
// Json Object, and if you are not importing this Annotation and you will miss one key  from Json Object
// your test will FAIL

public class ResultPojo { //class name

    //WE CREATE VARIABLES IN POJO CLASS AS MANY AS KEYS IN JSON OBJECT
    //WHEN WE SEE JSON OBJECT WE CREATE POJO CLASS FOR EACH JSON OBJECT
    //@Setter //from lombok -- it will automatically generate setters for us
    //@Getter //from lombok -- it will automatically generate getters for us

    private String name;
    private String height;
    private String mass;
    private String hair_color;
    private String skin_color;
    private String eye_color;
    private String birth_year;
    private String gender;
    private String homeworld;
    private List<String> films;
    private List<String> species;
    private List<String> vehicles;
    private List<String> starships;
    private String created;
    private String edited;
    private String url;

}
