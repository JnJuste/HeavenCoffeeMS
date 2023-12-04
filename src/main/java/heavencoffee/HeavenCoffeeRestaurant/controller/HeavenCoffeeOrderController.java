package heavencoffee.HeavenCoffeeRestaurant.controller;

import heavencoffee.HeavenCoffeeRestaurant.model.HeavenCoffeeOrder;
import heavencoffee.HeavenCoffeeRestaurant.service.HeavenCoffeeOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/orders")
public class HeavenCoffeeOrderController {
    private final HeavenCoffeeOrderService heavenCoffeeOrderService;

    @Autowired
    public HeavenCoffeeOrderController(HeavenCoffeeOrderService heavenCoffeeOrderService){
        this.heavenCoffeeOrderService = heavenCoffeeOrderService;
    }

    //List all Order
    @GetMapping
    public String createOrderForm(Model model){
        HeavenCoffeeOrder heavenCoffeeOrder = new HeavenCoffeeOrder();
        List<HeavenCoffeeOrder> heavenCoffeeOrders = heavenCoffeeOrderService.findAllHeavenCoffeeOrders();
        model.addAttribute("heavenCoffeeOrders", heavenCoffeeOrders);
        model.addAttribute("heavenCoffeeOrder", heavenCoffeeOrder);
        return "HeavenCoffeeOrder/HeavenCoffeeOrder";
    }


    //Save a new Order
    @PostMapping("/new")
    public String saveOrder(@ModelAttribute("heavenCoffeeOrder") HeavenCoffeeOrder heavenCoffeeOrder) {
        heavenCoffeeOrderService.saveHeavenCoffeeOrder(heavenCoffeeOrder);
        return "redirect:/orders";
    }

    //Find Order by ID
    @GetMapping("/{orderId}/edit")
    public String editOrderForm(@PathVariable UUID orderId, Model model) {
        HeavenCoffeeOrder heavenCoffeeOrder = heavenCoffeeOrderService.findById(orderId);
        model.addAttribute("heavenCoffeeOrder", heavenCoffeeOrder);
        model.addAttribute("orderId", orderId); // Add this line to pass ItemId to the view
        return "HeavenCoffeeOrder/EditHeavenCoffeeOrder";
    }


    // Update Order
    @PostMapping("/{orderId}/update")
    public String updateOrder(@PathVariable UUID orderId, @ModelAttribute("heavenCoffeeOrder") HeavenCoffeeOrder updatedHeavenCoffeeOrder, RedirectAttributes redirectAttributes) {
        try {
            heavenCoffeeOrderService.updateHeavenCoffeeOrder(orderId, updatedHeavenCoffeeOrder);
            return "redirect:/orders";
        } catch (RuntimeException e) {
            // Handle the exception (log it or add an error message to the redirect attributes)
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/orders";
        }
    }


    //Delete Order
    @GetMapping("/{orderId}/delete")
    public String deleteOrder(@PathVariable UUID orderId) {
        heavenCoffeeOrderService.deleteHeavenCoffeeOrder(orderId);
        return "redirect:/orders";
    }
}
