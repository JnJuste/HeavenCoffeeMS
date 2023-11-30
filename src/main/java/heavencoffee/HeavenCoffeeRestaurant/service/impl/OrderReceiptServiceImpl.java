package heavencoffee.HeavenCoffeeRestaurant.service.impl;


import heavencoffee.HeavenCoffeeRestaurant.model.OrderReceipt;
import heavencoffee.HeavenCoffeeRestaurant.repository.OrderReceiptRepository;
import heavencoffee.HeavenCoffeeRestaurant.service.OrderReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderReceiptServiceImpl implements OrderReceiptService {
    private OrderReceiptRepository orderReceiptRepository;

    @Autowired
    public OrderReceiptServiceImpl(OrderReceiptRepository orderReceiptRepository){
        this.orderReceiptRepository = orderReceiptRepository;
    }

    @Override
    public List<OrderReceipt> findAllOrderReceipts() {
        List<OrderReceipt> orderReceipts = orderReceiptRepository.findAll();
        return orderReceipts;
    }

    @Override
    public OrderReceipt saveOrderReceipt(OrderReceipt orderReceipt) {
        return orderReceiptRepository.save(orderReceipt);
    }
}
