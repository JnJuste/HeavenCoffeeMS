package heavencoffee.HeavenCoffeeRestaurant.repository;

import heavencoffee.HeavenCoffeeRestaurant.model.HeavenCoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HeavenCoffeeOrderRepository extends JpaRepository<HeavenCoffeeOrder, UUID> {
}
