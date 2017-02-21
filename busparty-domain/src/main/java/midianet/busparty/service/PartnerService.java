package midianet.busparty.service;

import midianet.busparty.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service of partner
 */
@Service
public class PartnerService {

    @Autowired
    private PartnerRepository repository;

}
