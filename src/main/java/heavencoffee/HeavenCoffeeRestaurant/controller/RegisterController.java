package heavencoffee.HeavenCoffeeRestaurant.controller;

import heavencoffee.HeavenCoffeeRestaurant.model.HeavenCoffeeUser;
import heavencoffee.HeavenCoffeeRestaurant.service.EmailService;
import heavencoffee.HeavenCoffeeRestaurant.service.HeavenCoffeeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/registerUser")
public class RegisterController {

    private final HeavenCoffeeUserService heavenCoffeeUserService;
    private final EmailService emailService;
    @Autowired
    public RegisterController(HeavenCoffeeUserService heavenCoffeeUserService, EmailService emailService) {
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
        return "redirect:/registerUser";
    }

    @GetMapping
    public String createUserForm(Model model){
        HeavenCoffeeUser heavenCoffeeUser = new HeavenCoffeeUser();
        model.addAttribute("heavenCoffeeUser", heavenCoffeeUser);
        return "Register/Register";
    }

    //Save a new User
    @PostMapping("/new")
    public String saveUser(@ModelAttribute("heavenCoffeeUser") HeavenCoffeeUser heavenCoffeeUser) {
        heavenCoffeeUserService.saveHeavenCoffeeUser(heavenCoffeeUser);
        return "redirect:/registerUser";
    }
}
