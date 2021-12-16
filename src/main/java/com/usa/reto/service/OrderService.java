package com.usa.reto.service;

import com.usa.reto.model.Order;
import com.usa.reto.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Ricardo Parrado Forero
 */

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAll() {
        return orderRepository.getAll();
    }

    public Optional<Order> getOrder(int id) {
        return orderRepository.getOrder(id);
    }

    public Order create(Order order) {
        Optional<Order> orderIdMaxima = orderRepository.lastUserId();
        if (order.getId() == null) {
            if (orderIdMaxima.isEmpty()) {
                order.setId(1);
            }
            else {
                order.setId(orderIdMaxima.get().getId() + 1);
            }
        }

        Optional<Order> exist = orderRepository.getOrder(order.getId());
        if (exist.isEmpty()) {
            return orderRepository.create(order);
        } else {
            return order;
        }
    }

    public Order update(Order order) {

        if (order.getId() != null) {
            Optional<Order> orderDb = orderRepository.getOrder(order.getId());
            if (orderDb.isPresent()) {
                if (order.getStatus() != null) {
                    orderDb.get().setStatus(order.getStatus());
                }
                orderRepository.update(orderDb.get());
                return orderDb.get();
            } else {
                return order;
            }
        } else {
            return order;
        }
    }

    public boolean delete(int id) {
        Boolean aBoolean = getOrder(id).map(order -> {
            orderRepository.delete(order);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    //Ordenes de pedido asociadas con asesores de una zona
    public List<Order> findByZone(String zona) {
        return orderRepository.findByZone(zona);
    }

    //Reto4
    //Reto 4: Ordenes de un asesor
    public List<Order> ordersSalesManByID(Integer id){
        return orderRepository.ordersSalesManByID(id);
    }
    //Reto 4: Ordenes de un asesor por estado
    public List<Order> ordersSalesManByState(String state, Integer id){
        return orderRepository.ordersSalesManByState(state, id);
    }
    //Reto 4: Ordenes de un asesor por fecha
    public List<Order> ordersSalesManByDate(String dateStr, Integer id) {
        return orderRepository.ordersSalesManByDate(dateStr,id);
    }


}
