package ua.kiev.prog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.dao.CustomUser;
import ua.kiev.prog.dao.Order;
import ua.kiev.prog.dao.OrderStatus;
import ua.kiev.prog.dao.impl.OrderRepository;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Transactional
    public Order findById(long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public List<Order> findOrderById(long id) {
        return orderRepository.findOrderById(id);
    }

    @Transactional
    public List<Order> findOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public List<Order> findOrdersByUser(CustomUser user) {
        return orderRepository.findOrdersByUser(user);
    }

    @Transactional
    public List<Order> findOrdersByStatus(OrderStatus status) {
        return orderRepository.findOrdersByStatus(status);
    }

    @Transactional
    public List<Order> findReturnedOrders() {
        return orderRepository.findOrdersByStatus(OrderStatus.RETURNED);
    }

    @Transactional
    public List<Order> findByUserAndStatus(CustomUser user, OrderStatus status) {
        return orderRepository.findByUserAndStatus(user, status);
    }

}
