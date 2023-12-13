package heavencoffee.HeavenCoffeeRestaurant.service;

import org.springframework.stereotype.Service;

@Service
public interface HeavenCoffeeLoginService {
    boolean authenticate(String email, String password);
}
