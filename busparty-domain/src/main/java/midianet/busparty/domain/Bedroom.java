package midianet.busparty.domain;

import lombok.Data;

/**
 * Entity bedroom
 */
@Data
public class Bedroom {
    public static final int TYPE_DOUBLE          = 2;
    public static final int TYPE_TRIPLE_WOMAN    = 30;
    public static final int TYPE_TRIPLE_MAN      = 31;
  //public static final int TYPE_TRIPLE_MIX      = 32;  //verify rule
    public static final int TYPE_QUADRUPLE_WOMAN = 40;
    public static final int TYPE_QUADRUPLE_MAN   = 41;
  //public static final int TYPE_QUADRUPLE_MIX   = 42;  //verify rule

    private Long id;            //Bedroom idenfy
    private String description; //Bedroom description
    private Integer type;       //Bedroom type

    /**
     * Set bedrom type, accept only types inner class
     * @param type Bedroom type
     */
    public void setType(int type){
        switch (type) {
            case 2:
                break;
            case 30:
                break;
            case 31:
                break;
//           case 32:   //verifi rule
//               break;
            case 40:
                break;
            case 41:
                break;
//           case 42:    //verify rule
//               break;
            default:
                throw new RuntimeException("Invalid type");
        }
        this.type = type;
    }

}
