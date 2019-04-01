package ua.kiev.prog.dao.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.prog.dao.Brand;
import ua.kiev.prog.dao.Mobile;
import ua.kiev.prog.dao.MobileStatus;

import java.util.List;

public interface MobileRepository extends JpaRepository<Mobile, Long> {

    List<Mobile> findMobilesByNameContains(String pattern);

    List<Mobile> findMobilesById(long id);

    List<Mobile> findByBrand(Brand brand);

    List<Mobile> findMobilesByStatus(MobileStatus status);

    void deleteById(long id);

}