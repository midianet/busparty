package midianet.busparty.domain;

import lombok.Data;

/**
 * Represents a Cost
 */
@Data
public class Cost {
    private String description; //Cost description
    private double ammount;     //Cost value
}