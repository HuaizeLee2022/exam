package shoppingcart.vo;

public class Product {
    //商品名稱
    String name;
    //商品金額
    double price;
    //商品數量
    int quantity;
    //商品類別
    String category;


    String state;

    public Product(String name, double price, int quantity, String category,String state) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.state = state;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public double getTotalPrice() {
        return price * quantity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
