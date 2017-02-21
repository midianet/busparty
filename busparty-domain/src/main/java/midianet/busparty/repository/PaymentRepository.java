package midianet.busparty.repository;

import midianet.busparty.domain.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository Payment entity
 */
@Repository
public interface PaymentRepository extends CrudRepository<Payment,Long> {

}
