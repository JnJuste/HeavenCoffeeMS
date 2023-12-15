package heavencoffee.HeavenCoffeeRestaurant.service;

import org.springframework.stereotype.Service;

@Service
public interface LoginHeavenCoffeeService {
    boolean authenticateUser(String email, String password);
    String getUserRole(String email);
}
