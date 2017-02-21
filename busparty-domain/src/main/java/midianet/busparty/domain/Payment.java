package midianet.busparty.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Entity payment
 */
@Data
@Entity
@Table(name = "tb_payment")
public class Payment {

    @Id
    @Column(name="paym_id")
    private Long id;             //Payment indetify

    @Past
    @NotNull
    @Column(name="paym_date")
    private Date date;           //Pay day

    @Column(name="paym_completion")
    private Date dateCompletion; //Date of the conclusion payment

    @Column(name="paym_ammount")
    private BigDecimal ammount;  //Ammount paid

}