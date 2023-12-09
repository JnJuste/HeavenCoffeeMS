package heavencoffee.HeavenCoffeeRestaurant.service;

import heavencoffee.HeavenCoffeeRestaurant.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ItemService {
    List<Item> findAllItems();
    void saveItem (Item item);
    Item findItemById(UUID itemId);
    void updateItem(UUID itemId, Item updatedItem);
    void deleteItem(UUID itemId);
}
