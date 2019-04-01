package ua.kiev.prog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.kiev.prog.dao.*;
import ua.kiev.prog.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/basket")
public class BasketController {

    @Autowired
    private MobileService mobileService;
    @Autowired
    private BasketService basketService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private MobilePhoneService mobilePhoneService;
    @Autowired
    private CardService cardService;

    @GetMapping("/show/{basket.id}")
    public String showBasket(Model model,
                             @PathVariable(value = "basket.id") Basket basket) {
        if (basket.getPhones().isEmpty()) model.addAttribute("emptybasket", true);
        model.addAttribute("basket", basket);
        return "basket";
    }

    @GetMapping("/{mobile.id}")
    public String addMobileToBasket(Model model,
                                    @PathVariable(value = "mobile.id") Mobile mobile) {
        if (checkAbsentMobilePhones(mobile)) return "redirect:/";
        putPhoneToBasket(mobile);
        return "redirect:/";
    }

    @GetMapping("/buy/{mobile.id}")
    public String buyMobile(Model model,
                            @PathVariable(value = "mobile.id") Mobile mobile) {
        if (checkAbsentMobilePhones(mobile)) return "redirect:/";
        Basket basket = basketService.findBasketByUserAndStatus(getCustomUser(), BasketStatus.NOT_PAID);
        putPhoneToBasket(mobile);
        return "redirect:/basket/show/" + basket.getId();
    }

    @GetMapping("/delete/{phone.id}")
    public String deleteMobileFromBasket(Model model,
                                         @PathVariable(value = "phone.id") long mobilePhoneId) {
        MobilePhone phone = mobilePhoneService.findMobilePhoneById(mobilePhoneId);
        phone.setStatus(PhoneStatus.FORSALE);
        mobilePhoneService.saveMobilePhone(phone);
        Basket basket = basketService.findBasketByUserAndStatus(getCustomUser(), BasketStatus.NOT_PAID);
        basket.getPhones().remove(phone);
        basket.setTotalPrice(basket.countTotalPrice());
        phone.setBasket(null);
        basket.setTotalQuantity(basket.countTotalQuantity());
        basketService.saveBasket(basket);
        model.addAttribute("basket", basket);
        return "basket";
    }

    @GetMapping("/buy")
    public String showBuy(RedirectAttributes redir) {
        Basket basket = basketService.findBasketByName(createBasketName());
        if (basket.getPhones().isEmpty()) return "redirect:/";
        return "basket_buy_page";
    }

    @PostMapping("/buy")
    public String buyBasket(@RequestParam String delMethod,
                            @RequestParam String delAddress,
                            @RequestParam String phone,
                            HttpServletRequest request,
                            RedirectAttributes redir
    ) {
        CustomUser dbUser = getCustomUser();
        Basket basket = basketService.findBasketByName(createBasketName());
        Card uah = cardService.findCardByNameAndUs("UAH", getCustomUser());
        Card cashback = cardService.findCardByNameAndUs("cash", getCustomUser());
        if (basket.countTotalPrice() > (uah.getMoney() + cashback.getMoney())) { //basket > all money
            redir.addFlashAttribute("notEnoughMoney", true);
            return "redirect:/basket/show/" + basket.getId();
        } else if (cashback.getMoney() >= basket.getTotalPrice()) { //cashback > basket
            payByCashback(cashback, basket.countTotalPrice());
        } else {                                                   //all
            pay(uah, cashback, basket.getTotalPrice());
        }
        basket.setStatus(BasketStatus.PAID);
        subtractionSoldPhones(basket);
        String ip = request.getRemoteAddr();
        Order order = new Order(dbUser, basket, delMethod, delAddress, ip, OrderStatus.NOTFULFILLED);
        orderService.saveOrder(order);
        basketService.saveBasket(basket);
        updateUserData(dbUser, delAddress, phone);
        createNewBasket();
        return "redirect:/";
    }

    private void putPhoneToBasket(Mobile mobile) {
        MobilePhone mobilePhone = mobilePhoneService.getFirstMobilePhoneByMobileAndStatus(mobile, PhoneStatus.FORSALE);
        Basket basket = basketService.findBasketByUserAndStatus(getCustomUser(), BasketStatus.NOT_PAID);
        mobilePhone.setStatus(PhoneStatus.INBASKET);
        basket.getPhones().add(mobilePhone);
        mobilePhone.setBasket(basket);
        mobilePhoneService.saveMobilePhone(mobilePhone);
        basket.setTotalPrice(basket.countTotalPrice());
        basket.setTotalQuantity(basket.countTotalQuantity());
        basketService.saveBasket(basket);
    }

    private void subtractionSoldPhones(Basket basket) {
        for (MobilePhone phone : basket.getPhones()) {
            phone.getMobile().delete();
            phone.setStatus(PhoneStatus.SOLD);
            mobilePhoneService.saveMobilePhone(phone);
        }
    }

    private void updateUserData(CustomUser dbUser, String delAddress, String phone) {
        dbUser.setBasketNumber(dbUser.getBasketNumber() + 1);
        dbUser.setAddress(delAddress);
        dbUser.setPhone(phone);
        userService.updateUser(dbUser);
    }

    private CustomUser getCustomUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();
        return userService.getUserByLogin(login);
    }

    private void createNewBasket() {
        Basket bas = new Basket(createBasketName(), getCustomUser());
        basketService.saveBasket(bas);
    }

    private String createBasketName() {
        CustomUser dbUser = getCustomUser();
        return dbUser.getLogin() + "Basket" + dbUser.getBasketNumber();
    }

    private boolean checkAbsentMobilePhones(Mobile mobile) {
        List<MobilePhone> mobilePhones = mobilePhoneService.findByBasketIsNullAndMobile(mobile);
        return mobilePhones.isEmpty();
    }

    private void pay(Card uah, Card cashback, double basketMoney) {
        double remainsPay = basketMoney - cashback.getMoney();
        uah.setMoney(uah.getMoney() - remainsPay);
        cardService.saveCard(uah);
        double cashBonus = (basketMoney * 5.00) / 100.00;
        cashback.setMoney(cashBonus);
        cardService.saveCard(cashback);
    }

    private void payByCashback(Card cashback, double basketMoney) {
        cashback.setMoney(cashback.getMoney() - basketMoney);
        double cashBonus = (basketMoney * 5.00) / 100.00;
        cashback.setMoney(cashback.getMoney() + cashBonus);
        cardService.saveCard(cashback);
    }
}