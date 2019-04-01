package ua.kiev.prog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.kiev.prog.dao.BasketStatus;
import ua.kiev.prog.dao.Card;
import ua.kiev.prog.dao.CustomUser;
import ua.kiev.prog.dao.OrderStatus;
import ua.kiev.prog.service.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private BasketService basketService;
    @Autowired
    private CardService cardService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/office")
    public String getOffice(Model model) {
        CustomUser dbUser = getCustomUser();
        model.addAttribute("basket", basketService.findBasketByUserAndStatus(getCustomUser(), BasketStatus.NOT_PAID));
        model.addAttribute("brands", brandService.findBrands());
        model.addAttribute("user", dbUser);
        model.addAttribute("UAH", cardService.findCardByNameAndUs("UAH", dbUser));
        model.addAttribute("cash", cardService.findCardByNameAndUs("cash", dbUser));
        return "office";
    }

    @GetMapping("/update")
    public String updateData(RedirectAttributes redir) {
        redir.addFlashAttribute("update", true);
        return "redirect:/user/office";
    }

    @PostMapping("/update")
    public String updateDataUser(@RequestParam String email,
                                 @RequestParam String phone,
                                 @RequestParam String address) {
        CustomUser dbUser = getCustomUser();
        dbUser.setEmail(email);
        dbUser.setPhone(phone);
        dbUser.setAddress(address);
        userService.save(dbUser);
        return "redirect:/user/office";
    }

    @GetMapping("/orders")
    public String getUsOrders(Model model) {
        model.addAttribute("basket", basketService.findBasketByUserAndStatus(getCustomUser(), BasketStatus.NOT_PAID));
        model.addAttribute("brands", brandService.findBrands());
        model.addAttribute("user", getCustomUser());
        model.addAttribute("executed", orderService.findByUserAndStatus(getCustomUser(), OrderStatus.SOLD));
        model.addAttribute("notexecuted", orderService.findByUserAndStatus(getCustomUser(), OrderStatus.NOTFULFILLED));
        return "user_orders";
    }

    @GetMapping("/cash")
    public String refillAccount(RedirectAttributes redir) {
        redir.addFlashAttribute("cashback", true);
        return "redirect:/user/office";
    }

    @PostMapping("/cash")
    public String refill(RedirectAttributes redir,
                         @RequestParam String quanto) {
        if (!isNumeric(quanto)) {
            redir.addFlashAttribute("cashback", true);
            redir.addFlashAttribute("notnumber", true);
            return "redirect:/user/office";
        }
        double quantoMoney = Double.valueOf(quanto);
        CustomUser dbUser = getCustomUser();
        Card card = cardService.findCardByNameAndUs("UAH", dbUser);
        card.setMoney(card.getMoney() + quantoMoney);
        cardService.saveCard(card);
        return "redirect:/user/office";
    }

    private static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private CustomUser getCustomUser() {
        CustomUser dbUser = null;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();
        dbUser = userService.getUserByLogin(login);
        return dbUser;
    }
}
