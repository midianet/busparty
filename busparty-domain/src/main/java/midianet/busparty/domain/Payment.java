package midianet.busparty.domain;

import lombok.Data;

import java.util.Date;

/**
 * Represents a Payment
 */
@Data
public class Payment {
    private Long id;              // Payment indetify
    private Person person;        // Person  paying
    private Date date;            // Pay day
    private Date done;            // Date of the conclusion payment
    private Double ammount;       // Ammount paid
}