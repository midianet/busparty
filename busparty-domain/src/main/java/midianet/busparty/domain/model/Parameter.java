package midianet.busparty.domain.model;

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