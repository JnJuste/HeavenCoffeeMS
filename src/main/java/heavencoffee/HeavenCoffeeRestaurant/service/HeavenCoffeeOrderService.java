package heavencoffee.HeavenCoffeeRestaurant.service;

import heavencoffee.HeavenCoffeeRestaurant.model.HeavenCoffeeOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HeavenCoffeeOrderService {
    List<HeavenCoffeeOrder> findAllHeavenCoffeeOrders();
    HeavenCoffeeOrder saveHeavenCoffeeOrder(HeavenCoffeeOrder heavenCoffeeOrder);
}
