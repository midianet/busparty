package midianet.busparty.domain.repository;

import midianet.busparty.domain.exception.InfraException;
import midianet.busparty.domain.model.Bedroom;
import midianet.busparty.domain.model.RoomType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
public class QuartoRepository {

    private Logger log = Logger.getLogger(QuartoRepository.class);

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    public Optional<Bedroom> findById(final Long id){
        final StringBuilder sql = new StringBuilder();
        sql.append("select q.quar_id,")
                .append("  q.quar_tipo,")
                .append("  q.quar_sexo, ")
                .append("  t.quat_descricao, ")
                .append("  t.quat_valor ")
                .append(" from tb_quarto q ")
                .append("inner join tb_quarto_tipo t ")
                .append("   on q.quar_tipo = t.quat_id ")
                .append("where q.quar_id = :id");
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

    private Bedroom mapRow(final ResultSet rs, final int i) throws SQLException {
        final Bedroom q = new Bedroom();
        q.setId(rs.getLong("quar_id"));
        final RoomType t = new RoomType();
        t.setId(rs.getLong("quar_tipo"));
        t.setDescription(rs.getString("quat_descricao"));
        t.setAmmount(rs.getDouble("quat_valor"));
        q.setType(t);
        //q.setSexo(rs.getInt("quar_sexo"));
        return q;
    }

}