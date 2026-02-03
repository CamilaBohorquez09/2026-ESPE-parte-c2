package es.upm.grise.profundizacion.order;

public class ItemImpl implements Item {

    private Product product;
    private double price;
    private int quantity;

    public ItemImpl(Product product, double price, int quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int i) {
        this.quantity = i;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
