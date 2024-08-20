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

    public double calculateTax(Product product) {
        double taxRate = 0.0;
        if (product.getState().equals("CA")) {
            if (!CA_EXEMPT_CATEGORIES.contains(product.getCategory().toLowerCase())) {
                taxRate = CA_TAX_RATE;
            }
        } else if (product.getState().equals("NY")) {
            if (!NY_EXEMPT_CATEGORIES.contains(product.getCategory().toLowerCase())) {
                taxRate = NY_TAX_RATE;
            }
        }
        return roundUpToNearest05(product.getTotalPrice() * taxRate);
    }

    private double roundUpToNearest05(double amount) {
        BigDecimal bd = new BigDecimal(amount);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        bd = customRound(bd);
        return bd.doubleValue();
    }

    private BigDecimal customRound(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            return amount;
        }

        BigDecimal scaled = amount.setScale(2, RoundingMode.HALF_UP);
        int lastDigit = scaled.movePointRight(2).remainder(BigDecimal.TEN).intValue(); // 获取小数点后第二位数字

        if (lastDigit >= 1 && lastDigit <= 4) {
            scaled = scaled.setScale(1, RoundingMode.DOWN).add(new BigDecimal("0.05"));
        } else if (lastDigit >= 5) {
            scaled = scaled.setScale(1, RoundingMode.UP);
        }

        return scaled.setScale(2, RoundingMode.HALF_UP);
    }

    public String printReceipt(List<Product> products) {
        StringBuilder receipt = new StringBuilder();
        double total = 0.0;

        receipt.append("list:\n");
        for (Product product : products) {
            double tax = calculateTax(product);
            double subtotal = product.getTotalPrice() + tax;
            total += subtotal;
            receipt.append(String.format("name:%s qty:%d: price:$%.2f subTotal:$%.2f (tax: $%.2f)\n",
                    product.getName(), product.getQuantity(), product.getPrice(), subtotal, tax));
        }

        receipt.append(String.format("total: $%.2f\n", total));
        return receipt.toString();
    }
}
