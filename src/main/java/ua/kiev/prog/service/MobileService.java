package ua.kiev.prog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.dao.Brand;
import ua.kiev.prog.dao.Mobile;
import ua.kiev.prog.dao.MobileStatus;
import ua.kiev.prog.dao.impl.MobileRepository;

import java.util.List;

@Service
public class MobileService {
    @Autowired
    private MobileRepository mobileRepository;

    @Transactional
    public void addMobile(Mobile mobile) {
        mobileRepository.save(mobile);
    }

    @Transactional
    public void saveMobile(Mobile mobile) {
        mobileRepository.save(mobile);
    }

    @Transactional
    public void deleteMobiles(long[] idList) {
        for (long id : idList)
            mobileRepository.deleteById(id);
    }

    @Transactional
    public Mobile findMobileById(long id) {
        return mobileRepository.getOne(id);
    }

    @Transactional
    public List<Mobile> findMobilesById(long id) {
        return mobileRepository.findMobilesById(id);
    }

    @Transactional(readOnly = true)
    public List<Mobile> findAll() {
        return mobileRepository.findAll();
    }

    @Transactional
    public List<Mobile> findByBrand(Brand brand) {
        return mobileRepository.findByBrand(brand);
    }

    @Transactional
    public List<Mobile> findNewMobiles() {
        return mobileRepository.findMobilesByStatus(MobileStatus.NEWMOBILE);
    }

    @Transactional
    public List<Mobile> findMobilesByPattern(String pattern) {
        return mobileRepository.findMobilesByNameContains(pattern);
    }

}