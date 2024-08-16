package shoppingcart;

import shoppingcart.service.ShoppingReceiptService;
import shoppingcart.vo.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class shoppingCartApplication {
    private final static String category = "food";
    private final static String state ="CA";
    public static void main(String[] args) {

        List<Product> products = new ArrayList<Product>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("請輸入購買的商品數量：");
        int numProducts = getValidIntegerInput(scanner);

        for (int i = 0; i < numProducts; i++) {
            System.out.println("輸入商品名稱：");
            String name = scanner.nextLine().trim();

            System.out.println("輸入商品價格：");
            double price = getValidDoubleInput(scanner);

            System.out.println("輸入購買數量：");
            int quantity = getValidIntegerInput(scanner);
            products.add(new Product(name, price, quantity, category));
        }


        ShoppingReceiptService receipt = new ShoppingReceiptService();
        String receiptText = receipt.printReceipt(products, state);
        System.out.println(receiptText);
    }


    private static int getValidIntegerInput(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                int value = Integer.parseInt(input);
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("請輸入一個正整數：");
                }
            } catch (NumberFormatException e) {
                System.out.println("輸入無效，請輸入一個整數：");
            }
        }
    }

    private static double getValidDoubleInput(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                double value = Double.parseDouble(input);
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("請輸入一個正數：");
                }
            } catch (NumberFormatException e) {
                System.out.println("輸入無效，請輸入一個數字：");
            }
        }
    }
}

