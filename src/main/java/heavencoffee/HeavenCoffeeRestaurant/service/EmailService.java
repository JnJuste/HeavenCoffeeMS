package heavencoffee.HeavenCoffeeRestaurant.service;

import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendEmail(String toEmail, String subject, String body) throws MessagingException;
}
