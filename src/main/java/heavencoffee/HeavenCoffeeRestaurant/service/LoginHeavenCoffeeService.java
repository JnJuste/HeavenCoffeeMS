package heavencoffee.HeavenCoffeeRestaurant.service;

import org.springframework.stereotype.Service;

@Service
public interface LoginHeavenCoffeeService {
    boolean authenticateUser(String email, String password);
    boolean doesEmailExist(String email);
    String getUserRole(String email);
}
