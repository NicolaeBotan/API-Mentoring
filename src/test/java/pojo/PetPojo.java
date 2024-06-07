package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true) //this annotation will ignore undefined keys from Json Object
                                            // so, we don't have to create them if we don't need them
@Getter // this annotation will automatically generate getters under the scene for my variables
@Setter // this annotation will automatically generate setters under the scene for my variables
public class PetPojo {
    private int id;
    private String name;
    private String status;

}
