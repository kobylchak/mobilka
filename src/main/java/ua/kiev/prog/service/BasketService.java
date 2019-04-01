package ua.kiev.prog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.dao.Basket;
import ua.kiev.prog.dao.BasketStatus;
import ua.kiev.prog.dao.CustomUser;
import ua.kiev.prog.dao.impl.BasketRepository;

import java.util.List;

@Service
public class BasketService {
    @Autowired
    private BasketRepository basketRepository;

    @Transactional
    public void saveBasket(Basket basket) {
        basketRepository.save(basket);
    }

    @Transactional
    public Basket findBasketByName(String name) {
        return basketRepository.findByName(name);
    }

    @Transactional
    public Basket findBasketById(long basketId) {
        return basketRepository.findBasketById(basketId);
    }

    @Transactional
    public List<Basket> findBasketsById(long basketId) {
        return basketRepository.findBasketsById(basketId);
    }

    @Transactional
    public List<Basket> findBaskets() {
        return basketRepository.findAll();
    }

    @Transactional
    public Basket findBasketByUserAndStatus(CustomUser user, BasketStatus status) {
        return basketRepository.findBasketByUsAndStatus(user, status);
    }

    @Transactional
    public List<Basket> findBasketsByStatus(BasketStatus status) {
        return basketRepository.findBasketByStatus(status);
    }

}

