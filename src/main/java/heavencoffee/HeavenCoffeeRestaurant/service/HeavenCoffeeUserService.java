package heavencoffee.HeavenCoffeeRestaurant.service;

import heavencoffee.HeavenCoffeeRestaurant.model.HeavenCoffeeUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeavenCoffeeUserService {
    List<HeavenCoffeeUser> findAllHeavenCoffeeUsers();
    HeavenCoffeeUser saveHeavenCoffeeUser(HeavenCoffeeUser heavenCoffeeUser);
}
