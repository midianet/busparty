package midianet.latinoware.api.repository;

import midianet.latinoware.api.exception.InfraException;
import midianet.latinoware.api.model.Person;
import midianet.latinoware.api.model.Bedroom;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@Repository
public class PessoaRepository {

    private Logger log = Logger.getLogger(PessoaRepository.class);

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public PessoaRepository(final JdbcTemplate template) {
        jdbcInsert =  new SimpleJdbcInsert(template)
                            .withTableName("tb_pessoa")
                            .usingColumns("pess_nome",
                                          "tele_id",
                                          "pess_pagou",
                                          "pess_cerveja",
                                          "pess_refrigerante",
                                          "pess_suco",
                                          "pess_ice",
                                          "pess_toddynho",
                                          "pess_coco",
                                          "quar_id",
                                          "pess_cadastro")
                            .usingGeneratedKeyColumns("pess_id");
    }

    public void updateAccordance(final Person pessoa){
        final StringBuilder sql = new StringBuilder();
        sql.append("update tb_pessoa set ")
                .append("       pess_acordo = :acordo")
                .append(" where pess_id           = :id");
        final Map<String,Object> param = new HashMap();
       // param.put("acordo",       pessoa.isConcorda());
        param.put("id",           pessoa.getId());
        try {
            jdbc.update(sql.toString(), param);
        }catch(Exception e){
            log.error(e);
            throw new InfraException(e);
        }
    }


    public void updateBebidas(final Person pessoa){
        final StringBuilder sql = new StringBuilder();
        sql.append("update tb_pessoa set ")
           .append("       pess_cerveja      = :cerveja ,")
           .append("       pess_refrigerante = :refrigerante ,")
           .append("       pess_suco         = :suco ,")
           .append("       pess_energetico   = :energetico,")
           .append("       pess_ice          = :ice ,")
           .append("       pess_toddynho     = :toddynho ,")
           .append("       pess_coco         = :coco ")
           .append(" where pess_id           = :id");
        final Map<String,Object> param = new HashMap();
        param.put("name",         pessoa.getName());
//        param.put("cerveja",      pessoa.isCerveja());
//        param.put("refrigerante", pessoa.isRefrigerante());
//        param.put("suco",         pessoa.isSuco());
//        param.put("ice",          pessoa.isIce());
//        param.put("toddynho",     pessoa.isToddynho());
//        param.put("energetico",   pessoa.isEnergetico());
//        param.put("coco",         pessoa.isAguaCoco());
        param.put("id",           pessoa.getId());
        try {
            jdbc.update(sql.toString(), param);
        }catch(Exception e){
            log.error(e);
            throw new InfraException(e);
        }
    }

    public void insert(final Person pessoa){
        try {
            final Map params = new HashMap();
            params.put("pess_nome",    pessoa.getName());
            params.put("tele_id",      pessoa.getTelegram());
            params.put("pess_pagou", false);
            params.put("pess_cerveja", false);
            params.put("pess_refrigerente", false);
            params.put("pess_suco", false);
            params.put("pess_ice", false);
            params.put("pess_toddynho", false);
            params.put("pess_coco", false);
            params.put("quar_id",null);
            params.put("pess_cadastro", new Timestamp(new Date().getTime()));
            pessoa.setId(jdbcInsert.executeAndReturnKey(params).longValue());
        }catch(Exception e){
            log.error(e);
            throw new InfraException(e);
        }
    }

    public Optional<Person> findByTelegram(final Long id){
        final StringBuilder sql = new StringBuilder();
        sql.append("select pess_id,")
           .append("       pess_nome,")
           .append("       pess_cadastro,")
           .append("       pess_pagou,")
           .append("       pess_cerveja,")
           .append("       pess_refrigerante,")
           .append("       pess_energetico," )
           .append("       pess_suco,")
           .append("       pess_ice,")
           .append("       pess_toddynho,")
           .append("       pess_coco,")
           .append("       quar_id,")
           .append("       tele_id")
           .append("  from tb_pessoa ")
           .append(" where tele_id = :id");
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
        sql.append("select pess_id,")
                .append("       pess_nome,")
                .append("       pess_cadastro,")
                .append("       pess_pagou,")
                .append("       pess_cerveja,")
                .append("       pess_refrigerante,")
                .append("       pess_suco,")
                .append("       pess_ice,")
                .append("       pess_toddynho,")
                .append("       pess_coco,")
                .append("       quar_id,")
                .append("       tele_id")
                .append("  from tb_pessoa ")
                .append(" where pess_id = :id");
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
        sql.append("select pess_id,")
           .append("       pess_nome,")
           .append("       pess_cadastro,")
           .append("       pess_pagou,")
           .append("       quar_id,")
           .append("       pess_cerveja,")
           .append("       pess_refrigerante,")
           .append("       pess_energetico,")
           .append("       pess_suco,")
           .append("       pess_ice,")
           .append("       pess_toddynho,")
           .append("       pess_coco,")
           .append("       tele_id")
           .append("  from tb_pessoa ")
           .append("  order by pess_cadastro");
        try {
            return jdbc.query(sql.toString(), this::mapRow);
        }catch(Exception e){
            log.error(e);
            throw new InfraException(e);
        }
    }

    private Person mapRow(final ResultSet rs, final int i) throws SQLException {
        final Person pessoa = new Person();
        pessoa.setId            (rs.getLong     ("pess_id"));
        pessoa.setName(rs.getString   ("pess_nome"));
        pessoa.setRegister(rs.getTimestamp("pess_cadastro"));
//        pessoa.setPagou         (rs.getBoolean  ("pess_pagou"));
//        pessoa.setCerveja       (rs.getBoolean  ("pess_cerveja"));
//        pessoa.setRefrigerante  (rs.getBoolean  ("pess_refrigerante"));
//        pessoa.setEnergetico    (rs.getBoolean  ("pess_energetico"));
//        pessoa.setSuco          (rs.getBoolean  ("pess_suco"));
//        pessoa.setIce           (rs.getBoolean  ("pess_ice"));
//        pessoa.setToddynho      (rs.getBoolean  ("pess_toddynho"));
//        pessoa.setAguaCoco      (rs.getBoolean  ("pess_coco"));
        pessoa.setTelegram(rs.getLong     ("tele_id"));
        Long quarId = rs.getLong("quar_id");
        Bedroom q = null;
        if(quarId != null){
            q = new Bedroom();
            q.setId(quarId);
        }
        pessoa.setBedroom(q);
        return pessoa;
    }

}