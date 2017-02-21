package midianet.busparty.service;

import com.google.common.collect.Lists;
import midianet.busparty.domain.Bedroom;
import midianet.busparty.repository.BedroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Services of Bedroom
 */

@Service
public class BedroomService {

    @Autowired
    private BedroomRepository repository;

    /**
     * Return one bedroom by id
     * @param id id bedroom
     * @return Optional of Bedroom
     */
    public Optional<Bedroom> finById(final Long id){
        return repository.findById(id);
    }

    /**
     * Return all bedroom's
     * @return List<Bedroom> list of Bedroom
     */
    public List<Bedroom> listAll(){
        return Lists.newArrayList(repository.findAll());
    }

    @Transactional
    public void save(final Bedroom bedroom){
        repository.save(bedroom);
    }

    @Transactional
    public void delete(final Bedroom bedroom){
        repository.delete(bedroom);
    }

}