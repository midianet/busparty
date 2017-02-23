package midianet.latinoware.api.model;

import lombok.Data;

/**
 * Represents a room type
 */
@Data
public class RoomType {
    private Long id;              // Type idenfy
    private String description;   // Type description
    private double ammount;       // Type ammount
}