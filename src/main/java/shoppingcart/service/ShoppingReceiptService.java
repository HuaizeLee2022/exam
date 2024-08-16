package shoppingcart.service;

import shoppingcart.vo.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

public class ShoppingReceiptService {
    private static final double CA_TAX_RATE = 0.0975;
    private static final double NY_TAX_RATE = 0.08875;

    private static final List<String> CA_EXEMPT_CATEGORIES = Arrays.asList("food");
    private static final List<String> NY_EXEMPT_CATEGORIES = Arrays.asList("food", "clothing");

    public double calculateTax(Product product, String state) {
        double taxRate = 0.0;
        if (state.equals("CA")) {
            if (!CA_EXEMPT_CATEGORIES.contains(product.getCategory().toLowerCase())) {
                taxRate = CA_TAX_RATE;
            }
        } else if (state.equals("NY")) {
            if (!NY_EXEMPT_CATEGORIES.contains(product.getCategory().toLowerCase())) {
                taxRate = NY_TAX_RATE;
            }
        }
        return roundUpToNearest05(product.getTotalPrice() * taxRate);
    }

    private double roundUpToNearest05(double amount) {
        BigDecimal bd = new BigDecimal(amount);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public String printReceipt(List<Product> products, String state) {
        StringBuilder receipt = new StringBuilder();
        double total = 0.0;

        receipt.append("購物收據:\n");
        for (Product product : products) {
            double tax = calculateTax(product, state);
            double subtotal = product.getTotalPrice() + tax;
            total += subtotal;
            receipt.append(String.format("%s x%d: $%.2f (稅: $%.2f)\n",
                    product.getName(), product.getQuantity(), subtotal, tax));
        }

        receipt.append(String.format("總計: $%.2f\n", total));
        return receipt.toString();
    }
}
