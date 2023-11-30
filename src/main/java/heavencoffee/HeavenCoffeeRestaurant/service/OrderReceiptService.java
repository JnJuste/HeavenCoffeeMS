package heavencoffee.HeavenCoffeeRestaurant.service;

import heavencoffee.HeavenCoffeeRestaurant.model.OrderReceipt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderReceiptService {
    List<OrderReceipt> findAllOrderReceipts();
    OrderReceipt saveOrderReceipt(OrderReceipt orderReceipt);
}
