package ua.kiev.prog.dao.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.prog.dao.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Brand findById(long id);

    Brand findByName(String name);

}
