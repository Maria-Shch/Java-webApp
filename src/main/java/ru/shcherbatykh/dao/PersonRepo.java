package ru.shcherbatykh.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.shcherbatykh.models.Person;

public interface PersonRepo extends CrudRepository<Person, Integer>{
    
}
