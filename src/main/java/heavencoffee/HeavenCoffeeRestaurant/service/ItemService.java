package heavencoffee.HeavenCoffeeRestaurant.service;

import heavencoffee.HeavenCoffeeRestaurant.model.Category;
import heavencoffee.HeavenCoffeeRestaurant.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ItemService {
    List<Item> findAllItems();
    Item saveItem (Item item);
    Item findById(UUID itemId);
    Item updateItem(UUID itemId, Item updatedItem);
    void deleteItem(UUID itemId);
}
