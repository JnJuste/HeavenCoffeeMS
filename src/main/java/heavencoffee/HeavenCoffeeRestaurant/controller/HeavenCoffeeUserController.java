package heavencoffee.HeavenCoffeeRestaurant.controller;

import heavencoffee.HeavenCoffeeRestaurant.model.HeavenCoffeeUser;
import heavencoffee.HeavenCoffeeRestaurant.service.EmailService;
import heavencoffee.HeavenCoffeeRestaurant.service.HeavenCoffeeUserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    //List all User
    @GetMapping
    public String createUserForm(Model model){
        HeavenCoffeeUser heavenCoffeeUser = new HeavenCoffeeUser();
        List<HeavenCoffeeUser> heavenCoffeeUsers = heavenCoffeeUserService.findAllHeavenCoffeeUsers();
        model.addAttribute("heavenCoffeeUsers", heavenCoffeeUsers);
        model.addAttribute("heavenCoffeeUser", heavenCoffeeUser);

        return "HeavenCoffeeUser/HeavenCoffeeUser";
    }

    @PostMapping("/new")
    public String saveNewUser(@ModelAttribute("heavenCoffeeUser") HeavenCoffeeUser heavenCoffeeUser) {
        try {
            // Set the password and save the user
            heavenCoffeeUser.setPassword(heavenCoffeeUser.getPassword());
            heavenCoffeeUserService.saveHeavenCoffeeUser(heavenCoffeeUser);

            // Extract the full name from the HeavenCoffeeUser object
            String fullName = heavenCoffeeUser.getFullNames();

            // Send the email using the EmailService
            sendUserEmail(heavenCoffeeUser.getEmail(), fullName);

            // Log successful registration
            Logger.getLogger(RegisterController.class.getName()).info("User Registered/Saved Successfully");

            // Redirect to the confirmation page after successful user registration
            return "redirect:/heavenCoffeeUsers";
        } catch (Exception ex) {
            // Log the exception or handle it appropriately
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, "Failed to save user", ex);
            return "redirect:/heavenCoffeeUsers?registrationFailed";
        }
    }

    private void sendUserEmail(String toEmail, String fullName) {
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
                    "Management Team<br/>" +
                    "Heaven Coffee Restaurant</p>" +
                    "</body></html>", fullName);
            // Use the EmailService to send the email
            emailService.sendEmail(toEmail, subject, body);
        } catch (MessagingException ex) {
            // Log the exception or handle it appropriately
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, "Failed to send verification email", ex);
        }
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
