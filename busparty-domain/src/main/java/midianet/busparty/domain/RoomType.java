package midianet.busparty.domain;

import lombok.Data;

/**
 * Represents a room type
 */
@Data
public class RoomType {
    private Long id;              // Type idenfy
    private String description;   // Type description
    private double price;         // price room
}