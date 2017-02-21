package midianet.busparty.service;

import com.google.common.collect.Lists;
import midianet.busparty.domain.Partner;
import midianet.busparty.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service of partner
 */
@Service
public class PartnerService {

    @Autowired
    private PartnerRepository repository;

    public Optional<Partner> finById(final Long id){
        return repository.findById(id);
    }

    public Optional<Partner> findByTelegram(final String id){
        return repository.findByTelegram(id);
    }

    public List<Partner> listAll(){
        return Lists.newArrayList(repository.findAll());
    }

    @Transactional
    public void save(final Partner partner){
        repository.save(partner);
    }

    @Transactional
    public void delete(final Partner partner){
        repository.delete(partner);
    }

}