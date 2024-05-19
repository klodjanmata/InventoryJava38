package entities;

public class ProductEntity {

    private int id;
    private String name;
    private String type;
    private int quantity;
    private double price;
    private int categoryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public ProductEntity(int id, String name, String type, int quantity, double price, int categoryId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", categoryId=" + categoryId +
                '}';
    }
}
