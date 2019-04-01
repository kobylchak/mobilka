package ua.kiev.prog.dao.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.prog.dao.Basket;
import ua.kiev.prog.dao.BasketStatus;
import ua.kiev.prog.dao.CustomUser;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    Basket findBasketById(long id);

    List<Basket> findBasketsById(long id);

    Basket findBasketByUsAndStatus(CustomUser user, BasketStatus status);

    Basket findByName(String name);

    List<Basket> findBasketByStatus(BasketStatus status);

}
