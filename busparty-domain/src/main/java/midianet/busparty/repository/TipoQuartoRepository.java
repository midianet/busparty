package midianet.busparty.repository;

import org.springframework.stereotype.Repository;

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
