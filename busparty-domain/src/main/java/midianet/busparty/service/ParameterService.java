package midianet.busparty.service;

import midianet.busparty.domain.Parameter;
import midianet.busparty.repository.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service of parameter
 */
@Service
public class ParameterService {

    @Autowired
    private ParameterRepository repository;

}
