package ua.kiev.prog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.dao.Brand;
import ua.kiev.prog.dao.impl.BrandRepository;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Transactional
    public void addBrand(Brand brand) {
        brandRepository.save(brand);
    }

    @Transactional
    public Brand findBrandById(long id) {
        return brandRepository.findById(id);
    }

    @Transactional()
    public List<Brand> findBrands() {
        return brandRepository.findAll();
    }
}
