package midianet.busparty.rep;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Represents payment for a partner
 */
@Data
public class Payment {
    private Long id;             //Indetify Payment
    private Date date;           //Pay day
    private Date dateCompletion; //Date of the conclusion payment
    private BigDecimal ammount;  //Ammount paid
}