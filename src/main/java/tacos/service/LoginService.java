package tacos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Service
public class LoginService {

    private String username;
    private String password;
    
    public LoginService() {};
    
//    @Autowired removed or else error is returned:
//      Parameter 0 of constructor in tacos.service.LoginService 
//      required a bean of type 'java.lang.String' that could not
//      be found.
    public LoginService(String username, String password) {
        this.username = username;
        this.password = password;
        log.info("User Created: \n" + this.toString());

    }
    
    @Override
    public String toString() {        
        return "LoginService : [" + 
            " username: " + this.username +
            " password: " + this.password +
            " ]"
            ;
    }
    
}
