package tacos.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.hateoas.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
@RestController
@RequestMapping(path="/design", produces="application/json")
@CrossOrigin(origins="*")
public class DesignTacoController {
    
    private TacoRepository tacoRepo;
    
    @Autowired
    EntityLinks entityLinks;
    
    @Autowired
    public DesignTacoController(TacoRepository tacoRepo) {

        this.tacoRepo = tacoRepo;

    };
    
    /*
     * 1.   Constructs a `PageRequest` object specifying a range of 12 objects
     *    from the first (0) page, sorted in descending order by the "createdAt" 
     *    attribute.
     * 2.   The `PageRequest` object is passed into the `tacoRepo`s `findAll()` 
     *    method, and the content of that page of results is returned to the client. 
     */
    @GetMapping("/recent")
    public Iterable<Taco> recentTacos() {
        
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending()); // 1
        
        return tacoRepo.findAll(page).getContent(); // 2
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") String id) {
        Optional<Taco> optTaco = tacoRepo.findById(id);
        if (optTaco.isPresent()) return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
