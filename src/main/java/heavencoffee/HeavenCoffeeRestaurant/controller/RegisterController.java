// UserController.java
package heavencoffee.HeavenCoffeeRestaurant.controller;

import heavencoffee.HeavenCoffeeRestaurant.model.HeavenCoffeeUser;
import heavencoffee.HeavenCoffeeRestaurant.service.EmailService;
import heavencoffee.HeavenCoffeeRestaurant.service.HeavenCoffeeUserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;
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

    @GetMapping
    public String createUserForm(Model model) {
        HeavenCoffeeUser heavenCoffeeUser = new HeavenCoffeeUser();
        model.addAttribute("heavenCoffeeUser", heavenCoffeeUser);
        return "Register/Register";
    }

    @PostMapping("/new")
    public String saveUser(@ModelAttribute("heavenCoffeeUser") HeavenCoffeeUser heavenCoffeeUser) {
        try {
            // Set the password and save the user
            heavenCoffeeUser.setPassword(heavenCoffeeUser.getPassword());
            heavenCoffeeUserService.saveHeavenCoffeeUser(heavenCoffeeUser);

            // Extract the full name from the HeavenCoffeeUser object
            String fullName = heavenCoffeeUser.getFullNames();

            // Send the email using the EmailService
            sendEmail(heavenCoffeeUser.getEmail(), fullName);

            // Log successful registration
            Logger.getLogger(RegisterController.class.getName()).info("User Registered/Saved Successfully");

            // Redirect to the confirmation page after successful user registration
            return "redirect:/registerUser/confirmation";
        } catch (Exception ex) {
            // Log the exception or handle it appropriately
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, "Failed to save user", ex);
            return "redirect:/registerUser?registrationFailed";
        }
    }

    private void sendEmail(String toEmail, String fullName) {
        try {
            String subject = "HeavenCoffee Registration Confirmation";
            String body = String.format("<html><body>" +
                    "<p>Dear %s,</p>" +
                    "<p>Welcome to Heaven Coffee Restaurant! Thank you for registering. " +
                    "Your role is crucial in managing and overseeing our operations.</p>" +
                    "<p>As a member, you have access to advanced functionalities and responsibilities. " +
                    "If you have any questions or need assistance, please don't hesitate to contact our management team.</p>" +
                    "<p>We appreciate your commitment to excellence and look forward to working together to make Heaven Coffee a great place for both our customers and our team.</p>" +
                    "<p>Best regards,<br/>" +
                    "Jean Juste IRAKOZE<br/>" +
                    "Manager<br/>" +
                    "Heaven Coffee Restaurant</p>" +
                    //"<p>Click the link below to verify your email:<br/>" +
                    "</body></html>", fullName);
            // Use the EmailService to send the email
            emailService.sendEmail(toEmail, subject, body);
        } catch (MessagingException ex) {
            // Log the exception or handle it appropriately
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, "Failed to send verification email", ex);
        }
    }

    @GetMapping("/confirmation")
    public String showConfirmationPage() {
        return "Register/Confirmation";
    }

}