package heavencoffee.HeavenCoffeeRestaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class AdminDashboardController {
    @GetMapping("indexAdmin")
    public String AdminDash(){
        return "indexAdmin";
    }
}
