package heavencoffee.HeavenCoffeeRestaurant.service.impl;

import heavencoffee.HeavenCoffeeRestaurant.model.HeavenCoffeeUser;
import heavencoffee.HeavenCoffeeRestaurant.repository.HeavenCoffeeUserRepository;
import heavencoffee.HeavenCoffeeRestaurant.service.HeavenCoffeeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeavenCoffeeUserServiceImpl implements HeavenCoffeeUserService {
    private HeavenCoffeeUserRepository heavenCoffeeUserRepository;

    @Autowired
    public HeavenCoffeeUserServiceImpl(HeavenCoffeeUserRepository heavenCoffeeUserRepository){
        this.heavenCoffeeUserRepository = heavenCoffeeUserRepository;
    }
    @Override
    public List<HeavenCoffeeUser> findAllHeavenCoffeeUsers() {
        List<HeavenCoffeeUser> heavenCoffeeUsers = heavenCoffeeUserRepository.findAll();
        return heavenCoffeeUsers;
    }

    @Override
    public HeavenCoffeeUser saveHeavenCoffeeUser(HeavenCoffeeUser heavenCoffeeUser) {
        return heavenCoffeeUserRepository.save(heavenCoffeeUser);
    }
}
