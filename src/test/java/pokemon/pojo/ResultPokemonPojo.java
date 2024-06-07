package pokemon.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class ResultPokemonPojo {
    private String name;
    private String url;

}