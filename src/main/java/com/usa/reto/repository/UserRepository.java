package com.usa.reto.repository;

import com.usa.reto.interfaces.UserCrudInterface;
import com.usa.reto.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Ricardo Parrado Forero
 */

@Repository
public class UserRepository {

    @Autowired
    private UserCrudInterface userCrudInterface;

    public List<User> getAll(){
        return (List<User>) userCrudInterface.findAll();
    }

    public Optional<User> getUser(Integer id){
        return userCrudInterface.findById(id);
    }

    public User create (User user){
        return userCrudInterface.save(user);
    }

    public void update(User user) {
        userCrudInterface.save(user);
    }

    public void delete(User user) {
        userCrudInterface.delete(user);
    }

    public boolean emailExists(String email) {
        Optional<User> usuario = userCrudInterface.findByEmail(email);

        return usuario.isPresent();
    }

    public Optional<User> authenticateUser(String email, String password) {
        return userCrudInterface.findByEmailAndPassword(email, password);
    }


    public Optional<User> lastUserId() {
        return userCrudInterface.findTopByOrderByIdDesc();
    }

    //Reto 5
    public List<User> birthtDayList(String monthBirthtDay) {
        return userCrudInterface.findByMonthBirthtDay(monthBirthtDay);
    }
 }
