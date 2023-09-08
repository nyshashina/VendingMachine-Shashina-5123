package Services;

import Domen.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HolderTest {
    Holder h;
    @BeforeEach
    void setUp() {
        h = new Holder(10, 10,10);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addProduct() {
        Exception exception1 = assertThrows(IllegalArgumentException.class,
                () -> new Product("MilkyWay", 50, -1));
        String expectedMessage = "Количество не может быть меньше нуля";
        String actualMessage = exception1.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            Product pr3 = new Product("MilkyWay", 50, 0);
            h.addProduct(pr3, 1, 0);
        });

        Exception exception2 = assertThrows(IllegalArgumentException.class,
                () -> h.addProduct(null, 1, 1));
        expectedMessage = String.format("Продукт для ячейки %d %d не указан (null).", 1, 1);
        actualMessage = exception2.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));


        Product pr1 = new Product("MilkyWay", 50, 10);
        h.addProduct(pr1, 1, 1);
        Product pr2 = new Product("MilkyWay", 50, 10);
        h.addProduct(pr2, 2, 1);
        Product pr3 = new Product("MilkyWay", 50, 0);
        h.addProduct(pr3, 1, 9);

    }

    @Test
    void release() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            h.release(0, 0);
        });

        Exception exception1 = assertThrows(IllegalArgumentException.class,
                () -> h.release(1, 1));
        String expectedMessage = String.format("В ячейке %d %d нет товара.", 1, 1);
        String actualMessage = exception1.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            h.addProduct(new Product("Milky", 1, 0), 1, 1);
            h.release(1,1);
        });
        expectedMessage = String.format("В ячейке %d %d не осталось товара.", 1, 1);
        actualMessage = exception2.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));


        Product pr2 = new Product("MilkyWay", 50, 10);
        h.addProduct(pr2, 2, 1);
        h.release(2,1);
        assertEquals(10 - 1, pr2.getAmount());

    }

    @Test
    void delProduct() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            h.delProduct(0, 0);
        });
        Product pr2 = new Product("MilkyWay", 50, 10);
        h.addProduct(pr2, 2, 1);
        Product[][] prs = h.getProducts();
        assertEquals(pr2, prs[2 - 1][1 - 1]);
        h.delProduct(2,1);
        prs = h.getProducts();
        assertNull(prs[2 - 1][1 - 1]);
    }

    @Test
    void getProducts() {
        Product[][] prs = h.getProducts();
        assertNull(prs[1 - 1][1 - 1]);
        assertNull(prs[2 - 1][1 - 1]);
        assertNull(prs[1 - 1][9 - 1]);
        Product pr1 = new Product("MilkyWay", 50, 10);
        h.addProduct(pr1, 1, 1);
        Product pr2 = new Product("MilkyWay", 50, 10);
        h.addProduct(pr2, 2, 1);
        Product pr3 = new Product("MilkyWay", 50, 0);
        h.addProduct(pr3, 1, 9);
        prs = h.getProducts();
        assertEquals(pr1, prs[1 - 1][1 - 1]);
        assertEquals(pr2, prs[2 - 1][1 - 1]);
        assertEquals(pr3, prs[1 - 1][9 - 1]);
    }

    @Test
    void getRows() {
        h = new Holder(6, 10,10);
        assertEquals(6, h.getRows());
    }

    @Test
    void getColumns() {
        h = new Holder(10, 12,10);
        assertEquals(12, h.getColumns());
    }

    @Test
    void getMaxItemsPerCell() {
        h = new Holder(10, 10,6);
        assertEquals(6, h.getMaxItemsPerCell());
    }
}