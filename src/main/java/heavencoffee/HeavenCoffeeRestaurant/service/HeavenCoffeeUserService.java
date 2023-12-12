package heavencoffee.HeavenCoffeeRestaurant.service;

import heavencoffee.HeavenCoffeeRestaurant.model.HeavenCoffeeUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HeavenCoffeeUserService {
    List<HeavenCoffeeUser> findAllHeavenCoffeeUsers();
    void saveHeavenCoffeeUser(HeavenCoffeeUser heavenCoffeeUser);
    HeavenCoffeeUser findUserById(UUID userId);
    void updateHeavenCoffeeUser(UUID userId, HeavenCoffeeUser updatedHeavenCoffeeUser);
    void deleteHeavenCoffeeUser(UUID userId);
}
