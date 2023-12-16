package heavencoffee.HeavenCoffeeRestaurant.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Configure security settings here
        http
                .authorizeRequests()
                .antMatchers("/public/**").permitAll()  // Allow access to public resources without authentication
                .anyRequest().authenticated()  // Require authentication for any other request
                .and()
                .formLogin()
                .loginPage("/login")  // Specify the login page URL
                .permitAll()  // Allow everyone to access the login page
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")  // Redirect to login page with a logout parameter
                .permitAll();  // Allow everyone to access the logout endpoint
    }
}
