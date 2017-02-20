package midianet.busparty.rep;

import lombok.Data;

/**
 * Represents a bedroom entity
 */
@Data
public class Bedroom {
    private Long id;             //Bedroom idenfy
    private String description;  //Bedroom description
    private Integer type;        //Bedroom type
}