package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import main.najah.code.Product;

@DisplayName("Test Product Class")
@Execution(ExecutionMode.CONCURRENT)
public class TestProduct {

    @Test
    @DisplayName("Create product with valid name and price")
    void testValidProduct() {
        Product product = new Product("Laptop", 1000);

        assertAll("Check valid product",
                () -> assertEquals("Laptop", product.getName()),
                () -> assertEquals(1000.0, product.getPrice()),
                () -> assertEquals(0.0, product.getDiscount()));
    }

    @Test
    @DisplayName("Create product with negative price should throw exception")
    void testNegativePrice() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product("Tablet", -100);
        });

        assertEquals("Price must be non-negative", exception.getMessage());
    }

    @Test
    @DisplayName("Apply valid discount successfully")
    void testApplyValidDiscount() {
        Product product = new Product("Phone", 500);
        product.applyDiscount(20);

        assertAll("Check applied discount",
                () -> assertEquals(20.0, product.getDiscount()),
                () -> assertEquals(400.0, product.getFinalPrice()));
    }

    @Test
    @DisplayName("Apply negative discount should throw exception")
    void testNegativeDiscount() {
        Product product = new Product("Mouse", 100);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            product.applyDiscount(-10);
        });

        assertEquals("Invalid discount", exception.getMessage());
    }

    @Test
    @DisplayName("Apply discount greater than 50 should throw exception")
    void testDiscountGreaterThanFifty() {
        Product product = new Product("Keyboard", 200);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            product.applyDiscount(60);
        });

        assertEquals("Invalid discount", exception.getMessage());
    }

    @Test
    @DisplayName("Final price should remain same when discount is zero")
    void testFinalPriceWithoutDiscount() {
        Product product = new Product("Monitor", 300);

        assertAll("Check final price without discount",
                () -> assertEquals(0.0, product.getDiscount()),
                () -> assertEquals(300.0, product.getFinalPrice()));
    }
}