package heavencoffee.HeavenCoffeeRestaurant.service;

import heavencoffee.HeavenCoffeeRestaurant.model.HeavenCoffeeOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface HeavenCoffeeOrderService {
    List<HeavenCoffeeOrder> findAllHeavenCoffeeOrders();
    HeavenCoffeeOrder saveHeavenCoffeeOrder(HeavenCoffeeOrder heavenCoffeeOrder);
    HeavenCoffeeOrder findById(UUID orderId);
    HeavenCoffeeOrder updateHeavenCoffeeUser(UUID orderId, HeavenCoffeeOrder updatedHeavenCoffeeOrder);
    void deleteHeavenCoffeeOrder(UUID orderId);
}
