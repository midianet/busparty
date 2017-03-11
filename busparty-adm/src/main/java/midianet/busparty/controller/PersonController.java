package midianet.busparty.controller;

import com.google.common.collect.Lists;
import midianet.busparty.domain.Person;
import midianet.busparty.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Person>> listAll() {
        final List<Person> list = Lists.newArrayList(repository.listAll());
        if(list.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<MoradorRep> getById(@PathVariable("id") final Long id) {
//        final Morador current = repository.findOne(id);
//        if (current == null) {
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity(toRep(current), HttpStatus.OK);
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    @Transactional
//    public ResponseEntity<Void> create(@RequestBody final MoradorRep person, final UriComponentsBuilder ucBuilder){
//        person.setId(null); //return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        final Morador current = toEntity(person);
//        repository.save(current);
//        final HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(ucBuilder.path("/api/person/{id}").buildAndExpand(current.getId()).toUri());
//        return new ResponseEntity(headers, HttpStatus.CREATED);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    @Transactional
//    public ResponseEntity<MoradorRep> update(@PathVariable("id") final Long id, @RequestBody final MoradorRep person) {
//        final Morador current = repository.findOne(id);
//        if (current == null) {
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//        current.setNome(person.getNome());
//        repository.save(current);
//        return new ResponseEntity(toRep(current), HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @Transactional
//    public ResponseEntity<Void> delete(@PathVariable("id") final Long id) {
//        final Person p = repository.f .findOne(id);
//        if (current == null) {
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//        repository.delete(id);
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }

}