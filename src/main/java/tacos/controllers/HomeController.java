package tacos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * The Controller is responsible for fetching 
 * requests and processing the data according
 * to the incoming request's details.
 */
@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "home";
	}
}

