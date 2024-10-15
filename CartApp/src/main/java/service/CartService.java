public interface CartService {
    Cart createCart();
    void addProductToCart(String cartId, Product product);
    Cart getCart(String cartId);
    void deleteCart(String cartId);
    void removeInactiveCarts();
}
