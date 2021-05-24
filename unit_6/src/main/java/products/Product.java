package products;

public class Product {
    private String id;
    private String manufId;
    private String manufName;
    private String name;
    private double price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManufId() {
        return manufId;
    }

    public void setManufId(String manufId) {
        this.manufId = manufId;
    }

    public String getManufName() {
        return manufName;
    }

    public void setManufName(String manufName) {
        this.manufName = manufName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", Manufacturer='" + manufName + '\'' +
                ", product='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
