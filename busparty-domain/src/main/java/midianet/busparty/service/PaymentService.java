package midianet.busparty.service;

import midianet.busparty.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service of payment
 */
@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

}