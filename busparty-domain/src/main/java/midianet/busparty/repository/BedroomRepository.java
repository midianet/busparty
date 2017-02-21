package midianet.busparty.repository;

import midianet.busparty.domain.Bedroom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository Bedroom entity
 */
@Repository
public interface BedroomRepository extends CrudRepository<Bedroom,Long> {

}