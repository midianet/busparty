package midianet.busparty.service;

import midianet.busparty.repository.BedroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Services of Bedroom
 */

@Service
public class BedroomService {

    @Autowired
    private BedroomRepository repository;


}