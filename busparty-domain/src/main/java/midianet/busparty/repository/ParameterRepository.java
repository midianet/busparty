package midianet.busparty.repository;

import midianet.busparty.domain.Parameter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository Parameter entity
 */
@Repository
public interface ParameterRepository extends CrudRepository<Parameter,String> {

}