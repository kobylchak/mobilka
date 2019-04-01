package ua.kiev.prog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.kiev.prog.dao.Brand;
import ua.kiev.prog.dao.Mobile;
import ua.kiev.prog.service.BrandService;
import ua.kiev.prog.service.MobilePhoneService;
import ua.kiev.prog.service.MobileService;

import java.util.List;
@Controller
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    private MobileService mobileService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private MobilePhoneService mobilePhoneService;

    @GetMapping
    public String getStatistic(Model model) {
        model.addAttribute("brands", brandService.findBrands());
        model.addAttribute("mobiles", mobileService.findAll());
        return "statistic";
    }

    @GetMapping("/{brand.id}")
    public String getStatisticByBrand(Model model,
                                      @PathVariable(value = "brand.id") long brandId) {
        Brand brand = brandService.findBrandById(brandId);
        List<Mobile> mobiles = mobileService.findByBrand(brand);
        model.addAttribute("brands", brandService.findBrands());
        model.addAttribute("mobiles", mobiles);
        return "statistic";
    }
}
