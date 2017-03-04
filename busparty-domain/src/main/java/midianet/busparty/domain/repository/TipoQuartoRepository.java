package midianet.busparty.domain.repository;

import midianet.busparty.domain.exception.InfraException;
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
 * Created by marcosfernandocosta on 21/09/16.
 */
@Repository
public class TipoQuartoRepository {
//    private Logger log = Logger.getLogger(QuartoRepository.class);
//
//    @Autowired
//    private NamedParameterJdbcTemplate jdbc;
//
//    public Optional<RoomType> findById(final Long id){
//        final StringBuilder sql = new StringBuilder();
//        sql.append("select quat_id,")
//                .append("       quat_descricao,")
//                .append("       quat_valor ")
//                .append("  from tb_quarto_tipo ")
//                .append(" where quat_id = :id");
//        final Map<String,Object> param = new HashMap();
//        param.put("id",id);
//        try {
//            return Optional.ofNullable(jdbc.queryForObject(sql.toString(), param, this::mapRow));
//        }catch(EmptyResultDataAccessException e){
//            return Optional.empty();
//        }catch(Exception e){
//            log.error(e);
//            throw new InfraException(e);
//        }
//    }
//
//    private RoomType mapRow(final ResultSet rs, final int i) throws SQLException {
//        final RoomType t = new RoomType();
//        t.setId(rs.getLong("quat_id"));
//        t.setDescription(rs.getString("quat_descricao"));
//        t.setAmmount(rs.getDouble("quat_valor"));
//        return t;
//    }
}
