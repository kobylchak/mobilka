package ua.kiev.prog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.kiev.prog.dao.*;
import ua.kiev.prog.service.*;

import java.util.List;

@Controller
public class MyController {

    @Autowired
    private UserService userService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private MobileService mobileService;
    @Autowired
    private BasketService basketService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CardService cardService;

    @RequestMapping("/")
    public String index(Model model) {
        if (isAdmin()) return "redirect:/admin/orders/notfulfilled";
        Basket basket = basketService.findBasketByUserAndStatus(getCustomUser(), BasketStatus.NOT_PAID);
        model.addAttribute("basket", basket);
        model.addAttribute("user", getCustomUser());
        model.addAttribute("login", getLogin());
        model.addAttribute("brands", brandService.findBrands());
        model.addAttribute("mobiles", mobileService.findAll());
        return "index";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        return "unauthorized";
    }

    private boolean isAdmin() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();
        return login.equals("admin");
    }

    private String getLogin() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }

    @GetMapping("/admin")
    public String getOrders(Model model) {
        List<Order> orders = orderService.findOrders();
        model.addAttribute("orders", orders);
        return "admin";
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    public String update(@RequestParam String first,
                         @RequestParam String last,
                         @RequestParam String login,
                         @RequestParam String email,
                         @RequestParam String password,
                         RedirectAttributes redir,
                         Model model) {
        if (userService.existsByLogin(login)) {
            redir.addFlashAttribute("exists", true);
            return "redirect:/login";
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passHash = passwordEncoder.encode(password);
        CustomUser dbUser = new CustomUser(login, passHash, UserRole.USER, first, last, email);
        userService.addUser(dbUser);
        String basketName = login + "Basket" + dbUser.getBasketNumber();
        Basket basket = new Basket(basketName, dbUser);
        basketService.saveBasket(basket);
        createCards(dbUser);
        return "redirect:/";
    }

    @GetMapping("/{brand.id}")
    public String getBrandMobiles(Model model,
                                  @PathVariable(value = "brand.id") Brand brand) {
        Basket basket = basketService.findBasketByUserAndStatus(getCustomUser(), BasketStatus.NOT_PAID);
        model.addAttribute("basket", basket);
        model.addAttribute("user", getCustomUser());
        model.addAttribute("brands", brandService.findBrands());
        model.addAttribute("mobiles", mobileService.findByBrand(brand));
        return "index";
    }

    @GetMapping("/new")
    public String getNewMobiles(Model model) {
        List<Mobile> mobiles = mobileService.findNewMobiles();
        model.addAttribute("user", getCustomUser());
        model.addAttribute("brands", brandService.findBrands());
        model.addAttribute("mobiles", mobiles);
        model.addAttribute("basket", basketService.findBasketByUserAndStatus(getCustomUser(), BasketStatus.NOT_PAID));
        return "index";
    }

    @PostMapping("/find")
    public String findMobile(Model model,
                             @RequestParam String pattern) {
        model.addAttribute("brands", brandService.findBrands());
        model.addAttribute("user", getCustomUser());
        model.addAttribute("mobiles", mobileService.findMobilesByPattern(pattern));
        model.addAttribute("basket", basketService.findBasketByUserAndStatus(getCustomUser(), BasketStatus.NOT_PAID));
        return "index";
    }

    @GetMapping("/contacts")
    public String getContacts(Model model) {
        CustomUser dbUser = getCustomUser();
        model.addAttribute("brands", brandService.findBrands());
        model.addAttribute("user", dbUser);
        model.addAttribute("basket", basketService.findBasketByUserAndStatus(getCustomUser(), BasketStatus.NOT_PAID));
        return "contacts";
    }

    private CustomUser getCustomUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getUserByLogin(user.getUsername());
    }

    private void createCards(CustomUser dbUser) {
        Card cardUAH = new Card("UAH", 0, dbUser);
        Card cashBack = new Card("cash", 0, dbUser);
        cardService.saveCard(cardUAH);
        cardService.saveCard(cashBack);
    }
}
