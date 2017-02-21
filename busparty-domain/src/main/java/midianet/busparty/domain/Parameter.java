package midianet.busparty.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity Parameter
 */
@Data
@Entity
@Table(name = "tb_parameter")
public class Parameter {

    @Id
    @Size(min = 4 , max = 10)
    @Column(name = "para_key")
    private String key;          //Parameter Key

    @NotNull
    @Size(min = 4 , max = 20)
    @Column(name = "para_description")
    private String description;  //Description Key

    @NotNull
    @Size(min = 1 , max = 10)
    @Column(name = "para_description")
    private String value;        //Value key
}
