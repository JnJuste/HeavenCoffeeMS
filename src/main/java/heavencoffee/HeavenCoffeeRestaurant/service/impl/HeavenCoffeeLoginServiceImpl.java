package heavencoffee.HeavenCoffeeRestaurant.service.impl;

import heavencoffee.HeavenCoffeeRestaurant.service.HeavenCoffeeLoginService;
import org.springframework.stereotype.Service;

@Service
public class HeavenCoffeeLoginServiceImpl implements HeavenCoffeeLoginService {
    @Override
    public boolean authenticate(String email, String password) {

        return true;
    }
}
