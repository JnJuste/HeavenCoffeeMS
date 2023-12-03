package heavencoffee.HeavenCoffeeRestaurant.service.impl;

import heavencoffee.HeavenCoffeeRestaurant.model.OrderReceipt;
import heavencoffee.HeavenCoffeeRestaurant.repository.OrderReceiptRepository;
import heavencoffee.HeavenCoffeeRestaurant.service.OrderReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderReceiptServiceImpl implements OrderReceiptService {
    private final OrderReceiptRepository orderReceiptRepository;

    @Autowired
    public OrderReceiptServiceImpl(OrderReceiptRepository orderReceiptRepository){
        this.orderReceiptRepository = orderReceiptRepository;
    }

    @Override
    public List<OrderReceipt> findAllOrderReceipts() {
        return orderReceiptRepository.findAll();
    }

    @Override
    public OrderReceipt saveOrderReceipt(OrderReceipt orderReceipt) {
        return orderReceiptRepository.save(orderReceipt);
    }

    @Override
    public OrderReceipt findById(UUID orderReceiptId) {
        return orderReceiptRepository.findById(orderReceiptId).orElse(null);
    }

    @Override
    public OrderReceipt updateOrderReceipt(UUID orderReceiptId, OrderReceipt updatedOrderReceipt) {
        Optional<OrderReceipt> optionalOrderReceipt = orderReceiptRepository.findById(orderReceiptId);
        if (optionalOrderReceipt.isPresent()) {
            OrderReceipt existingOrderReceipt = optionalOrderReceipt.get();
            existingOrderReceipt.setOrderReceiptCode(updatedOrderReceipt.getOrderReceiptCode());
            existingOrderReceipt.setHeavenCoffeeOrder(updatedOrderReceipt.getHeavenCoffeeOrder());
            existingOrderReceipt.setReceiptDate(updatedOrderReceipt.getReceiptDate());
            return orderReceiptRepository.save(existingOrderReceipt);
        } else {
            throw new RuntimeException("Order Receipt with ID " + orderReceiptId + " is not found!");
        }
    }

    @Override
    public void deleteOrderReceipt(UUID orderReceiptId) {
        orderReceiptRepository.deleteById(orderReceiptId);
    }
}
