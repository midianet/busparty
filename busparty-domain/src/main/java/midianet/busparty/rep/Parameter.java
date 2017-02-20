package midianet.busparty.rep;

import lombok.Data;

/**
 * Represents Parameter entity
 */
@Data
public class Parameter {
    private String key;         //Parameter Key
    private String description; //Description Key
    private String value;       //Value key
}
