package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.najah.code.Calculator;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Test Calculator")
public class TestCalculator {

    private Calculator calculator;

    @BeforeAll
    static void beforeAllTests() {
        System.out.println("Starting Calculator tests");
    }

    @AfterAll
    static void afterAllTests() {
        System.out.println("Finished Calculator tests");
    }

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
        System.out.println("setup complete");
    }

    @AfterEach
    void tearDown() {
        System.out.println("test finished");
    }

    @Test
    @Order(1)
    @DisplayName("Add multiple numbers successfully")
    void testAdd() {
        int result = calculator.add(1, 2, 3);

        assertAll(
            () -> assertEquals(6, result),
            () -> assertNotEquals(5, result)
        );
    }

    @Test
    @Order(2)
    @DisplayName("Add with no numbers should return zero")
    void testAddEmpty() {
        int result = calculator.add();

        assertAll(
            () -> assertEquals(0, result),
            () -> assertTrue(result >= 0)
        );
    }

    @ParameterizedTest
    @CsvSource({
        "1, 2, 3, 6",
        "5, 5, 5, 15",
        "-1, 1, 0, 0"
    })
    @Order(3)
    @DisplayName("Parameterized add test")
    void testAddParameterized(int a, int b, int c, int expected) {
        assertEquals(expected, calculator.add(a, b, c));
    }

    @Test
    @Order(4)
    @DisplayName("Divide two numbers successfully")
    void testDivide() {
        int result = calculator.divide(10, 2);

        assertAll(
            () -> assertEquals(5, result),
            () -> assertTrue(result > 0)
        );
    }

    @Test
    @Order(5)
    @DisplayName("Divide by zero should throw ArithmeticException")
    void testDivideByZero() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            calculator.divide(10, 0);
        });

        assertEquals("Cannot divide by zero", exception.getMessage());
    }

    @Test
    @Order(6)
    @DisplayName("Factorial of positive number")
    void testFactorialPositive() {
        int result = calculator.factorial(5);

        assertAll(
            () -> assertEquals(120, result),
            () -> assertTrue(result > 0)
        );
    }

    @Test
    @Order(7)
    @DisplayName("Factorial of zero should be one")
    void testFactorialZero() {
        int result = calculator.factorial(0);

        assertAll(
            () -> assertEquals(1, result)
        );
    }

    @Test
    @Order(8)
    @DisplayName("Factorial with negative input should throw exception")
    void testFactorialNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.factorial(-1);
        });

        assertEquals("Negative input", exception.getMessage());
    }

    @Test
    @Order(9)
    @DisplayName("Calculator methods should finish quickly")
    void testTimeout() {
        assertTimeout(Duration.ofMillis(500), () -> {
            calculator.add(1, 2, 3);
            calculator.divide(10, 2);
            calculator.factorial(5);
        });
    }

    @Disabled("Intentionally disabled failing test. divide(10,0) throws ArithmeticException, so fix by using assertThrows instead.")
    @Test
    @Order(10)
    @DisplayName("Disabled failing divide test")
    void testDisabledFailingCase() {
        assertEquals(0, calculator.divide(10, 0));
    }
}