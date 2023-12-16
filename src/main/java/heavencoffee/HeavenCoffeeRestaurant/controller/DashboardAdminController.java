package heavencoffee.HeavenCoffeeRestaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class DashboardAdminController {
    @GetMapping("indexAdmin")
    public String adminDash(){
        return "indexAdmin";
    }
}
