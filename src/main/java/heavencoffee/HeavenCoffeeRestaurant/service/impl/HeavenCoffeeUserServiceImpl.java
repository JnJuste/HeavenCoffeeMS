package heavencoffee.HeavenCoffeeRestaurant.service.impl;

import heavencoffee.HeavenCoffeeRestaurant.model.HeavenCoffeeUser;
import heavencoffee.HeavenCoffeeRestaurant.repository.HeavenCoffeeUserRepository;
import heavencoffee.HeavenCoffeeRestaurant.service.HeavenCoffeeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HeavenCoffeeUserServiceImpl implements HeavenCoffeeUserService {
    private final HeavenCoffeeUserRepository heavenCoffeeUserRepository;

    @Autowired
    public HeavenCoffeeUserServiceImpl(HeavenCoffeeUserRepository heavenCoffeeUserRepository){
        this.heavenCoffeeUserRepository = heavenCoffeeUserRepository;
    }
    @Override
    public List<HeavenCoffeeUser> findAllHeavenCoffeeUsers() {
        return heavenCoffeeUserRepository.findAll();
    }

    @Override
    public HeavenCoffeeUser saveHeavenCoffeeUser(HeavenCoffeeUser heavenCoffeeUser) {
        return heavenCoffeeUserRepository.save(heavenCoffeeUser);
    }

    @Override
    public HeavenCoffeeUser findUserById(UUID userId) {
        return heavenCoffeeUserRepository.findById(userId).orElse(null);
    }

    @Override
    public HeavenCoffeeUser updateHeavenCoffeeUser(UUID userId, HeavenCoffeeUser updatedHeavenCoffeeUser) {
        Optional<HeavenCoffeeUser> optionalHeavenCoffeeUser = heavenCoffeeUserRepository.findById(userId);
        if (optionalHeavenCoffeeUser.isPresent()) {
            HeavenCoffeeUser existingHeavenCoffeeUser = optionalHeavenCoffeeUser.get();
            existingHeavenCoffeeUser.setUserCode(updatedHeavenCoffeeUser.getUserCode());
            existingHeavenCoffeeUser.setUserNames(updatedHeavenCoffeeUser.getUserNames());
            existingHeavenCoffeeUser.setPhoneNumber(updatedHeavenCoffeeUser.getPhoneNumber());
            existingHeavenCoffeeUser.setEmail(updatedHeavenCoffeeUser.getEmail());
            existingHeavenCoffeeUser.setPassword(updatedHeavenCoffeeUser.getPassword());
            existingHeavenCoffeeUser.setUserRole(updatedHeavenCoffeeUser.getUserRole());
            return heavenCoffeeUserRepository.save(existingHeavenCoffeeUser);
        } else {
            throw new RuntimeException("Heaven Coffee User with ID " + userId + " is not found!");
        }
    }

    @Override
    public void deleteHeavenCoffeeUser(UUID userId) {
        heavenCoffeeUserRepository.deleteById(userId);
    }
}
