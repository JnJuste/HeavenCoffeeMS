package heavencoffee.HeavenCoffeeRestaurant.service;

import heavencoffee.HeavenCoffeeRestaurant.model.HeavenCoffeeUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface HeavenCoffeeLoginService {
    Optional<HeavenCoffeeUser> authenticateUser(String email, String password, String requiredRole);

}
