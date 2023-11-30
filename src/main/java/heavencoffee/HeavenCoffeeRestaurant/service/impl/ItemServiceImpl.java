package heavencoffee.HeavenCoffeeRestaurant.service.impl;


import heavencoffee.HeavenCoffeeRestaurant.model.Item;
import heavencoffee.HeavenCoffeeRestaurant.repository.ItemRepository;
import heavencoffee.HeavenCoffeeRestaurant.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
