@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<Cart> createCart() {
        Cart cart = cartService.createCart();
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @PostMapping("/{cartId}/products")
    public ResponseEntity<Void> addProduct(@PathVariable String cartId, @RequestBody Product product) {
        cartService.addProductToCart(cartId, product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable String cartId) {
        Cart cart = cartService.getCart(cartId);
        if (cart != null) {
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable String cartId) {
        cartService.deleteCart(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}