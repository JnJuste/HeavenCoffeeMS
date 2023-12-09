package heavencoffee.HeavenCoffeeRestaurant.service;

import heavencoffee.HeavenCoffeeRestaurant.model.HeavenCoffeeUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HeavenCoffeeUserService {
    List<HeavenCoffeeUser> findAllHeavenCoffeeUsers();
    HeavenCoffeeUser saveHeavenCoffeeUser(HeavenCoffeeUser heavenCoffeeUser);
    HeavenCoffeeUser findUserById(UUID userId);
    HeavenCoffeeUser updateHeavenCoffeeUser(UUID userId, HeavenCoffeeUser updatedHeavenCoffeeUser);
    void deleteHeavenCoffeeUser(UUID userId);
}
