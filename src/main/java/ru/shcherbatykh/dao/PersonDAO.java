package ru.shcherbatykh.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import ru.shcherbatykh.models.Person;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;
    
    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "dima"));
        people.add(new Person(++PEOPLE_COUNT, "nastya"));
        people.add(new Person(++PEOPLE_COUNT, "maria"));
        people.add(new Person(++PEOPLE_COUNT, "rustam"));
    }
    
    public List<Person> index(){
        return people;
    }
    
    public Person show(int id){
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }
    
    public void save(Person person){
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }
    
    public void update(int id, Person updatedPerson){
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(updatedPerson.getName());
    }
    
    public void delete(int id){
        people.removeIf(p -> p.getId()==id);
    }
}
