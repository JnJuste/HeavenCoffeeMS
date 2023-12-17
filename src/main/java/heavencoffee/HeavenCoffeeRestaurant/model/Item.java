package heavencoffee.HeavenCoffeeRestaurant.model;

import jakarta.persistence.*;
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
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID itemID;
    private String itemCode;
    private String itemName;
    @ManyToOne
    private Category category;
    private BigDecimal unitPrice;
    private Integer stockQuantity;
    private EItemStatus itemStatus;
    @CreationTimestamp
    private LocalDateTime createdAt;
}