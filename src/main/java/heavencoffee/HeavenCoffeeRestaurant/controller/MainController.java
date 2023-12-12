package heavencoffee.HeavenCoffeeRestaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("index")
public class MainController {
    @GetMapping
    public String showHomePage(){
        return "index";
    }

    @GetMapping("indexAdmin")
    public String AdminDash(){
        return "indexAdmin";
    }
}
