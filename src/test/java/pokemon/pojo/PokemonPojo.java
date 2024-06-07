package pokemon.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class PokemonPojo {
    private int count;
    private String next;
    private String previous;
    private List<Map<String, String>> maps;
    private List<ResultPokemonPojo> results;

}
