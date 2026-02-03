package es.upm.grise.profundizacion.order;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderTest {

    private Order order;
    private Product product;

    // ---------- Inicialización ----------
    @BeforeEach
    void setUp() {
        order = new Order();

        product = new Product();
        product.setId(1L);
    }

    // ---------- PR-01 Smoke Test ----------
    @Test
    public void smokeTest() {
        assertTrue(true);
    }

    // ---------- PR-02 Crear Order ----------
    @Test
    public void testCrearOrder() {
        assertNotNull(order);
    }

    // ---------- PR-03 Lista inicial ----------
    @Test
    public void testListaItemsInicialVacia() {
        assertNotNull(order.getItems());
        assertEquals(0, order.getItems().size());
    }

    // ---------- PR-04 Añadir item válido ----------
    @Test
    public void testAddItemValido() {
        Item item = new ItemImpl(product, 10.0, 2);

        order.addItem(item);

        assertEquals(1, order.getItems().size());
    }

    // ---------- PR-05 Precio negativo ----------
    @Test
    public void testAddItemPrecioNegativo() {
        Item item = new ItemImpl(product, -5.0, 2);

        assertThrows(IncorrectItemException.class, () -> {
            order.addItem(item);
        });
    }

    // ---------- PR-06 Cantidad cero ----------
    @Test
    public void testAddItemCantidadCero() {
        Item item = new ItemImpl(product, 10.0, 0);

        assertThrows(IncorrectItemException.class, () -> {
            order.addItem(item);
        });
    }

    // ---------- PR-07 Cantidad negativa ----------
    @Test
    public void testAddItemCantidadNegativa() {
        Item item = new ItemImpl(product, 10.0, -3);

        assertThrows(IncorrectItemException.class, () -> {
            order.addItem(item);
        });
    }

    // ---------- PR-08 Mismo producto, mismo precio ----------
    @Test
    public void testMismoProductoMismoPrecio() {
        Item item1 = new ItemImpl(product, 20.0, 2);
        Item item2 = new ItemImpl(product, 20.0, 3);

        order.addItem(item1);
        order.addItem(item2);

        assertEquals(1, order.getItems().size());

        Iterator<Item> it = order.getItems().iterator();
        Item storedItem = it.next();

        assertEquals(5, storedItem.getQuantity());
    }

    // ---------- PR-09 Mismo producto, distinto precio ----------
    @Test
    public void testMismoProductoDistintoPrecio() {
        Item item1 = new ItemImpl(product, 20.0, 2);
        Item item2 = new ItemImpl(product, 25.0, 1);

        order.addItem(item1);
        order.addItem(item2);

        assertEquals(2, order.getItems().size());
    }

    // ---------- PR-10 Item nulo (robustez) ----------
    @Test
    public void testAddItemNulo() {
        assertThrows(RuntimeException.class, () -> {
            order.addItem(null);
        });
    }
}
