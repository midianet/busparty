package midianet.busparty.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Entity "busparty" partner
 */
@Data
@Entity
@Table(name = "tb_partner")
public class Partner {

    @Id
    @Column(name = "part_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;       //Partner idenfy

    @NotNull
    @Column(name = "part_telegram")
    private String telegram;

    @NotNull
    @Size(min = 4, max = 30)
    @Column(name = "part_telegram")
    private String name;   //Partner name

    @NotNull
    @Column(name = "part_telegram")
    private Date dateAdd;  //Date added

    @Column(name = "part_gender")
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;   //Partner gender

    @Column(name = "part_agree")
    private boolean agree; //Partner agree to the rules


}