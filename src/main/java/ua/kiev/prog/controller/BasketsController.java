package ua.kiev.prog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kiev.prog.dao.Basket;
import ua.kiev.prog.dao.BasketStatus;
import ua.kiev.prog.dao.MobilePhone;
import ua.kiev.prog.dao.PhoneStatus;
import ua.kiev.prog.service.BasketService;
import ua.kiev.prog.service.MobilePhoneService;

import java.util.List;

@Controller
@RequestMapping("/baskets")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class BasketsController {

    @Autowired
    private BasketService basketService;
    @Autowired
    private MobilePhoneService mobilePhoneService;

    @GetMapping
    public String getBasketsPage(Model model) {
        model.addAttribute("baskets", basketService.findBaskets());
        return "baskets";
    }

    @PostMapping("/find")
    public String findBasketsById(Model model,
                                  @RequestParam(required = false, defaultValue = "0") long basketId) {
        List<Basket> baskets = basketService.findBasketsById(basketId);
        model.addAttribute("baskets", baskets);
        return "baskets";
    }

    @GetMapping("/find/{order.basket.id}")
    public String findBasketById(Model model,
                                 @PathVariable(value = "order.basket.id") long basketId) {
        List<Basket> baskets = basketService.findBasketsById(basketId);
        model.addAttribute("baskets", baskets);
        return "baskets";
    }

    @GetMapping("/paid")
    public String findPaidBaskets(Model model) {
        List<Basket> baskets = basketService.findBasketsByStatus(BasketStatus.PAID);
        model.addAttribute("baskets", baskets);
        return "baskets";
    }

    @GetMapping("/notpaid")
    public String findNotpaidBaskets(Model model) {
        List<Basket> baskets = basketService.findBasketsByStatus(BasketStatus.NOT_PAID);
        model.addAttribute("baskets", baskets);
        model.addAttribute("clearPosibility", true);
        return "baskets";
    }

    @GetMapping("/clear/{basket.id}")
    public String clearBasket(Model model,
                              @PathVariable(value = "basket.id") long basketId) {
        Basket basket = basketService.findBasketById(basketId);
        for (MobilePhone phone : basket.getPhones()) {
            phone.setStatus(PhoneStatus.FORSALE);
            phone.setBasket(null);
            mobilePhoneService.saveMobilePhone(phone);
        }
        basket.getPhones().clear();
        basket.setTotalPrice(basket.countTotalPrice());
        basket.setTotalQuantity(basket.countTotalQuantity());
        basketService.saveBasket(basket);
        return "baskets";
    }
}