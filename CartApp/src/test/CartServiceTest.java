@SpringBootTest
public class CartServiceTest {

    @Autowired
    private CartService cartService;

    @Test
    public void testCreateCart() {
        Cart cart1 = cartService.createCart();
        Cart cart2 = cartService.createCart();

        assertNotNull(cart1);
        assertNotNull(cart2);
        assertNotEquals(cart1.getId(), cart2.getId()); 
    }
}

@Test
public void testAddProductToCart() {
    Cart cart = cartService.createCart();
    Product product = new Product(1, "Laptop", 2);

    cartService.addProductToCart(cart.getId(), product);

    assertEquals(1, cart.getProducts().size());
    assertEquals("Laptop", cart.getProducts().get(0).getDescription());
}

@Test
public void testCartDeletionAfterInactivity() throws InterruptedException {
    Cart cart = cartService.createCart();

    Thread.sleep(11000);

    cartService.removeInactiveCarts();

    assertNull(cartService.getCart(cart.getId()));
}

@Test
public void testGetCartById() {
    Cart cart = cartService.createCart();
    Product product = new Product(1, "Phone", 1);
    cartService.addProductToCart(cart.getId(), product);

    Cart retrievedCart = cartService.getCart(cart.getId());

    assertNotNull(retrievedCart); 
    assertEquals(1, retrievedCart.getProducts().size());
}

@Test
public void testDeleteCart() {
    Cart cart = cartService.createCart();
    cartService.deleteCart(cart.getId());

    assertNull(cartService.getCart(cart.getId()));
}

