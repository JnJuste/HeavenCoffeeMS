package heavencoffee.HeavenCoffeeRestaurant.model;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class HeavenCoffeeOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderID;
    private String orderCode;
    @ManyToOne
    private HeavenCoffeeUser heavenCoffeeUser;
    @ManyToOne
    private Item item;
    private Integer quantity;
    private BigDecimal totalAmount;
    @CreationTimestamp
    private LocalDateTime orderDate;
    @CreationTimestamp
    private LocalDateTime modifiedDate;

}
