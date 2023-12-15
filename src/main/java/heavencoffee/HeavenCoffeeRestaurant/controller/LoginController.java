package heavencoffee.HeavenCoffeeRestaurant.controller;

import heavencoffee.HeavenCoffeeRestaurant.model.HeavenCoffeeUser;
import heavencoffee.HeavenCoffeeRestaurant.service.LoginHeavenCoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/loginUser")
public class LoginController{
    private final LoginHeavenCoffeeService loginHeavenCoffeeService;

    @Autowired
    public LoginController(LoginHeavenCoffeeService loginHeavenCoffeeService) {
        this.loginHeavenCoffeeService = loginHeavenCoffeeService;
    }

    @GetMapping
    public String showLoginForm(Model model) {
        model.addAttribute("heavenCoffeeUser", new HeavenCoffeeUser());
        return "Login/Login";
    }

    @PostMapping("/authenticate")
    public String authenticateUser(HeavenCoffeeUser heavenCoffeeUser, Model model) {
        String email = heavenCoffeeUser.getEmail();
        String password = heavenCoffeeUser.getPassword();

        // Authenticate the user
        boolean isAuthenticated = loginHeavenCoffeeService.authenticateUser(email, password);

        if (isAuthenticated) {
            // Retrieve user role
            String userRole = loginHeavenCoffeeService.getUserRole(email);

            // Redirect based on user role
            if ("ADMIN".equals(userRole)) {
                // Redirect to the admin page
                return "redirect:/indexAdmin";
            } else if ("STAFF".equals(userRole)) {
                // Redirect to the staff page
                return "redirect:/error404";
            } else {
                // Handle other roles or scenarios
                return "redirect:/index";
            }
        } else {
            // Handle failed authentication
            model.addAttribute("error", "Invalid credentials");
            return "Login/Login";
        }
    }
}
