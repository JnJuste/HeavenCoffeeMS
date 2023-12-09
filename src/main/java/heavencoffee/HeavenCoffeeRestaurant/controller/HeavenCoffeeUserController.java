package heavencoffee.HeavenCoffeeRestaurant.controller;

import heavencoffee.HeavenCoffeeRestaurant.model.HeavenCoffeeUser;
import heavencoffee.HeavenCoffeeRestaurant.service.EmailService;
import heavencoffee.HeavenCoffeeRestaurant.service.HeavenCoffeeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/heavenCoffeeUsers")
public class HeavenCoffeeUserController {
    private final HeavenCoffeeUserService heavenCoffeeUserService;
    private final EmailService emailService;

    @Autowired
    public HeavenCoffeeUserController(HeavenCoffeeUserService heavenCoffeeUserService, EmailService emailService){
        this.heavenCoffeeUserService = heavenCoffeeUserService;
        this.emailService = emailService;
    }

    // Send email to the registered users
    @GetMapping("/email")
    public String sendEmail(){
        String toEmail = "jeanjusteirakoze@gmail.com";
        String subjectEmail = "Account Created Successful ";
        String bodyEmail = "HELLO, Dear User Your Account has been created. You can now login!";
        emailService.sendEmail(toEmail, subjectEmail, bodyEmail);
        return "redirect:/heavenCoffeeUsers";
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
        return "redirect:/heavenCoffeeUsers";
    }


    //Find User by ID
    @GetMapping("/{userId}/edit")
    public String editUserForm(@PathVariable UUID userId, Model model) {
        HeavenCoffeeUser heavenCoffeeUser = heavenCoffeeUserService.findUserById(userId);
        model.addAttribute("heavenCoffeeUser", heavenCoffeeUser);
        model.addAttribute("userId", userId); // Add this line to pass ItemId to the view
        return "HeavenCoffeeUser/EditHeavenCoffeeUser";
    }


    // Update User
    @PostMapping("/{userId}/update")
    public String updateUser(@PathVariable UUID userId, @ModelAttribute("heavenCoffeeUser") HeavenCoffeeUser updatedheavenCoffeeUser, RedirectAttributes redirectAttributes) {
        try {
            heavenCoffeeUserService.updateHeavenCoffeeUser(userId, updatedheavenCoffeeUser);
            return "redirect:/heavenCoffeeUsers";
        } catch (RuntimeException e) {
            // Handle the exception (log it or add an error message to the redirect attributes)
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/heavenCoffeeUsers";
        }
    }


    //Delete User
    @GetMapping("/{userId}/delete")
    public String deleteUser(@PathVariable UUID userId) {
        heavenCoffeeUserService.deleteHeavenCoffeeUser(userId);
        return "redirect:/heavenCoffeeUsers";
    }
}
