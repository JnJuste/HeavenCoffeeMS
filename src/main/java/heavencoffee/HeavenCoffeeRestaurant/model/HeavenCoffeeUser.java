package heavencoffee.HeavenCoffeeRestaurant.model;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class HeavenCoffeeUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userID;
    private String fullNames;
    private String userName;
    private String phoneNumber;
    private String email;
    private String password;
    private EUserRole userRole;
    @CreationTimestamp
    private LocalDateTime createdAt;

    // Hash the password using bcrypt before saving it
    /*public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }*/
}
