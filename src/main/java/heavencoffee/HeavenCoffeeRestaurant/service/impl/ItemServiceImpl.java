package heavencoffee.HeavenCoffeeRestaurant.service.impl;


import heavencoffee.HeavenCoffeeRestaurant.model.Item;
import heavencoffee.HeavenCoffeeRestaurant.repository.ItemRepository;
import heavencoffee.HeavenCoffeeRestaurant.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemServiceImpl implements ItemService {
    private ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> findAllItems() {
        List<Item> items = itemRepository.findAll();
        return items;
    }

    @Override
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item findById(UUID itemId) {
        return itemRepository.findById(itemId).orElse(null);
    }

    @Override
    public Item updateItem(UUID itemId, Item updatedItem) {
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (optionalItem.isPresent()) {
            Item existingItem = optionalItem.get();
            existingItem.setItemCode(updatedItem.getItemCode());
            existingItem.setItemName(updatedItem.getItemName());
            existingItem.setUnitPrice(updatedItem.getUnitPrice());
            existingItem.setStockQuantity(updatedItem.getStockQuantity());
            existingItem.setEItemStatus(updatedItem.getEItemStatus());
            existingItem.setModifiedAt(updatedItem.getModifiedAt());
            return itemRepository.save(existingItem);
        } else {
            throw new RuntimeException("Item with ID " + itemId + " is not found!");
        }
    }

    @Override
    public void deleteItem(UUID itemId) {
         itemRepository.deleteById(itemId);
    }
}
