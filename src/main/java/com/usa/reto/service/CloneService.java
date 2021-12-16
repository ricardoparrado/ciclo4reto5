package com.usa.reto.service;

import com.usa.reto.model.Clone;
import com.usa.reto.repository.CloneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Ricardo Parrado Forero
 */

@Service
public class CloneService {


    @Autowired
    private CloneRepository cloneRepository;

    public List<Clone> getAll() {
        return cloneRepository.getAll();
    }

    public Optional<Clone> getCloneById(Integer id) {
        return cloneRepository.getClone(id);
    }

    public Clone create (Clone clone) {
        if (clone.getBrand() == null || clone.getProcesor() == null ||
                clone.getOs() == null || clone.getDescription() == null ||
                clone.getMemory() == null || clone.getHardDrive() == null ||
                clone.getPhotography() == null) {
            return clone;
        } else {
            Optional<Clone> cloneExists = cloneRepository.getClone(clone.getId());
            if (cloneExists.isEmpty()) {
                return cloneRepository.save(clone);
            } else {
                return clone;
            }
        }
    }

    public Clone update(Clone clone) {
        if (clone.getId() != null) {
            Optional<Clone> cloneExist = cloneRepository.getClone(clone.getId());
            if (cloneExist.isPresent()) {
                if (clone.getBrand() != null) {
                    cloneExist.get().setBrand(clone.getBrand());
                }
                if (clone.getProcesor() != null) {
                    cloneExist.get().setProcesor(clone.getProcesor());
                }
                if (clone.getOs() != null) {
                    cloneExist.get().setOs(clone.getOs());
                }
                if (clone.getDescription() != null) {
                    cloneExist.get().setDescription(clone.getDescription());
                }
                if (clone.getMemory() != null) {
                    cloneExist.get().setMemory(clone.getMemory());
                }
                if (clone.getHardDrive() != null) {
                    cloneExist.get().setHardDrive(clone.getHardDrive());
                }
                if (clone.getPrice() != 0.0) {
                    cloneExist.get().setPrice(clone.getPrice());
                }
                if (clone.getQuantity() != 0) {
                    cloneExist.get().setQuantity(clone.getQuantity());
                }
                if (clone.getPhotography() != null) {
                    cloneExist.get().setPhotography(clone.getPhotography());
                }
                return cloneRepository.save(cloneExist.get());
            } else {
                return clone;
            }
        } else {
            return clone;
        }
    }

    public boolean delete(Integer id) {
        boolean aBoolean = cloneRepository.getClone(id).map(clone -> {
            cloneRepository.delete(clone);
            return true;
        }).orElse(false);
        return false;
    }

    //Reto 5
    public List<Clone> clonesByPrice(double price) {
        return cloneRepository.clonesByPrice(price);
    }

    public List<Clone> findByDescriptionLike(String description) {
        return cloneRepository.findByDescriptionLike(description);
    }

}
