package ua.kiev.prog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.dao.Card;
import ua.kiev.prog.dao.CustomUser;
import ua.kiev.prog.dao.impl.CardRepository;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    @Transactional
    public void saveCard(Card card) {
        cardRepository.save(card);
    }

    @Transactional
    public Card findCardByNameAndUs(String name, CustomUser user) {
        return cardRepository.findByNameAndCustomUser(name, user);
    }
}
