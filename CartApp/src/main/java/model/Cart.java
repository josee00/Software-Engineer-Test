@Data
public class Cart {
    private String id;
    private List<Product> products;
    private LocalDateTime lastUpdated;

    public Cart() {
        this.id = UUID.randomUUID().toString();
        this.products = new ArrayList<>();
        this.lastUpdated = LocalDateTime.now();
    }
}
