package tacos.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.domain.Ingredient;
import tacos.domain.Taco;
import tacos.domain.User;
import tacos.domain.Ingredient.Type;
import tacos.domain.Order;
import tacos.repository.IngredientRepository;
import tacos.repository.TacoRepository;
import tacos.repository.UserRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
    
    private final IngredientRepository ingredientRepo;
    
    private TacoRepository tacoRepo;
    
    private UserRepository userRepo;
    
    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepo, UserRepository userRepo) {
        this.ingredientRepo = ingredientRepo;
        this.tacoRepo = tacoRepo;
        this.userRepo = userRepo;
    };
    
    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }
    
    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model, Principal principal) {
        
        List<Ingredient> ingredients = new ArrayList<>();
        
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));
        
        Type[] types = Ingredient.Type.values();
        
        for (Type type : types) {
            
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
            
        }
        
        String username = principal.getName();
        
        User user = userRepo.findByUsername(username);
        
        model.addAttribute("user", user);
        
        return "design";
    }
    
    @PostMapping
    public String processDesign(@Valid Taco design, @ModelAttribute Order order, Errors errors) {
        
        
        log.info("design: " + design);
        log.info("order: " + order);
        log.warn("errors: " + errors);
        
        if(errors.hasErrors()) return "design";
        
        Taco savedTaco = tacoRepo.save(design);
        order.addTaco(savedTaco);
        
        return "redirect:orders/current";

    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getIngredientType().equals(type))
                .collect(Collectors.toList());
    }

}
