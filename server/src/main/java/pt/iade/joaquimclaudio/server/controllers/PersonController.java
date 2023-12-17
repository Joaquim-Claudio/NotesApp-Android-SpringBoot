package pt.iade.joaquimclaudio.server.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.iade.joaquimclaudio.server.models.Person;
import pt.iade.joaquimclaudio.server.models.repositories.PersonRepository;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api/persons")
public class PersonController {
    private final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Person> getPeople(){
        logger.info("Sending all the people.");
        return PersonRepository.getPeopleList();
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person addPerson(@RequestBody Person person){
        logger.info("Adding person with name " + person.getName() + ".");
        return PersonRepository.addPerson(person);
    }
}
