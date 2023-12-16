package heavencoffee.HeavenCoffeeRestaurant.controller;

import heavencoffee.HeavenCoffeeRestaurant.model.HeavenCoffeeOrder;
import heavencoffee.HeavenCoffeeRestaurant.model.OrderReceipt;
import heavencoffee.HeavenCoffeeRestaurant.service.HeavenCoffeeOrderService;
import heavencoffee.HeavenCoffeeRestaurant.service.OrderReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("orderReceipts")
public class OrderReceiptController {
    private final OrderReceiptService orderReceiptService;
    private final HeavenCoffeeOrderService heavenCoffeeOrderService;
    @Autowired
    public OrderReceiptController(OrderReceiptService orderReceiptService,
                                  HeavenCoffeeOrderService heavenCoffeeOrderService){
        this.orderReceiptService = orderReceiptService;
        this.heavenCoffeeOrderService =heavenCoffeeOrderService;
    }

    //List all OrderReceipts
    @GetMapping
    public String createOrderReceiptForm(Model model){
        OrderReceipt orderReceipt = new OrderReceipt();
        List<OrderReceipt> orderReceipts = orderReceiptService.findAllOrderReceipts();
        List<HeavenCoffeeOrder> heavenCoffeeOrders = heavenCoffeeOrderService.findAllHeavenCoffeeOrders();
        model.addAttribute("orderReceipts", orderReceipts);
        model.addAttribute("orderReceipt", orderReceipt);
        model.addAttribute("heavenCoffeeOrders", heavenCoffeeOrders);
        return "OrderReceipt";
    }


    //Save a new OrderReceipt
    @PostMapping("/new")
    public String saveOrderReceipt(@ModelAttribute("heavenCoffeeUser") OrderReceipt orderReceipt) {
        orderReceiptService.saveOrderReceipt(orderReceipt);
        return "redirect:/orderReceipts";
    }

    //Find OrderReceipt by ID
    @GetMapping("/{orderReceiptId}/edit")
    public String editOrderReceiptForm(@PathVariable UUID orderReceiptId, Model model) {
        OrderReceipt orderReceipt = orderReceiptService.findOrderById(orderReceiptId);
        List<HeavenCoffeeOrder> heavenCoffeeOrders = heavenCoffeeOrderService.findAllHeavenCoffeeOrders();
        model.addAttribute("orderReceipt", orderReceipt);
        model.addAttribute("orderReceiptId", orderReceiptId); // Add this line to pass orderReceiptId to the view
        model.addAttribute("heavenCoffeeOrders", heavenCoffeeOrders);
        return "EditOrderReceipt";
    }


    // Update OrderReceipt
    @PostMapping("/{orderReceiptId}/update")
    public String updateOrderReceipt(@PathVariable UUID orderReceiptId, @ModelAttribute("heavenCoffeeUser") OrderReceipt updatedOrderReceipt, RedirectAttributes redirectAttributes) {
        try {
            orderReceiptService.updateOrderReceipt(orderReceiptId, updatedOrderReceipt);
            return "redirect:/orderReceipts";
        } catch (RuntimeException ex) {
            // Handle the exception (log it or add an error message to the redirect attributes)
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
            return "redirect:/orderReceipts";
        }
    }


    //Delete User
    @GetMapping("/{orderReceiptId}/delete")
    public String deleteOrderReceipt(@PathVariable UUID orderReceiptId) {
        orderReceiptService.deleteOrderReceipt(orderReceiptId);
        return "redirect:/orderReceipts";
    }

}
