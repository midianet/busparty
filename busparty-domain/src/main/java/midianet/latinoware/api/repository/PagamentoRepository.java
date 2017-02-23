package midianet.latinoware.api.repository;

import midianet.latinoware.api.exception.InfraException;
import midianet.latinoware.api.model.Payment;
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
public class PagamentoRepository {

    private Logger log = Logger.getLogger(PessoaRepository.class);

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public PagamentoRepository(final JdbcTemplate template) {
        jdbcInsert = new SimpleJdbcInsert(template)
                .withTableName("tb_pagamento")
                .usingColumns("paga_data",
                        "paga_valor",
                        "pess_id")
                .usingGeneratedKeyColumns("paga_id");
    }

    public void insert(final Payment pagamento){
        try {
            final Map params = new HashMap();
            params.put("paga_data", pagamento.getDate());
            params.put("paga_valor", pagamento.getAmmount());
            params.put("pess_id", pagamento.getPerson().getId());
            pagamento.setId(jdbcInsert.executeAndReturnKey(params).longValue());
        } catch (Exception e) {
            log.error(e);
            throw new InfraException(e);
        }
    }

    public void update(final Payment pagamento){
        final StringBuilder sql = new StringBuilder();
        sql.append("update tb_pagamento set ")
                .append("       paga_data  = :date,")
                .append("       paga_valor = :value ")
                .append("       pess_id    = :idPessoa ")
                .append(" where paga_id    = :id");
        final Map<String, Object> param = new HashMap();
        param.put("date", pagamento.getDate());
        param.put("value", pagamento.getAmmount());
        param.put("idPessoa", pagamento.getPerson().getId());
        param.put("id", pagamento.getId());
        try {
            jdbc.update(sql.toString(), param);
        } catch (Exception e) {
            log.error(e);
            throw new InfraException(e);
        }
    }

    public void delete(final Long id){
        final String sql = "delete tb_pagamento where paga_id = :id";
        final Map<String, Object> param = new HashMap();
        param.put("id", id);
        try {
            jdbc.update(sql.toString(), param);
        } catch (Exception e) {
            log.error(e);
            throw new InfraException(e);
        }

    }

    public List<Payment> listByPessoa(final Long idPessoa){
        final StringBuilder sql = new StringBuilder();
        sql.append("select paga_id,")
                .append("       paga_data,")
                .append("       paga_valor ")
                .append("  from tb_pagamento ")
                .append(" where pess_id = :idPessoa")
                .append("  order by paga_data");
        try {
            final Map<String, Object> param = new HashMap();
            param.put("idPessoa", idPessoa);
            return jdbc.query(sql.toString(), param, this::mapRow);
        } catch (Exception e) {
            log.error(e);
            throw new InfraException(e);
        }
    }

    private Payment mapRow(final ResultSet rs, final int i) throws SQLException {
        final Payment pagamento = new Payment();
        pagamento.setId   (rs.getLong  ("paga_id"));
        pagamento.setDate(rs.getDate  ("paga_data"));
        pagamento.setAmmount(rs.getDouble("paga_valor"));
        return pagamento;
    }

}