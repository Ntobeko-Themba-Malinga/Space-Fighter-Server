package server.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
    private IUserRepository userRepository;

    @BeforeEach
    void setUp() {
        this.userRepository = new UserRepository();
    }

    @Test
    @Order(1)
    void register() {
        User testUser = userRepository.register("TestCrashDummy", "testpass123");
        assertEquals(1, testUser.getId());
        assertEquals("TestCrashDummy", testUser.getUsername());
        assertEquals("testpass123", testUser.getToken());
    }

    @Test
    @Order(1)
    void registerExist() {
        userRepository.register("TestCrashDummy", "testpass123");
        User testUser = userRepository.register("TestCrashDummy", "testpass123");
        assertNull(testUser);
    }

    @Test
    @Order(2)
    void getUser() {
        userRepository.register("TestCrashDummy2", "testpass123");
        User user = userRepository.getUser("testpass123");
        assertEquals("TestCrashDummy2", user.getUsername());
        assertEquals("testpass123", user.getToken());
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}