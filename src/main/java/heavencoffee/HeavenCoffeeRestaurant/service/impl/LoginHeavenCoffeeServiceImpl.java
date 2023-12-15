package heavencoffee.HeavenCoffeeRestaurant.service.impl;

import heavencoffee.HeavenCoffeeRestaurant.model.HeavenCoffeeUser;
import heavencoffee.HeavenCoffeeRestaurant.repository.HeavenCoffeeUserRepository;
import heavencoffee.HeavenCoffeeRestaurant.service.LoginHeavenCoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginHeavenCoffeeServiceImpl implements LoginHeavenCoffeeService {
    private final HeavenCoffeeUserRepository heavenCoffeeUserRepository;

    @Autowired
    public LoginHeavenCoffeeServiceImpl(HeavenCoffeeUserRepository heavenCoffeeUserRepository) {
        this.heavenCoffeeUserRepository = heavenCoffeeUserRepository;
    }

    @Override
    public boolean authenticateUser(String email, String password) {
        // Find the user by email
        HeavenCoffeeUser user = heavenCoffeeUserRepository.findByEmail(email);

        // Check if the user exists and the password is correct
        return user != null && passwordMatches(password, user.getPassword());
    }

    @Override
    public String getUserRole(String email) {
        // Find the user by email
        HeavenCoffeeUser user = heavenCoffeeUserRepository.findByEmail(email);

        // Return the user's role
        return (user != null) ? user.getUserRole().toString() : null;
    }

    private boolean passwordMatches(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
