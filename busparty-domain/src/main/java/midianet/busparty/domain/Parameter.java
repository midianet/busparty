package midianet.busparty.domain;

import lombok.Data;

/**
 * Entity Parameter
 */
@Data
public class Parameter {
    private String key;          //Parameter Key
    private String description;  //Description Key
    private String value;        //Value key
}
