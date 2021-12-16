package com.usa.reto.interfaces;

import com.usa.reto.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Ricardo Parrado Forero
 */

public interface UserCrudInterface extends MongoRepository <User, Integer> {

    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
    //Para seleccionar el usuario con el id mayor o último.
    Optional<User> findTopByOrderByIdDesc();

    //Reto 5
    List<User> findByMonthBirthtDay(String monthBirthtDay);


}
