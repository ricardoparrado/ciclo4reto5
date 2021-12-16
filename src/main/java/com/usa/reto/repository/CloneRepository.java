package com.usa.reto.repository;

import com.usa.reto.interfaces.CloneCrudInterface;
import com.usa.reto.model.Clone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Ricardo Parrado Forero
 */

@Repository
public class CloneRepository {

    @Autowired
    private CloneCrudInterface cloneCrudInterface;

    public List<Clone> getAll() {
        return (List<Clone>) cloneCrudInterface.findAll();
    }

    public Optional<Clone> getClone(Integer id) {
        return cloneCrudInterface.findById(id);
    }

    public Clone create(Clone clone) {
        return cloneCrudInterface.save(clone);
    }

    public Clone save(Clone clone) {
        return cloneCrudInterface.save(clone);
    }

    public void update(Clone clone) {
        cloneCrudInterface.save(clone);
    }

    public void delete(Clone clone) {
        cloneCrudInterface.delete(clone);
    }


    //Reto 5
    public List<Clone> clonesByPrice(double precio) {
        return cloneCrudInterface.findByPriceLessThanEqual(precio);
    }

    public List<Clone> findByDescriptionLike(String description) {
        return cloneCrudInterface.findByDescriptionLike(description);
    }
}
