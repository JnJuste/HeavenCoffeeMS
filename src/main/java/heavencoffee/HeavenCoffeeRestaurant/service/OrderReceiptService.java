package heavencoffee.HeavenCoffeeRestaurant.service;

import heavencoffee.HeavenCoffeeRestaurant.model.OrderReceipt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface OrderReceiptService {
    List<OrderReceipt> findAllOrderReceipts();
    void saveOrderReceipt(OrderReceipt orderReceipt);
    OrderReceipt findOrderById(UUID orderReceiptId);
    void updateOrderReceipt(UUID orderReceiptId, OrderReceipt updatedOrderReceipt);
    void deleteOrderReceipt(UUID orderReceiptId);
}
