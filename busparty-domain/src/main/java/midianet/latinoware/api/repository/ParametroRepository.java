package midianet.latinoware.api.repository;

import midianet.latinoware.api.exception.InfraException;
import midianet.latinoware.api.model.Parameter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by marcos-fc on 09/06/16.
 */
@Repository
public class ParametroRepository {

    private Logger log = Logger.getLogger(PessoaRepository.class);

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public ParametroRepository(final JdbcTemplate template) {
        jdbcInsert =  new SimpleJdbcInsert(template)
                .withTableName("tb_parametro")
                .usingColumns("para_chave",
                              "para_descricao",
                              "para_valor")
                .usingGeneratedKeyColumns("para_id");
    }

    public Optional<Parameter> findByChave(final String chave){
        final StringBuilder sql = new StringBuilder();
        sql.append("select para_chave,")
                .append("       para_descricao,")
                .append("       para_valor ")
                .append("  from tb_parametro ")
                .append(" where para_chave = :key");
        final Map<String,Object> param = new HashMap();
        param.put("key",chave);
        try {
            return Optional.ofNullable(jdbc.queryForObject(sql.toString(),param, this::mapRow));
        }catch(Exception e){
            log.error(e);
            throw new InfraException(e);
        }
    }

    public void insert(final Parameter parametro){
        try {
            final Map params = new HashMap();
            params.put("para_chave",     parametro.getKey());
            params.put("para_descricao", parametro.getDescription());
            params.put("para_valor",     parametro.getValue());
            jdbcInsert.execute(params);
        }catch(Exception e){
            log.error(e);
            throw new InfraException(e);
        }
    }

    public void update(final Parameter parametro){
        final StringBuilder sql = new StringBuilder();
        sql.append("update tb_parametro set ")
                .append("       para_descricao  = :description,")
                .append("       para_valor = :value ")
                .append(" where para_chave    = :key");
        final Map<String,Object> param = new HashMap();
        param.put("description",parametro.getDescription());
        param.put("value",    parametro.getValue());
        param.put("key",       parametro.getKey());
        try {
            jdbc.update(sql.toString(), param);
        }catch(Exception e){
            log.error(e);
            throw new InfraException(e);
        }
    }

    public void delete(final String chave){
        final String sql = "delete tb_parametro where para_chave = :key";
        final Map<String,Object> param = new HashMap();
        param.put("key",chave);
        try {
            jdbc.update(sql.toString(), param);
        }catch(Exception e){
            log.error(e);
            throw new InfraException(e);
        }

    }

    private Parameter mapRow(final ResultSet rs, final int i) throws SQLException {
        final Parameter parametro = new Parameter();
        parametro.setKey(rs.getString   ("para_chave"));
        parametro.setDescription(rs.getString   ("para_descricao"));
        parametro.setValue(rs.getString   ("para_valor"));
        return parametro;
    }

}