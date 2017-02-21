package midianet.busparty.repository;

import midianet.busparty.domain.Partner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository Partner entity
 */
@Repository
public interface PartnerRepository extends CrudRepository<Partner,Long> {

    /**
     * Return one partner by id
     * @param id id bedroom
     * @return Optional of Partner
     */
    Optional<Partner> findById(Long id);

    /**
     * Return one partner by telegram
     * @param id id telegram
     * @return Optional of Partner
     */
    Optional<Partner> findByTelegram(String id);

}
