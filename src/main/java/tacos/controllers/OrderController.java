package tacos.controllers;

import javax.validation.Valid;

//import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import tacos.domain.Order;
import tacos.domain.User;
import tacos.repository.OrderRepository;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
@ConfigurationProperties(prefix="taco.orders")
public class OrderController {
    
    private int pageSize = 20;
    
    private OrderRepository orderRepo;
    
    public OrderController(OrderRepository orderRepo) {this.orderRepo = orderRepo;}
    
    @GetMapping("/current")
    public String orderForm(Model model) {
        log.info(model.toString());
        return "orderForm";
        }
    
    @GetMapping
    public String ordersForUser(
            @AuthenticationPrincipal User user, 
            Model model) 
    {
        
        Pageable pageable = PageRequest.of(0, pageSize);
        
        model.addAttribute("orders", orderRepo.findByuserOrderByPlacedAtDesc(user, pageable));
        
        return "orderList";
    }
    
    
    @PostMapping
    public String processOrder(
            @Valid Order order, 
            @AuthenticationPrincipal User user, 
            SessionStatus sessionStatus, 
            Errors error) 
    {
        if(error.hasErrors()) return "orderForm";
        
        order.setUser(user);
        
        orderRepo.save(order);

        sessionStatus.setComplete();
        
        return "redirect:/";
    }
}
