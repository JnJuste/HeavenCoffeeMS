package heavencoffee.HeavenCoffeeRestaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardStaffController {
    @GetMapping("indexStaff")
    public String AdminDash(){
        return "indexStaff";
    }
}
