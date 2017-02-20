package midianet.busparty.domain;

import lombok.Data;

import java.util.Date;

/**
 * Entity "busparty" partner
 */
@Data
public class Partner {
    public static final int SEX_MALE = 1;
    public static final int SEX_FEMALE = 0;

    private Long id;       //Partner idenfy
    private String name;   //Partner name
    private Date dateAdd;  //Date added
    private Integer sex;   //Partner gender
    private boolean agree; //Partner agree to the rules

    /**
     * Set partner gender, accept only male and female
     * @param sex Partner sex
     */
    public void setSex(int sex){
        if(sex < 0 || sex > 1){
            throw new RuntimeException("Invalid type");
        }
        this.sex = sex;
    }

}