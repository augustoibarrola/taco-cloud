import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
//    @Autowired
//    DataSource datasource;
    
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
        authenticationManagerBuilder
            .ldapAuthentication()
                .userSearchBase("ou=people")
                .userSearchFilter("(uid={0)")
                .groupSearchBase("ou=groups")
                .groupSearchFilter("member={0}")
                .passwordCompare()
                .passwordEncoder(new BCryptPasswordEncoder())
                .passwordAttribute("passcode")
                .contextSource()
                    .url("ldap://tacocloud.com:389/dc=tacocloud,dc=com");
    }

}
