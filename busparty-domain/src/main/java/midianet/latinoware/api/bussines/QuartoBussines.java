package midianet.latinoware.api.bussines;

import midianet.latinoware.api.model.Bedroom;
import midianet.latinoware.api.repository.PessoaRepository;
import midianet.latinoware.api.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by marcosfernandocosta on 21/09/16.
 */
@Service
public class QuartoBussines {

    @Autowired
    private QuartoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Optional<Bedroom> findById(final Long id){
        return repository.findById(id);
    }

    public Optional<Bedroom> ocuppationById(final Long id){
        final Optional<Bedroom> quarto = repository.findById(id);
        quarto.ifPresent(q -> q.setOccupants(pessoaRepository.listAll()
                    .stream().filter(p -> p.getBedroom().getId().equals(q.getId()))
                    .collect(Collectors.toList())));
        return quarto;
    }
}
