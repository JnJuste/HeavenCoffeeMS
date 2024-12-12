package heavencoffee.HeavenCoffeeRestaurant;

import heavencoffee.HeavenCoffeeRestaurant.model.EUserRole;
import heavencoffee.HeavenCoffeeRestaurant.model.HeavenCoffeeUser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDateTime;
import java.util.UUID;

public class HeavenCoffeeUserTests {

    // 1. Test User Creation with Valid Inputs
    @Test
    public void testUserCreationValidInputs() {
        HeavenCoffeeUser user = HeavenCoffeeUser.builder()
                .userID(UUID.randomUUID())
                .fullNames("John Doe")
                .phoneNumber("1234567890")
                .email("john.doe@example.com")
                .password("securepassword")
                .userRole(EUserRole.ADMIN)
                .createdAt(LocalDateTime.now())
                .build();

        assertNotNull(user);
        assertEquals("John Doe", user.getFullNames());
        assertEquals("1234567890", user.getPhoneNumber());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals(EUserRole.ADMIN, user.getUserRole());
    }

    // 2. Test Enum Role Assignment Validity
    @Test
    public void testUserRoleEnumAssignment() {
        HeavenCoffeeUser user = new HeavenCoffeeUser();
        user.setUserRole(EUserRole.STAFF);
        assertEquals(EUserRole.STAFF, user.getUserRole());
    }

    // 3. Test Default Timestamp Initialization
    @Test
    public void testDefaultTimestampInitialization() {
        HeavenCoffeeUser user = HeavenCoffeeUser.builder()
                .userID(UUID.randomUUID())
                .fullNames("Jane Doe")
                .phoneNumber("0987654321")
                .email("jane.doe@example.com")
                .password("securepassword")
                .userRole(EUserRole.STAFF)
                .build();

        assertNotNull(user.getCreatedAt());
    }

    // 4. Test Password Validation with Null
    @Test
    public void testPasswordValidationWithNull() {
        HeavenCoffeeUser user = new HeavenCoffeeUser();
        user.setPassword(null);
        assertNull(user.getPassword());
    }

    // 5. Test Invalid Role Assignment
    @Test
    public void testInvalidUserRoleAssignment() {
        assertThrows(IllegalArgumentException.class, () -> {
            EUserRole.valueOf("INVALID_ROLE");
        });
    }

    // 6. Test Email Validation for Invalid Format
    @Test
    public void testInvalidEmailFormat() {
        HeavenCoffeeUser user = HeavenCoffeeUser.builder()
                .email("invalidemail")
                .build();

        assertFalse(user.getEmail().contains("@"));
    }

    // 7. Test Duplicate User ID
    @Test
    public void testDuplicateUserId() {
        UUID commonId = UUID.randomUUID();

        HeavenCoffeeUser user1 = HeavenCoffeeUser.builder()
                .userID(commonId)
                .build();

        HeavenCoffeeUser user2 = HeavenCoffeeUser.builder()
                .userID(commonId)
                .build();

        assertEquals(user1.getUserID(), user2.getUserID());
    }

    // 8. Test Builder with Missing Fields
    @Test
    public void testBuilderMissingFields() {
        HeavenCoffeeUser user = HeavenCoffeeUser.builder()
                .email("test@example.com")
                .build();

        assertNull(user.getFullNames());
        assertNotNull(user.getEmail());
    }
}

