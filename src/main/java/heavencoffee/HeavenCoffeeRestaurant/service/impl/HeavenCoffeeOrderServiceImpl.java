package heavencoffee.HeavenCoffeeRestaurant.service.impl;

import heavencoffee.HeavenCoffeeRestaurant.model.HeavenCoffeeOrder;
import heavencoffee.HeavenCoffeeRestaurant.repository.HeavenCoffeeOrderRepository;
import heavencoffee.HeavenCoffeeRestaurant.service.HeavenCoffeeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeavenCoffeeOrderServiceImpl implements HeavenCoffeeOrderService {
    private HeavenCoffeeOrderRepository heavenCoffeeOrderRepository;

    @Autowired
    public HeavenCoffeeOrderServiceImpl(HeavenCoffeeOrderRepository heavenCoffeeOrderRepository){
        this.heavenCoffeeOrderRepository = heavenCoffeeOrderRepository;
    }

    @Override
    public List<HeavenCoffeeOrder> findAllHeavenCoffeeOrders() {
        List<HeavenCoffeeOrder> heavenCoffeeOrders = heavenCoffeeOrderRepository.findAll();
        return heavenCoffeeOrders;
    }

    @Override
    public HeavenCoffeeOrder saveHeavenCoffeeOrder(HeavenCoffeeOrder heavenCoffeeOrder) {
        return heavenCoffeeOrderRepository.save(heavenCoffeeOrder);
    }
}
