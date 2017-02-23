package midianet.latinoware.api.bussines;

import midianet.latinoware.api.model.Person;
import midianet.latinoware.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaBussines {

    @Autowired
    private PessoaRepository repository;

    public Optional<Person> findById(final Long id){
        return repository.findById(id);
    }

    public Optional<Person> findByTelegram(final Long id){
        return repository.findByTelegram(id);
    }

    public List<Person> listAll(){
        return repository.listAll();
    }

    @Transactional
    public void save(final Person pessoa){
        if(pessoa.getId() == null){
            pessoa.setRegister(new Date());
            repository.insert(pessoa);
        }else{

        }
    }

    @Transactional
    public void updateAccordance(final Person pessoa){
        repository.updateAccordance(pessoa);
    }

//    @Transactional
//    public void updateBebidas(Person pessoa){
//        repository.updateBebidas(pessoa);
//    }

}