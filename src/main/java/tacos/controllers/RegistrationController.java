package tacos.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.domain.User;
import tacos.repository.UserRepository;
import tacos.service.LoginService;
import tacos.service.RegistrationService;

@Slf4j
@Controller
@RequestMapping("/auth")
public class RegistrationController {
    
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;
    
    public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder) 
    {   
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    
//    @GetMapping
//    public String registerForm(Model model) 
//    {   
//        model.addAttribute(new RegistrationService());
//        
//        return "registration";
//    }
    
    @GetMapping
    public String registerForm() {
      return "registration";
    }
    
    @PostMapping
    public String processRegistration(RegistrationService form) {
        
        log.info(form.toString());
        
        //      userRepo.save(form.toUser(passwordEncoder));
      return "redirect:/login";
    }

//    @PostMapping
//    public String processRegistration(@ModelAttribute RegistrationService registrationService) 
//    {    
//        userRepo.save(registrationService.toUser(passwordEncoder));
//        
//        return "redirect:/";
//    }
//    
//    @PostMapping("/welcome")
//    public String userLogin(@ModelAttribute LoginService loginService) 
//    {   
//        User user = userRepo.findByUsername(loginService.getUsername());
//        
//        return "redirect:/design";
//    }
}
