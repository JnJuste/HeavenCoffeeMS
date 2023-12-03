package heavencoffee.HeavenCoffeeRestaurant.service.impl;

import heavencoffee.HeavenCoffeeRestaurant.model.HeavenCoffeeOrder;
import heavencoffee.HeavenCoffeeRestaurant.repository.HeavenCoffeeOrderRepository;
import heavencoffee.HeavenCoffeeRestaurant.service.HeavenCoffeeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public HeavenCoffeeOrder findById(UUID orderId) {
        return heavenCoffeeOrderRepository.findById(orderId).orElse(null);
    }

    @Override
    public HeavenCoffeeOrder updateHeavenCoffeeUser(UUID orderId, HeavenCoffeeOrder updatedHeavenCoffeeOrder) {
        Optional<HeavenCoffeeOrder> optionalHeavenCoffeeOrder = heavenCoffeeOrderRepository.findById(orderId);
        if (optionalHeavenCoffeeOrder.isPresent()) {
            HeavenCoffeeOrder existingHeavenCoffeeOrder = optionalHeavenCoffeeOrder.get();
            existingHeavenCoffeeOrder.setOrderCode(updatedHeavenCoffeeOrder.getOrderCode());
            existingHeavenCoffeeOrder.setHeavenCoffeeUser(updatedHeavenCoffeeOrder.getHeavenCoffeeUser());
            existingHeavenCoffeeOrder.setItem(updatedHeavenCoffeeOrder.getItem());
            existingHeavenCoffeeOrder.setQuantity(updatedHeavenCoffeeOrder.getQuantity());
            existingHeavenCoffeeOrder.setTotalAmount(updatedHeavenCoffeeOrder.getTotalAmount());
            existingHeavenCoffeeOrder.setModifiedDate(updatedHeavenCoffeeOrder.getModifiedDate());
            return heavenCoffeeOrderRepository.save(existingHeavenCoffeeOrder);
        } else {
            throw new RuntimeException("Heaven Coffee Order with ID " + orderId + " is not found!");
        }
    }

    @Override
    public void deleteHeavenCoffeeOrder(UUID orderId) {
        heavenCoffeeOrderRepository.deleteById(orderId);
    }
}
