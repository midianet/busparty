package midianet.busparty.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Entity payment
 */
@Data
public class Payment {
    private Long id;             //Payment indetify
    private Date date;           //Pay day
    private Date dateCompletion; //Date of the conclusion payment
    private BigDecimal ammount;  //Ammount paid
}