package tacos;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

import tacos.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
	
	@GetMapping
	public String showDesignForm(Model model) {
		List<Ingredient> ingredients = Arrays.asList( 
					new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
					new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
					new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
					new Ingredient("CARN", "Carnitas", Type.PROTEIN),
				);
	}

}
