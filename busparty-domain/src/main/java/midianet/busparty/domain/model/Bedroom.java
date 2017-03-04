package midianet.busparty.domain.model;

import lombok.Data;

import java.util.List;

/**
 * Represents a bedroom
 */
@Data
public class Bedroom {
    private Long id;                 //Bedroom idenfy
    private RoomType type;           //Bedroom type
    private RoomKind kind;           //Bedroom class
    private List<Person> occupants;  //Occupants of bedroom
}