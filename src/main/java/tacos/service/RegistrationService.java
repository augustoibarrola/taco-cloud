package tacos.service;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;
import tacos.domain.User;

@Data
public class RegistrationService {

    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;
    
    public User toUser(PasswordEncoder passwordEncoder) {

        return new User(username, passwordEncoder.encode(password), fullname, street, city, state, zip, phoneNumber);
    }
    
    @Override
    public String toString() {
        return "RegistrationService : [" + 
                " username: " + this.username +
                " password: " + this.password +
                " fullname: " + this.fullname +
                " street: " + this.street +
                " city: " + this.city +
                " state: " + this.state +
                " zip: " + this.zip +
                " phoneNumber: " + this.phoneNumber +
                " ]"
                ;
    }

}
