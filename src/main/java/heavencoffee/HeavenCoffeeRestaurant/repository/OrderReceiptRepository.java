package heavencoffee.HeavenCoffeeRestaurant.repository;

import heavencoffee.HeavenCoffeeRestaurant.model.OrderReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderReceiptRepository extends JpaRepository<OrderReceipt, UUID> {
}
