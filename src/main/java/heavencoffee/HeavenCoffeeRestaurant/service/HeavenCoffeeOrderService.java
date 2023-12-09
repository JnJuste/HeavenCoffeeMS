package heavencoffee.HeavenCoffeeRestaurant.service;

import heavencoffee.HeavenCoffeeRestaurant.model.HeavenCoffeeOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface HeavenCoffeeOrderService {
    List<HeavenCoffeeOrder> findAllHeavenCoffeeOrders();
    void saveHeavenCoffeeOrder(HeavenCoffeeOrder heavenCoffeeOrder);
    HeavenCoffeeOrder findOrderById(UUID orderId);
    void updateHeavenCoffeeOrder(UUID orderId, HeavenCoffeeOrder updatedHeavenCoffeeOrder);
    void deleteHeavenCoffeeOrder(UUID orderId);
}
