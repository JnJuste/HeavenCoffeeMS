package heavencoffee.HeavenCoffeeRestaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error404")
public class ErrorController {
    @GetMapping
    public String ErrorForm(){
        return "error404";
    }
}