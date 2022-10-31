package tacos.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.repository.UserRepository;
import tacos.service.RegistrationService;

@Slf4j
@Controller
@RequestMapping("/auth")
public class RegistrationController {
    
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;
    
    public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    
    @GetMapping
    public String registerForm(Model model) {
        
        model.addAttribute(new RegistrationService());
        return "registration";
    }

    @PostMapping
    public String processRegistration(@ModelAttribute RegistrationService registrationService) {
        log.info(registrationService.toString());
        log.info(registrationService.toUser(passwordEncoder).toString());
        userRepo.save(registrationService.toUser(passwordEncoder));
        return "redirect:/design";
    }
}
