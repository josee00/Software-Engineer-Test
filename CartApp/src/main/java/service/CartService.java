@Service
public class CartService {
    private Map<String, Cart> carts = new HashMap<>();

    public Cart createCart() {
        Cart cart = new Cart();
        carts.put(cart.getId(), cart);
        return cart;
    }


    public void addProductToCart(String cartId, Product product) {
        Cart cart = carts.get(cartId);
        if (cart != null) {
            cart.getProducts().add(product);
            cart.setLastUpdated(LocalDateTime.now());
        }
    }


    public Cart getCart(String cartId) {
        return carts.get(cartId);
    }


    public void deleteCart(String cartId) {
        carts.remove(cartId);
    }

    @Scheduled(fixedRate = 60000) 
    public void removeInactiveCarts() {
        LocalDateTime now = LocalDateTime.now();
        carts.values().removeIf(cart -> Duration.between(cart.getLastUpdated(), now).toMinutes() >= 10);
    }
}
