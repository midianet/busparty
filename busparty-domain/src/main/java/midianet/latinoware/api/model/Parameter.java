package midianet.latinoware.api.model;

import lombok.Data;

/**
 * Represents a Parameter.
 */
@Data
public class Parameter {
    private String key;          //Parameter Key
    private String description;  //Description Key
    private String value;        //Value key
}