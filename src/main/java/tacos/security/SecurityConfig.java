package tacos.security;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    DataSource datasource;
    
    @Autowired
    UserDetailsService userDetailsService;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder("53cr3t");
    }
    
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        
//        httpSecurity
//            .authorizeRequests()
//                .antMatchers("/design", "/orders")
//                    .hasRole("ROLE_USER")
//                .antMatchers("/", "/**").permitAll();
        httpSecurity
        .authorizeRequests()
            .antMatchers("/design", "/orders")
                .access("hasRole('ROLE_USER')")
            .antMatchers("/", "/**").access("permitAll")
            .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/design")
            .and()
            .logout()
            .logoutSuccessUrl("/");
        
        /*
         * The below allows access to the h2-console
         * via the web browser. Without it, we get a 
         * 403 Forbidden response
         */
        httpSecurity
            .csrf().
                disable();
        httpSecurity
            .headers()
                .frameOptions()
                    .disable();
        
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        
        /*
         * In-Memory User Store
         */
//        authenticationManagerBuilder
//            .inMemoryAuthentication()
//                .withUser("Augusto")
//                .password("augustopassword")
//                .authorities("ROLE_USER")
//            .and()
//                .withUser("Anastasia")
//                .password("anastasiapassword")
//                .authorities("ROLE_USER");
        
        /*
         * JDBC-Based User Store (plain)
         */
//        authenticationManagerBuilder
//            .jdbcAuthentication()
//                .dataSource(datasource);
        
        /*
         * JDBC-Based User Store (configured queries)
         */
//        authenticationManagerBuilder
//            .jdbcAuthentication()
//                .dataSource(datasource)
//                .usersByUsernameQuery(
//                        "select username, password, enabled from Users " + 
//                        "where username = ?")
//                .authoritiesByUsernameQuery(
//                        "select username, authority from UserAuthorities " + 
//                        "where username = ?");
        
        /*
         * JDBC-Based User Store (configured for encoded passwords)
         */
//        authenticationManagerBuilder
//            .jdbcAuthentication()
//                .dataSource(datasource)
//                .usersByUsernameQuery(
//                        "select username, password, enabled from Users " + 
//                        "where username = ?")
//                .authoritiesByUsernameQuery(
//                        "select username, authority from UserAuthorities " + 
//                        "where username = ?")
//                .passwordEncoder(new StandardPasswordEncoder("53cr3t"));
        
        /*
         * LDAP-Backed User Store
         */
//        authenticationManagerBuilder
//            .ldapAuthentication()
//                .userSearchFilter("(uid={0)")
//                .groupSearchFilter("member={0}");
        
        /*
         * LDAP-Backed User Store with configured query base
         */
//        authenticationManagerBuilder
//            .ldapAuthentication()
//                .userSearchBase("ou=people")
//                .userSearchFilter("(uid={0)")
//                .groupSearchBase("ou=groups")
//                .groupSearchFilter("member={0}");
        
        /*
         * LDAP-Backed User Store with configured query base and password comparison
         */
//        authenticationManagerBuilder
//            .ldapAuthentication()
//                .userSearchBase("ou=people")
//                .userSearchFilter("(uid={0)")
//                .groupSearchBase("ou=groups")
//                .groupSearchFilter("member={0}")
//                .passwordCompare();
        
        /*
         * LDAP-Backed User Store with configured query base and password comparison for non-standard
         * password attribute name
         */
//        authenticationManagerBuilder
//            .ldapAuthentication()
//                .userSearchBase("ou=people")
//                .userSearchFilter("(uid={0)")
//                .groupSearchBase("ou=groups")
//                .groupSearchFilter("member={0}")
//                .passwordCompare()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .passwordAttribute("passcode");
        
        /*
         * Remote LDAP-Backed User Store with configured query base and password comparison for non-standard
         * password attribute name
         */
//        authenticationManagerBuilder
//            .ldapAuthentication()
//                .userSearchBase("ou=people")
//                .userSearchFilter("(uid={0)")
//                .groupSearchBase("ou=groups")
//                .groupSearchFilter("member={0}")
//                .passwordCompare()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .passwordAttribute("passcode")
//                .contextSource()
//                    .url("ldap://tacocloud.com:389/dc=tacocloud,dc=com");
        
        /*
         * Embedded LDAP-Backed User Store with configured query base and password comparison for non-standard
         * password attribute name
         */
//        authenticationManagerBuilder
//            .ldapAuthentication()
//                .userSearchBase("ou=people")
//                .userSearchFilter("(uid={0)")
//                .groupSearchBase("ou=groups")
//                .groupSearchFilter("member={0}")
//                .passwordCompare()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .passwordAttribute("passcode")
//                .contextSource()
//                .root("dc=tacocloud,dc=com")
//                .ldif("classpath:src/main/resources/users.ldif");
        
        authenticationManagerBuilder
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());

    }

}
