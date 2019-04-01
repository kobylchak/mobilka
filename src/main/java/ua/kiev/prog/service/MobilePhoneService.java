package ua.kiev.prog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.dao.Mobile;
import ua.kiev.prog.dao.MobilePhone;
import ua.kiev.prog.dao.PhoneStatus;
import ua.kiev.prog.dao.impl.MobilePhoneRepository;

import java.util.List;

@Service
public class MobilePhoneService {
    @Autowired
    private MobilePhoneRepository mobilePhoneRepository;

    @Transactional
    public void saveMobilePhone(MobilePhone mobilePhone) {
        mobilePhoneRepository.save(mobilePhone);
    }

    @Transactional
    public MobilePhone findMobilePhoneById(long id) {
        return mobilePhoneRepository.findById(id);
    }

    @Transactional
    public List<MobilePhone> findAll() {
        return mobilePhoneRepository.findAll();
    }

    @Transactional
    public MobilePhone getFirstMobilePhoneByMobileAndStatus(Mobile mobile, PhoneStatus status) {
        return mobilePhoneRepository.getFirstByMobileAndStatus(mobile, status);
    }

    @Transactional
    public List<MobilePhone> findByBasketIsNullAndMobile(Mobile mobile) {
        return mobilePhoneRepository.findByBasketIsNullAndMobile(mobile);
    }

    @Transactional
    public List<MobilePhone> findPhonesByStatus(PhoneStatus status) {
        return mobilePhoneRepository.findMobilePhonesByStatus(status);
    }

    @Transactional
    public List<MobilePhone> findPhoneByImei(String imei) {
        return mobilePhoneRepository.findMobilePhoneByImei(imei);
    }

}
