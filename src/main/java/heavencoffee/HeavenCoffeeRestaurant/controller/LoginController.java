package heavencoffee.HeavenCoffeeRestaurant.controller;

import heavencoffee.HeavenCoffeeRestaurant.model.HeavenCoffeeUser;
import heavencoffee.HeavenCoffeeRestaurant.service.HeavenCoffeeLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;



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


}
