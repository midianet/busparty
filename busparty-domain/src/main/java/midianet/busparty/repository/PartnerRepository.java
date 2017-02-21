package midianet.busparty.repository;

import midianet.busparty.domain.Partner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository Partner entity
 */
@Repository
public interface PartnerRepository extends CrudRepository<Partner,Long> {

}
