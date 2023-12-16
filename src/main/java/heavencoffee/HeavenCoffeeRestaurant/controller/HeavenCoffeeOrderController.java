package heavencoffee.HeavenCoffeeRestaurant.controller;

import heavencoffee.HeavenCoffeeRestaurant.model.HeavenCoffeeOrder;
import heavencoffee.HeavenCoffeeRestaurant.model.HeavenCoffeeUser;
import heavencoffee.HeavenCoffeeRestaurant.model.Item;
import heavencoffee.HeavenCoffeeRestaurant.service.HeavenCoffeeOrderService;

import heavencoffee.HeavenCoffeeRestaurant.service.HeavenCoffeeUserService;
import heavencoffee.HeavenCoffeeRestaurant.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/heavenCoffeeOrders")
public class HeavenCoffeeOrderController {
    private final HeavenCoffeeOrderService heavenCoffeeOrderService;
    private final ItemService itemService;
    private final HeavenCoffeeUserService heavenCoffeeUserService;

    @Autowired
    public HeavenCoffeeOrderController(HeavenCoffeeOrderService heavenCoffeeOrderService, ItemService itemService, HeavenCoffeeUserService heavenCoffeeUserService){
        this.heavenCoffeeOrderService = heavenCoffeeOrderService;
        this.itemService = itemService;
        this.heavenCoffeeUserService = heavenCoffeeUserService;
    }

    //List all Order
    @GetMapping
    public String createOrderForm(Model model){
        HeavenCoffeeOrder heavenCoffeeOrder = new HeavenCoffeeOrder();
        List<HeavenCoffeeOrder> heavenCoffeeOrders = heavenCoffeeOrderService.findAllHeavenCoffeeOrders();
        List<HeavenCoffeeUser> heavenCoffeeUsers = heavenCoffeeUserService.findAllHeavenCoffeeUsers();
        List<Item> items = itemService.findAllItems();

        model.addAttribute("heavenCoffeeOrders", heavenCoffeeOrders);
        model.addAttribute("heavenCoffeeOrder", heavenCoffeeOrder);
        model.addAttribute("heavenCoffeeUsers", heavenCoffeeUsers);
        model.addAttribute("items", items);

        return "HeavenCoffeeOrder";
    }

    //Save a new Order
    @PostMapping("/new")
    public String saveOrder(@ModelAttribute("heavenCoffeeOrder") HeavenCoffeeOrder heavenCoffeeOrder) {
        heavenCoffeeOrderService.saveHeavenCoffeeOrder(heavenCoffeeOrder);
        return "redirect:/heavenCoffeeOrders";
    }

    //Find Order by ID
    @GetMapping("/{orderId}/edit")
    public String editOrderForm(@PathVariable UUID orderId, Model model) {
        HeavenCoffeeOrder heavenCoffeeOrder = heavenCoffeeOrderService.findOrderById(orderId);
        List<HeavenCoffeeUser> heavenCoffeeUsers = heavenCoffeeUserService.findAllHeavenCoffeeUsers();
        List<Item> items = itemService.findAllItems();
        model.addAttribute("heavenCoffeeOrder", heavenCoffeeOrder);
        model.addAttribute("heavenCoffeeUsers", heavenCoffeeUsers);
        model.addAttribute("items", items);
        model.addAttribute("orderId", orderId); // Add this line to pass ItemId to the view
        return "EditHeavenCoffeeOrder";
    }


    // Update Order
    @PostMapping("/{orderId}/update")
    public String updateOrder(@PathVariable UUID orderId, @ModelAttribute("heavenCoffeeOrder") HeavenCoffeeOrder updatedHeavenCoffeeOrder, RedirectAttributes redirectAttributes) {
        try {
            heavenCoffeeOrderService.updateHeavenCoffeeOrder(orderId, updatedHeavenCoffeeOrder);
            return "redirect:/heavenCoffeeOrders";
        } catch (RuntimeException ex) {
            // Handle the exception (log it or add an error message to the redirect attributes)
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
            return "redirect:/heavenCoffeeOrders";
        }
    }


    //Delete Order
    @GetMapping("/{orderId}/delete")
    public String deleteOrder(@PathVariable UUID orderId) {
        heavenCoffeeOrderService.deleteHeavenCoffeeOrder(orderId);
        return "redirect:/heavenCoffeeOrders";
    }
}
