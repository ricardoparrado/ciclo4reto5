package com.usa.reto.interfaces;

import com.usa.reto.model.Clone;
import com.usa.reto.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author Ricardo Parrado Forero
 */

public interface CloneCrudInterface extends MongoRepository<Clone, Integer> {

    //Reto 5
    public List<Clone> findByPriceLessThanEqual(double precio);

    @Query("{'description':{'$regex':'?0','$options':'i'}}")
    public List<Clone> findByDescriptionLike(String description);
}


