package Domen;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @org.junit.jupiter.api.Test
    void getName() {
        Product p = new Product("cheetos", 60, 10);
        assertEquals("cheetos", p.getName());
    }

    @org.junit.jupiter.api.Test
    void setName() {
        Product p = new Product("cheetos", 60, 10);
        p.setName("Crisps");
        assertEquals("Crisps", p.getName());
    }

    @org.junit.jupiter.api.Test
    void getPrice() {
    }

    @org.junit.jupiter.api.Test
    void setPrice() {
    }

    @org.junit.jupiter.api.Test
    void getAmount() {
    }

    @org.junit.jupiter.api.Test
    void setAmount() {
    }

    @org.junit.jupiter.api.Test
    void testToString() {
    }
}