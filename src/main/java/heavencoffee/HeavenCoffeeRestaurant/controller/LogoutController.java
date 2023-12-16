package heavencoffee.HeavenCoffeeRestaurant.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // Invalidate the current session
        request.getSession().invalidate();

        // Redirect to the login page with a logout message
        return "redirect:/loginUser?logout=true";
    }
}
