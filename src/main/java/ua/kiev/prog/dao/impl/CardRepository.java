package ua.kiev.prog.dao.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.prog.dao.Card;
import ua.kiev.prog.dao.CustomUser;

public interface CardRepository extends JpaRepository<Card, Long> {

    Card findByNameAndCustomUser(String name, CustomUser user);

}
