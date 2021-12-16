package com.usa.reto.repository;


import com.usa.reto.interfaces.OrderCrudInterface;
import com.usa.reto.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * @author Ricardo Parrado Forero
 */

@Repository
public class OrderRepository {

    @Autowired
    private OrderCrudInterface orderCrudInterface;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Order> getAll() {
        return (List<Order>) orderCrudInterface.findAll();
    }

    public Optional<Order> getOrder(int id) {
        return orderCrudInterface.findById(id);
    }

    public Order create(Order order) {
        return orderCrudInterface.save(order);
    }

    public void update(Order order) {
        orderCrudInterface.save(order);
    }

    public void delete(Order order) {
        orderCrudInterface.delete(order);
    }

    public Optional<Order> lastUserId() {
        return orderCrudInterface.findTopByOrderByIdDesc();
    }

    public List<Order> findByZone(String zona) {
        return orderCrudInterface.findByZone(zona);
    }

    //Reto 4: Ordenes de un asesor
    public List<Order> ordersSalesManByID(Integer id) {
        Query query = new Query();

        Criteria criterio = Criteria.where("salesMan.id").is(id);
        query.addCriteria(criterio);

        List<Order> orders = (List<Order>) mongoTemplate.find(query, Order.class);

        return orders;

    }

    //Reto 4: Ordenes de un asesor x Estado
    public List<Order> ordersSalesManByState(String state, Integer id) {
        Query query = new Query();
        Criteria criterio = Criteria.where("salesMan.id").is(id)
                .and("status").is(state);

        query.addCriteria(criterio);

        List<Order> orders = mongoTemplate.find(query,Order.class);

        return orders;
    }

    //Reto 4: Ordenes de un asesor x Fecha
    public List<Order> ordersSalesManByDate(String dateStr, Integer id) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Query query = new Query();

        Criteria dateCriteria = Criteria.where("registerDay")
                .gte(LocalDate.parse(dateStr, dtf).minusDays(1).atStartOfDay())
                .lt(LocalDate.parse(dateStr, dtf).plusDays(1).atStartOfDay())
                .and("salesMan.id").is(id);

        query.addCriteria(dateCriteria);

        List<Order> orders = mongoTemplate.find(query,Order.class);

        return orders;
    }
}
