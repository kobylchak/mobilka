package ua.kiev.prog.dao.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.prog.dao.CustomUser;
import ua.kiev.prog.dao.Order;
import ua.kiev.prog.dao.OrderStatus;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findById(long id);

    List<Order> findOrderById(long id);

    List<Order> findOrdersByStatus(OrderStatus status);

    List<Order> findOrdersByUser(CustomUser user);

    List<Order> findByUserAndStatus(CustomUser user, OrderStatus status);

}