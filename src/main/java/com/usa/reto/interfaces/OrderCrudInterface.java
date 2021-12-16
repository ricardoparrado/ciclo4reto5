package com.usa.reto.interfaces;

import com.usa.reto.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author Ricardo Parrado Forero
 */


public interface OrderCrudInterface extends MongoRepository<Order, Integer> {

    //Retorna ordenes de pedido que concidan con la zona ingresada
    @Query("{'salesMan.zone': ?0}")
    List<Order> findByZone(final String zone);

    //Retorna las ordenes por estado
    @Query("{status: ?0}")
    List<Order> findByStatus(final  String status);

    //Selecciona la orden con id máximo
    Optional<Order> findTopByOrderByIdDesc();


}
