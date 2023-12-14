package heavencoffee.HeavenCoffeeRestaurant.service.impl;

import heavencoffee.HeavenCoffeeRestaurant.model.HeavenCoffeeUser;
import heavencoffee.HeavenCoffeeRestaurant.repository.HeavenCoffeeUserRepository;
import heavencoffee.HeavenCoffeeRestaurant.service.HeavenCoffeeLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HeavenCoffeeLoginServiceImpl implements HeavenCoffeeLoginService {

    private final HeavenCoffeeUserRepository heavenCoffeeUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public HeavenCoffeeLoginServiceImpl(HeavenCoffeeUserRepository heavenCoffeeUserRepository,
                                        @Qualifier("passwordEncoder")BCryptPasswordEncoder passwordEncoder) {
        this.heavenCoffeeUserRepository = heavenCoffeeUserRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Optional<HeavenCoffeeUser> authenticateUser(String email, String password, String requiredRole) {
        Optional<HeavenCoffeeUser> heavenCoffeeUserOptional = heavenCoffeeUserRepository.findByEmail(email);

        if (heavenCoffeeUserOptional.isPresent()) {
            HeavenCoffeeUser user = heavenCoffeeUserOptional.get();

            // Check password
            if (passwordEncoder.matches(password, user.getPassword())) {
                // Check user role
                if (user.getUserRole().name().equals(requiredRole)) {
                    return Optional.of(user);
                }
            }
        }
        return Optional.empty();
    }
}
