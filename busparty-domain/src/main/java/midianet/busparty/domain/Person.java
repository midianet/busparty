package midianet.busparty.domain;

import lombok.Data;

import java.util.Date;

/**
 * Represents a Person
 */
@Data
public class Person {
    private Long   id;           // Person idenfy
    private Long telegram;       // Telegram identify
    private String name;         // Person name
    private Date register;       // Registration date
    private Gender gender;       // Person gender
    private Bedroom bedroom;     // Room of the person
    private boolean confirmed;   // Flag confirmed (pay)

/*
    private boolean concorda;
    private boolean pagou;
    private boolean cerveja;
    private boolean refrigerante;
    private boolean ice;
    private boolean suco;
    private boolean energetico;
    private boolean toddynho;
    private boolean aguaCoco;
*/
}