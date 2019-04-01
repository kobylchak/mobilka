package ua.kiev.prog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kiev.prog.dao.Brand;
import ua.kiev.prog.dao.Mobile;
import ua.kiev.prog.dao.MobilePhone;
import ua.kiev.prog.dao.PhoneStatus;
import ua.kiev.prog.service.BrandService;
import ua.kiev.prog.service.MobilePhoneService;
import ua.kiev.prog.service.MobileService;

import java.util.List;

@Controller
@RequestMapping("/mobilephone")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class MobilePhoneController {

    @Autowired
    private MobilePhoneService mobilePhoneService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private MobileService mobileService;

    @PostMapping
    public String addMobilePhone(Model model,
                                 @RequestParam(value = "mobile") long mobileId,
                                 @RequestParam String imei) {
        Mobile mobile = mobileService.findMobileById(mobileId);
        MobilePhone mobilePhone = new MobilePhone(imei, mobile);
        mobile.add();
        mobilePhoneService.saveMobilePhone(mobilePhone);
        List<MobilePhone> phones = mobilePhoneService.findAll();
        model.addAttribute("phones", phones);
        model.addAttribute("brands", brandService.findBrands());
        return "phone";
    }

    @GetMapping("/{brand.id}")
    public String addMobilePhone(Model model,
                                 @PathVariable(value = "brand.id") Brand brand) {
        List<Mobile> mobiles = mobileService.findByBrand(brand);
        model.addAttribute("mobiles", mobiles);
        return "mobilephone_add_page";
    }

    @GetMapping
    public String getPhones(Model model) {
        List<MobilePhone> phones = mobilePhoneService.findAll();
        model.addAttribute("phones", phones);
        model.addAttribute("brands", brandService.findBrands());
        return "phone";
    }

    @GetMapping("/description/{phone.id}")
    public String changeImeiPage(Model model,
                                 @PathVariable(value = "phone.id") long phoneId) {
        MobilePhone phone = mobilePhoneService.findMobilePhoneById(phoneId);
        model.addAttribute("phone", phone);
        return "mobilephone_change_imei";
    }

    @PostMapping("/description")
    public String changeImei(@RequestParam long phoneId,
                             @RequestParam String newImei) {
        MobilePhone phone = mobilePhoneService.findMobilePhoneById(phoneId);
        phone.setImei(newImei);
        mobilePhoneService.saveMobilePhone(phone);
        return "redirect:/mobilephone";
    }

    @GetMapping("/forsale")
    public String getForsalePhones(Model model) {
        List<MobilePhone> phones = mobilePhoneService.findPhonesByStatus(PhoneStatus.FORSALE);
        model.addAttribute("brands", brandService.findBrands());
        model.addAttribute("phones", phones);
        return "phone";
    }

    @GetMapping("/inbasket")
    public String getInBasketPhones(Model model) {
        List<MobilePhone> phones = mobilePhoneService.findPhonesByStatus(PhoneStatus.INBASKET);
        model.addAttribute("brands", brandService.findBrands());
        model.addAttribute("phones", phones);
        return "phone";
    }

    @GetMapping("/sold")
    public String getSoldPhones(Model model) {
        List<MobilePhone> phones = mobilePhoneService.findPhonesByStatus(PhoneStatus.SOLD);
        model.addAttribute("brands", brandService.findBrands());
        model.addAttribute("fishka", true);
        model.addAttribute("phones", phones);
        return "phone";
    }

    @GetMapping("/returned")
    public String getReturnedPhones(Model model) {
        List<MobilePhone> phones = mobilePhoneService.findPhonesByStatus(PhoneStatus.RETURNED);
        model.addAttribute("brands", brandService.findBrands());
        model.addAttribute("fishka", true);
        model.addAttribute("phones", phones);
        return "phone";
    }

    @PostMapping("/imei")
    public String findPhoneByImei(Model model,
                                  @RequestParam(required = false, defaultValue = "0") String imei) {
        List<MobilePhone> phones = mobilePhoneService.findPhoneByImei(imei);
        model.addAttribute("phones", phones);
        model.addAttribute("brands", brandService.findBrands());
        return "phone";
    }

    @GetMapping("/status/change")
    public String getPosibilitiToChangeStatus(Model model) {
        List<MobilePhone> phones = mobilePhoneService.findPhonesByStatus(PhoneStatus.RETURNED);
        List<MobilePhone> soldPhones = mobilePhoneService.findPhonesByStatus(PhoneStatus.SOLD);
        phones.addAll(soldPhones);
        if (phones.isEmpty()) model.addAttribute("SoldAndReturnedPhonesMissing", true);
        model.addAttribute("phones", phones);
        model.addAttribute("brands", brandService.findBrands());
        model.addAttribute("changeStatus", true);
        return "phone";
    }

    @GetMapping("/status/{phone.id}")
    public String getChangeStatusPage(Model model,
                                      @PathVariable(value = "phone.id") long phoneId) {
        MobilePhone phone = mobilePhoneService.findMobilePhoneById(phoneId);
        model.addAttribute("phone", phone);
        return "phone_change_status";
    }

    @PostMapping("/status")
    public String changeStatusPage(@RequestParam String status,
                                   @RequestParam long phoneId) {
        MobilePhone phone = mobilePhoneService.findMobilePhoneById(phoneId);
        PhoneStatus phoneStatus = PhoneStatus.valueOf(status);
        phone.setStatus(phoneStatus);
        mobilePhoneService.saveMobilePhone(phone);
        return "phone";
    }
}
