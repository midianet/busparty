package midianet.busparty.repository;

import midianet.busparty.exception.InfraException;
import midianet.busparty.domain.Payment;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by marcos-fc on 09/06/16.
 */
@Repository
public class PaymentRepository {

    private Logger log = Logger.getLogger(getClass());

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public PaymentRepository(final JdbcTemplate template) {
        jdbcInsert = new SimpleJdbcInsert(template)
                .withTableName("tb_payment")
                .usingColumns("paym_date",
                              "paym_ammount",
                              "pers_id")
                .usingGeneratedKeyColumns("paym_id");
    }

//    public void insert(final Payment pagamento){
//        try {
//            final Map params = new HashMap();
//            params.put("paym_date", pagamento.getDate());
//            params.put("paym_ammount", pagamento.getAmmount());
//            params.put("pers_id", pagamento.getPerson().getId());
//            pagamento.setId(jdbcInsert.executeAndReturnKey(params).longValue());
//        } catch (Exception e) {
//            log.error(e);
//            throw new InfraException(e);
//        }
//    }

//    public void update(final Payment pagamento){
//        final StringBuilder sql = new StringBuilder();
//        sql.append("update tb_pagamento set ")
//                .append("       paga_data  = :date,")
//                .append("       paga_valor = :value ")
//                .append("       pess_id    = :idPessoa ")
//                .append(" where paga_id    = :id");
//        final Map<String, Object> param = new HashMap();
//        param.put("date", pagamento.getDate());
//        param.put("value", pagamento.getAmmount());
//        param.put("idPessoa", pagamento.getPerson().getId());
//        param.put("id", pagamento.getId());
//        try {
//            jdbc.update(sql.toString(), param);
//        } catch (Exception e) {
//            log.error(e);
//            throw new InfraException(e);
//        }
//    }

//    public void delete(final Long id){
//        final String sql = "delete tb_pagamento where paga_id = :id";
//        final Map<String, Object> param = new HashMap();
//        param.put("id", id);
//        try {
//            jdbc.update(sql.toString(), param);
//        } catch (Exception e) {
//            log.error(e);
//            throw new InfraException(e);
//        }
//
//    }

    public List<Payment> listDoneByPerson(final Long id){
        final StringBuilder sql = new StringBuilder();
        sql.append("select paym_id,")
           .append("       paym_date,")
           .append("       paym_ammount,")
           .append("       paym_done")
           .append("  from tb_payment ")
           .append(" where pers_id = :id")
           .append("   and paym_done is not null")
           .append("  order by paym_date");
        try {
            final Map<String, Object> param = new HashMap();
            param.put("id", id);
            return jdbc.query(sql.toString(), param, this::mapRow);
        } catch (Exception e) {
            log.error(e);
            throw new InfraException(e);
        }
    }

    private Payment mapRow(final ResultSet rs, final int i) throws SQLException {
        final Payment pay = new Payment();
        pay.setId     (rs.getLong      ("paym_id"));
        pay.setDate   (rs.getDate      ("paym_date"));
        pay.setDone   (rs.getDate      ("paym_done"));
        pay.setAmmount(rs.getDouble    ("paym_ammount"));
        return pay;
    }

}