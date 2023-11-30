package heavencoffee.HeavenCoffeeRestaurant.repository;

import heavencoffee.HeavenCoffeeRestaurant.model.HeavenCoffeeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HeavenCoffeeUserRepository extends JpaRepository<HeavenCoffeeUser, UUID> {
}
