package ua.kiev.prog.dao.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.prog.dao.Brand;
import ua.kiev.prog.dao.Mobile;
import ua.kiev.prog.dao.MobilePhone;
import ua.kiev.prog.dao.PhoneStatus;

import java.util.List;

public interface MobilePhoneRepository extends JpaRepository<MobilePhone, Long> {

    MobilePhone findById(long id);

    MobilePhone getFirstByMobileAndStatus(Mobile mobile, PhoneStatus status);

    List<MobilePhone> findMobilePhonesByStatus(PhoneStatus status);

    List<MobilePhone> findByBasketIsNullAndMobile(Mobile mobile);

    List<MobilePhone> findMobilePhoneByImei(String imei);

    long countByStatus(PhoneStatus status);

    long countByStatusAndMobileBrand(PhoneStatus status, Brand brand);

    long countByStatusAndMobile(PhoneStatus status, Mobile mobile);

}
