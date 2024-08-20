import shoppingcart.service.ShoppingReceiptService;
import shoppingcart.vo.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class shoppingCartApplication {
    private final static String category = "food";

    public static void main(String[] args) {

        List<Product> products = new ArrayList<Product>();
        Product apple = new Product("Apple", 1.0, 10, category,"CA");
        products.add(apple);
        Product shirt = new Product("Shirt", 20.0, 1, category,"NY");
        products.add(shirt);
        Product cap = new Product("Cap", 200.0, 2, category,"CA");
        products.add(cap);
        ShoppingReceiptService receipt = new ShoppingReceiptService();
        String receiptText = receipt.printReceipt(products);
        System.out.println(receiptText);
    }

}

