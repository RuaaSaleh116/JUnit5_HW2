package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.najah.code.UserService;

@DisplayName("Test UserService Class")
public class TestUserService {

    @Test
    @DisplayName("Valid email should return true")
    void testValidEmail() {
        UserService userService = new UserService();

        assertTrue(userService.isValidEmail("admin@test.com"));
    }

    @Test
    @DisplayName("Null email should return false")
    void testNullEmail() {
        UserService userService = new UserService();

        assertFalse(userService.isValidEmail(null));
    }

    @Test
    @DisplayName("Email without @ should return false")
    void testEmailWithoutAt() {
        UserService userService = new UserService();

        assertFalse(userService.isValidEmail("admintest.com"));
    }

    @Test
    @DisplayName("Email without dot should return false")
    void testEmailWithoutDot() {
        UserService userService = new UserService();

        assertFalse(userService.isValidEmail("admin@testcom"));
    }

    @Test
    @DisplayName("Correct username and password should authenticate successfully")
    void testAuthenticateValid() {
        UserService userService = new UserService();

        assertTrue(userService.authenticate("admin", "1234"));
    }

    @Test
    @DisplayName("Wrong username should fail authentication")
    void testAuthenticateWrongUsername() {
        UserService userService = new UserService();

        assertFalse(userService.authenticate("user", "1234"));
    }

    @Test
    @DisplayName("Wrong password should fail authentication")
    void testAuthenticateWrongPassword() {
        UserService userService = new UserService();

        assertFalse(userService.authenticate("admin", "wrong"));
    }

    @Test
    @DisplayName("Wrong username and password should fail authentication")
    void testAuthenticateWrongCredentials() {
        UserService userService = new UserService();

        assertFalse(userService.authenticate("user", "wrong"));
    }
}