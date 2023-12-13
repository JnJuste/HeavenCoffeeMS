package heavencoffee.HeavenCoffeeRestaurant.controller;

import heavencoffee.HeavenCoffeeRestaurant.model.HeavenCoffeeUser;
import heavencoffee.HeavenCoffeeRestaurant.service.HeavenCoffeeLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/loginUser")
public class LoginController{
    private final HeavenCoffeeLoginService heavenCoffeeLoginService;

    @Autowired
    public LoginController(HeavenCoffeeLoginService heavenCoffeeLoginService) {
        this.heavenCoffeeLoginService = heavenCoffeeLoginService;
    }

    @GetMapping
    public String showLoginForm(Model model) {
        model.addAttribute("heavenCoffeeUser", new HeavenCoffeeUser());
        return "Login/Login";
    }

    @PostMapping("/authenticate")
    public String authenticateUser(HeavenCoffeeUser heavenCoffeeUser) {
        try {
            // Perform authentication using the HeavenCoffeeLoginService
            boolean isAuthenticated = heavenCoffeeLoginService.authenticate(heavenCoffeeUser.getEmail(), heavenCoffeeUser.getPassword());

            if (isAuthenticated) {
                // Redirect to a dashboard or home page upon successful authentication
                return "redirect:/dashboard";
            } else {
                // Redirect back to the login page with an error message
                return "redirect:/login?error";
            }
        } catch (Exception ex) {
            // Log the exception or handle it appropriately
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, "Failed to authenticate user", ex);
            return "redirect:/login?error";
        }
    }
}
