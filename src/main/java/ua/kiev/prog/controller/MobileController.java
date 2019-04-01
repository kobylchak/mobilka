package ua.kiev.prog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kiev.prog.dao.Brand;
import ua.kiev.prog.dao.Mobile;
import ua.kiev.prog.dao.MobileStatus;
import ua.kiev.prog.service.BrandService;
import ua.kiev.prog.service.MobileService;

import java.util.List;

@Controller
@RequestMapping("/mobile")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class MobileController {

    @Autowired
    private BrandService brandService;
    @Autowired
    private MobileService mobileService;

    @GetMapping
    public String getMobiles(Model model
    ) {
        List<Mobile> mobiles = mobileService.findAll();
        model.addAttribute("brands", brandService.findBrands());
        model.addAttribute("mobiles", mobiles);
        return "mobile";
    }

    @GetMapping("/page")
    public String mobileAddPage(Model model) {
        model.addAttribute("brands", brandService.findBrands());
        return "mobile_add_page";
    }

    @PostMapping
    public String mobileAdd(@RequestParam(value = "brand") Brand brand,
                            Model model,
                            @RequestParam String name,
                            @RequestParam Double price,
                            @RequestParam String path,
                            @RequestParam String color,
                            @RequestParam String description,
                            @RequestParam String status,
                            @RequestParam int discount) {
        MobileStatus mobileStatus = MobileStatus.valueOf(status);
        Mobile mobile = new Mobile(brand, name, price, path, color, description, discount);
        mobile.setStatus(mobileStatus);
        mobileService.addMobile(mobile);
        model.addAttribute("brands", brandService.findBrands());
        model.addAttribute("mobiles", mobileService.findAll());
        return "mobile";
    }

    @GetMapping("/description/{mobile.id}")
    public String description(Model model,
                              @PathVariable(value = "mobile.id") long mobileId) {
        Mobile mobile = mobileService.findMobileById(mobileId);
        model.addAttribute("mobile", mobile);
        return "mobile_change_description";
    }

    @PostMapping("/description")
    public String changeDescription(Model model,
                                    @RequestParam long mobileId,
                                    @RequestParam String newDescription) {
        Mobile mobile = mobileService.findMobileById(mobileId);
        mobile.setDescription(newDescription);
        mobileService.saveMobile(mobile);
        List<Mobile> mobiles = mobileService.findAll();
        model.addAttribute("brands", brandService.findBrands());
        model.addAttribute("mobiles", mobiles);
        return "mobile";
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteMobile(@RequestParam(value = "toDo[]", required = false) long[] toDelete) {
        if (toDelete != null && toDelete.length > 0)
            mobileService.deleteMobiles(toDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/change_price")
    public ResponseEntity<Void> changePrice(@RequestParam(required = false) double newPrice,
                                            @RequestParam(value = "toDo[]", required = false) long[] toChange) {
        if (toChange != null && toChange.length > 0) {
            for (long id : toChange) {
                Mobile mobile = mobileService.findMobileById(id);
                mobile.setPrice(newPrice);
                mobileService.saveMobile(mobile);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/change_discount")
    public ResponseEntity<Void> changeDiscount(
            @RequestParam(required = false) int newDiscount,
            @RequestParam(value = "toDo[]", required = false) long[] toChange) {
        if (toChange != null && toChange.length > 0) {
            for (long id : toChange) {
                Mobile mobile = mobileService.findMobileById(id);
                mobile.setDiscount(newDiscount);
                mobileService.saveMobile(mobile);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{phone.mobile.id}")
    public String findMobileById(Model model,
                                 @PathVariable(value = "phone.mobile.id") long mobileId) {
        List<Mobile> mobiles = mobileService.findMobilesById(mobileId);
        model.addAttribute("mobiles", mobiles);
        return "mobile";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(Model model,
                         @RequestParam String pattern) {
        model.addAttribute("brands", brandService.findBrands());
        model.addAttribute("mobiles", mobileService.findMobilesByPattern(pattern));
        return "mobile";
    }
}
