package pt.iade.joaquimclaudio.server.models.repositories;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.boot.context.properties.bind.Name;
import pt.iade.joaquimclaudio.server.models.Person;

import java.util.ArrayList;

public class PersonRepository {
    private static ArrayList<Person> peopleList = new ArrayList<Person>();

    public static void populate(){
        peopleList.add(new Person("Joaquim", "joaquim@gmail.com", 177));
    }

    public static ArrayList<Person> getPeopleList() {
        return peopleList;
    }

    public static Person getPersonById(int id){
        for (Person p : peopleList){
            if (p.getId() == id){
                return p;
            }
        }
        return null;
    }

    public static Person addPerson(@org.jetbrains.annotations.NotNull Person newPerson){
        Person person = new Person(newPerson.getName(), newPerson.getEmail(), newPerson.getHeight());
        peopleList.add(person);
        return person;
    }
}
