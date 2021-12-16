package com.usa.reto.controller;

import com.usa.reto.model.Clone;
import com.usa.reto.service.CloneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Ricardo Parrado Forero
 */

@RestController
@RequestMapping("/api/clone")
@CrossOrigin("*")
public class CloneController {

    @Autowired
    private CloneService cloneService;

    @GetMapping("/all")
    public List<Clone> getAll() {
        return cloneService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Clone> getClone(@PathVariable("id") Integer id) {
        return cloneService.getCloneById(id);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Clone create(@RequestBody Clone clone) {
        return cloneService.create(clone);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Clone update(@RequestBody Clone clone) {
        return cloneService.update(clone);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") Integer id) {
        return cloneService.delete(id);
    }

    //Reto 5
    @GetMapping("/price/{price}")
    public List<Clone> clonesByPrice(@PathVariable("price") double precio) {
        return cloneService.clonesByPrice(precio);
    }

    @GetMapping("/description/{description}")
    public List<Clone> findByDescriptionLike(@PathVariable("description") String description) {
        return cloneService.findByDescriptionLike(description);
    }
}
