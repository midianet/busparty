package midianet.busparty.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity bedroom
 */
@Data
@Entity
@Table(name = "tb_bedroom")
public class Bedroom {

    @Id
    @Column(name = "bedr_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;            //Bedroom idenfy

    @NotNull
    @Size(min = 4 , max = 20)
    @Column(name = "bedr_description")
    private String description; //Bedroom description

    @NotNull
    @Column(name = "bedr_type")
    @Enumerated(EnumType.ORDINAL)
    private TypeRoom type;       //Bedroom type

}
