package tacos.controllers;

import javax.validation.Valid;

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
import tacos.config.OrderProps;
import tacos.domain.Order;
import tacos.domain.User;
import tacos.repository.OrderRepository;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
    
    private OrderRepository orderRepo;
    
    private OrderProps orderProps;
    
    public OrderController(OrderRepository orderRepo, OrderProps orderProps) {
        this.orderRepo = orderRepo;
        this.orderProps = orderProps;
        }
    
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
        
        Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
        
        model.addAttribute("orders", orderRepo.findByuserOrderByPlacedAtDesc(user, pageable));
        
        return "orderList";
    }
    
    
    @PostMapping
    public String processOrder(
            @Valid Order order, 
            SessionStatus sessionStatus, 
            Errors error) 
    {
        
        if(error.hasErrors()) return "orderForm";
        
        orderRepo.save(order);

        sessionStatus.setComplete();
        
        return "redirect:/";
    }
}
