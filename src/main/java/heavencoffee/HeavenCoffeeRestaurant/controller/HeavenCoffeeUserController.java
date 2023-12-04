package heavencoffee.HeavenCoffeeRestaurant.controller;

import heavencoffee.HeavenCoffeeRestaurant.model.HeavenCoffeeUser;
import heavencoffee.HeavenCoffeeRestaurant.service.HeavenCoffeeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class HeavenCoffeeUserController {
    private final HeavenCoffeeUserService heavenCoffeeUserService;

    @Autowired
    public HeavenCoffeeUserController(HeavenCoffeeUserService heavenCoffeeUserService){
        this.heavenCoffeeUserService = heavenCoffeeUserService;
    }

    //List all User
    @GetMapping
    public String createUserForm(Model model){
        HeavenCoffeeUser heavenCoffeeUser = new HeavenCoffeeUser();
        List<HeavenCoffeeUser> heavenCoffeeUsers = heavenCoffeeUserService.findAllHeavenCoffeeUsers();
        model.addAttribute("heavenCoffeeUsers", heavenCoffeeUsers);
        model.addAttribute("heavenCoffeeUser", heavenCoffeeUser);
        return "HeavenCoffeeUser/HeavenCoffeeUser";
    }


    //Save a new User
    @PostMapping("/new")
    public String saveUser(@ModelAttribute("heavenCoffeeUser") HeavenCoffeeUser heavenCoffeeUser) {
        heavenCoffeeUserService.saveHeavenCoffeeUser(heavenCoffeeUser);
        return "redirect:/users";
    }

    //Find User by ID
    @GetMapping("/{userId}/edit")
    public String editUserForm(@PathVariable UUID userId, Model model) {
        HeavenCoffeeUser heavenCoffeeUser = heavenCoffeeUserService.findById(userId);
        model.addAttribute("heavenCoffeeUser", heavenCoffeeUser);
        model.addAttribute("userId", userId); // Add this line to pass ItemId to the view
        return "HeavenCoffeeUser/EditHeavenCoffeeUser";
    }


    // Update User
    @PostMapping("/{userId}/update")
    public String updateUser(@PathVariable UUID userId, @ModelAttribute("heavenCoffeeUser") HeavenCoffeeUser updatedheavenCoffeeUser, RedirectAttributes redirectAttributes) {
        try {
            heavenCoffeeUserService.updateHeavenCoffeeUser(userId, updatedheavenCoffeeUser);
            return "redirect:/users";
        } catch (RuntimeException e) {
            // Handle the exception (log it or add an error message to the redirect attributes)
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/users";
        }
    }


    //Delete User
    @GetMapping("/{orderId}/delete")
    public String deleteUser(@PathVariable UUID orderId) {
        heavenCoffeeUserService.deleteHeavenCoffeeUser(orderId);
        return "redirect:/users";
    }
}
