package ua.kiev.prog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kiev.prog.dao.Brand;
import ua.kiev.prog.dao.Mobile;
import ua.kiev.prog.service.BrandService;
import ua.kiev.prog.service.MobileService;

import java.util.List;

@Controller
@RequestMapping("/brand")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class BrandController {

    @Autowired
    private MobileService mobileService;
    @Autowired
    private BrandService brandService;

    @GetMapping
    public String brandAddPage() {
        return "brand_add_page";
    }

    @PostMapping
    public String brandAdd(@RequestParam String name) {
        if (checkExistBrandName(name)) return "redirect:/mobile";
        brandService.addBrand(new Brand(name));
        return "redirect:/mobile";
    }

    @GetMapping("/{id}")
    public String findMobilesByBrand(@PathVariable(value = "id") Brand brand,
                                     Model model) {
        List<Mobile> mobiles = mobileService.findByBrand(brand);
        model.addAttribute("brands", brandService.findBrands());
        model.addAttribute("mobiles", mobiles);
        model.addAttribute("brandId", brand.getId());
        return "mobile";
    }

    private boolean checkExistBrandName(String name) {
        long countSameName = brandService.findBrands().stream()
                .filter(brand -> brand.checkName(name))
                .count();
        return countSameName != 0;
    }
}
