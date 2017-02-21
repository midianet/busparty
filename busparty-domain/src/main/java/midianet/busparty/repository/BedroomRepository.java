package midianet.busparty.repository;

import midianet.busparty.domain.Bedroom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository Bedroom entity
 */
@Repository
public interface BedroomRepository extends CrudRepository<Bedroom,Long> {
    /**
     * Return one bedroom by id
     * @param id id bedroom
     * @return Optional of Bedroom
     */
    Optional<Bedroom> findById(Long id);
}