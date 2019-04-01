package ua.kiev.prog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ua.kiev.prog.dao.CustomUser;
import ua.kiev.prog.dao.UserRole;
import ua.kiev.prog.service.MobileService;
import ua.kiev.prog.service.UserService;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(final MobileService mobileService, final UserService userService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                userService.addUser(new CustomUser("admin", "$2a$08$.JUplF24HwIZW7XPgo.94uRQHYse2HGYNq4z.5nQllco7O.EyHSu.", UserRole.ADMIN, "admin@gmailcom"));
            }
        };
    }
}