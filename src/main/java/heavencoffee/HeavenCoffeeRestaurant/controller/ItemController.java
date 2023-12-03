package heavencoffee.HeavenCoffeeRestaurant.controller;

import heavencoffee.HeavenCoffeeRestaurant.model.Item;
import heavencoffee.HeavenCoffeeRestaurant.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    //List all Items
    @GetMapping
    public String createItemForm(Model model){
        Item item = new Item();
        List<Item> items = itemService.findAllItems();
        model.addAttribute("items", items);
        model.addAttribute("item", item);
        return "Item/Item";
    }


    //Save a new Category
    @PostMapping("/new")
    public String saveCategory(@ModelAttribute("item") Item item) {
        itemService.saveItem(item);
        return "redirect:/items";
    }

    //Find Item by ID
    @GetMapping("/{itemId}/edit")
    public String editItemForm(@PathVariable UUID itemId, Model model) {
        Item item = itemService.findById(itemId);
        model.addAttribute("item", item);
        model.addAttribute("itemId", itemId); // Add this line to pass ItemId to the view
        return "Item/EditItem";
    }


    // Update Item
    @PostMapping("/{itemId}/update")
    public String updateItem(@PathVariable UUID itemId, @ModelAttribute("item") Item updatedItem, RedirectAttributes redirectAttributes) {
        try {
            itemService.updateItem(itemId, updatedItem);
            return "redirect:/items";
        } catch (RuntimeException e) {
            // Handle the exception (log it or add an error message to the redirect attributes)
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/items";
        }
    }


    //Delete Item
    @GetMapping("/{itemId}/delete")
    public String deleteCategory(@PathVariable UUID itemId) {
        itemService.deleteItem(itemId);
        return "redirect:/items";
    }

}
