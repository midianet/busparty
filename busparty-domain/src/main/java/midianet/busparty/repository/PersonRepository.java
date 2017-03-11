package midianet.busparty.repository;

import midianet.busparty.exception.InfraException;
import midianet.busparty.domain.Gender;
import midianet.busparty.domain.Person;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class PersonRepository {

    private Logger log = Logger.getLogger(PersonRepository.class);

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public PersonRepository(final JdbcTemplate template) {
        jdbcInsert =  new SimpleJdbcInsert(template)
                            .withTableName("tb_person")
                            .usingColumns("pers_name",
                                          "pers_telegram")
                            .usingGeneratedKeyColumns("pers_id");
    }

//    public void updateAccordance(final Person pessoa){
//        final StringBuilder sql = new StringBuilder();
//        sql.append("update tb_pessoa set ")
//                .append("       pess_acordo = :acordo")
//                .append(" where pess_id           = :id");
//        final Map<String,Object> param = new HashMap();
//       // param.put("acordo",       pessoa.isConcorda());
//        param.put("id",           pessoa.getId());
//        try {
//            jdbc.update(sql.toString(), param);
//        }catch(Exception e){
//            log.error(e);
//            throw new InfraException(e);
//        }
//    }


//    public void updateBebidas(final Person pessoa){
//        final StringBuilder sql = new StringBuilder();
//        sql.append("update tb_pessoa set ")
//           .append("       pess_cerveja      = :cerveja ,")
//           .append("       pess_refrigerante = :refrigerante ,")
//           .append("       pess_suco         = :suco ,")
//           .append("       pess_energetico   = :energetico,")
//           .append("       pess_ice          = :ice ,")
//           .append("       pess_toddynho     = :toddynho ,")
//           .append("       pess_coco         = :coco ")
//           .append(" where pess_id           = :id");
//        final Map<String,Object> param = new HashMap();
//        param.put("name",         pessoa.getName());
////        param.put("cerveja",      pessoa.isCerveja());
////        param.put("refrigerante", pessoa.isRefrigerante());
////        param.put("suco",         pessoa.isSuco());
////        param.put("ice",          pessoa.isIce());
////        param.put("toddynho",     pessoa.isToddynho());
////        param.put("energetico",   pessoa.isEnergetico());
////        param.put("coco",         pessoa.isAguaCoco());
//        param.put("id",           pessoa.getId());
//        try {
//            jdbc.update(sql.toString(), param);
//        }catch(Exception e){
//            log.error(e);
//            throw new InfraException(e);
//        }
//    }

    public void insert(final Person person){
        try {
            final Map params = new HashMap();
            params.put("pers_name",    person.getName());
            params.put("pers_telegram",      person.getTelegram());
            person.setId(jdbcInsert.executeAndReturnKey(params).longValue());
        }catch(Exception e){
            log.error(e);
            throw new InfraException(e);
        }
    }

    public Optional<Person> findByTelegram(final Long id){
        final StringBuilder sql = new StringBuilder();
        sql.append("select pers_id,")
           .append("       pers_name,")
           .append("       pers_register,")
           .append("       pers_telegram,")
           .append("       pers_confirm,")
           .append("       gend_id")
           .append("  from tb_person ")
           .append(" where pers_telegram = :id");
        final Map<String,Object> param = new HashMap();
        param.put("id",id);
        try {
            return Optional.ofNullable(jdbc.queryForObject(sql.toString(), param, this::mapRow));
        }catch(EmptyResultDataAccessException e){
            return Optional.empty();
        }catch(Exception e){
            log.error(e);
            throw new InfraException(e);
        }
    }

    public Optional<Person> findById(final Long id){
        final StringBuilder sql = new StringBuilder();
        sql.append("select pers_id,")
           .append("       pers_name,")
           .append("       pers_register,")
           .append("       pers_telegram,")
           .append("       pers_confirm")
           .append("  from tb_person ")
           .append(" where pers_id = :id");
        final Map<String,Object> param = new HashMap();
        param.put("id",id);
        try {
            return Optional.ofNullable(jdbc.queryForObject(sql.toString(),param, this::mapRow));
        }catch(Exception e){
            log.error(e);
            throw new InfraException(e);
        }
    }

    public List<Person> listAll(){
        final StringBuilder sql = new StringBuilder();
        sql.append("select pers_id,")
           .append("       pers_name,")
           .append("       pers_register,")
           .append("       pers_telegram,")
           .append("       pers_confirm,")
           .append("       gend_id")
           .append("  from tb_person")
           .append(" order by pers_register");
        try {
            return jdbc.query(sql.toString(), this::mapRow);
        }catch(Exception e){
            log.error(e);
            throw new InfraException(e);
        }
    }

    private Person mapRow(final ResultSet rs, final int i) throws SQLException {
        final Person person = new Person();
        person.setId      (rs.getLong     ("pers_id"));
        person.setName    (rs.getString   ("pers_name"));
        person.setTelegram(rs.getLong     ("pers_telegram"));
        person.setRegister(rs.getTimestamp("pers_register"));
        person.setConfirmed(rs.getBoolean  ("pers_confirm"));
        Optional.ofNullable(rs.getInt("gend_id")).ifPresent(g -> {
            if(g == 0){
                person.setGender(Gender.FEMALE);
            }else{
                person.setGender(Gender.MALE);
            }
        });
//        pessoa.setPagou         (rs.getBoolean  ("pess_pagou"));
//        pessoa.setCerveja       (rs.getBoolean  ("pess_cerveja"));
//        pessoa.setRefrigerante  (rs.getBoolean  ("pess_refrigerante"));
//        pessoa.setEnergetico    (rs.getBoolean  ("pess_energetico"));
//        pessoa.setSuco          (rs.getBoolean  ("pess_suco"));
//        pessoa.setIce           (rs.getBoolean  ("pess_ice"));
//        pessoa.setToddynho      (rs.getBoolean  ("pess_toddynho"));
//        pessoa.setAguaCoco      (rs.getBoolean  ("pess_coco"));

//        Long quarId = rs.getLong("quar_id");
//        Bedroom q = null;
//        if(quarId != null){
//            q = new Bedroom();
//            q.setId(quarId);
//        }
//        pessoa.setBedroom(q);
        return person;
    }

}