package midianet.busparty.domain.bussines;

import midianet.busparty.domain.model.Person;
import midianet.busparty.domain.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonBussines {

    @Autowired
    private PersonRepository repository;

//    public Optional<Person> findById(final Long id){
//        return repository.findById(id);
//    }
//
    public Optional<Person> findByTelegram(final Long id){
        return repository.findByTelegram(id);
    }

    public List<Person> listAll(){
        return repository.listAll();
    }

    public List<Person> listConfirmed(){
        return repository.listAll().stream().filter(p -> p.isConfirmed()).collect(Collectors.toList());
    }

    public List<Person> listWaiting(){
        return repository.listAll().stream().filter(p -> !p.isConfirmed()).collect(Collectors.toList());
    }

    @Transactional
    public void save(final Person pessoa){
        if(pessoa.getId() == null){
            pessoa.setRegister(new Date());
            repository.insert(pessoa);
        }
    }

//    @Transactional
//    public void updateAccordance(final Person pessoa){
//        repository.updateAccordance(pessoa);
//    }

//    @Transactional
//    public void updateBebidas(Person pessoa){
//        repository.updateBebidas(pessoa);
//    }

}