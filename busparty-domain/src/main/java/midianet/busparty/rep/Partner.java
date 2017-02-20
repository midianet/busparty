package midianet.busparty.rep;

import lombok.Data;

import java.util.Date;

/**
 * Represents a busparty partner
 */
@Data
public class Partner {
    private Long id;       //Idenfy partner
    private String name;   //Name partner
    private Date dateAdd;  //Date add partner
    private Integer sex;   //Sex partner
}