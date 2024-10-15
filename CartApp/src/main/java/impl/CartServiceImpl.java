@Service
public class CartServiceImpl implements CartService {

    private Map<String, Cart> carts = new HashMap<>();

    @Override
    public Cart createCart() {
        Cart cart = new Cart();
        carts.put(cart.getId(), cart);
        return cart;
    }

    @Override
    public void addProductToCart(String cartId, Product product) {
        Cart cart = carts.get(cartId);
        if (cart != null) {
            cart.getProducts().add(product);
            cart.setLastUpdated(LocalDateTime.now());
        }
    }

    @Override
    public Cart getCart(String cartId) {
        return carts.get(cartId);
    }

    @Override
    public void deleteCart(String cartId) {
        carts.remove(cartId);
    }

    @Override
    @Scheduled(fixedRate = 60000)
    public void removeInactiveCarts() {
        LocalDateTime now = LocalDateTime.now();
        carts.values().removeIf(cart -> Duration.between(cart.getLastUpdated(), now).toMinutes() >= 10);
    }
    
}
