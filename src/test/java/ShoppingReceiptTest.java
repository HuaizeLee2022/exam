import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shoppingcart.service.ShoppingReceiptService;
import shoppingcart.vo.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingReceiptTest {
    private ShoppingReceiptService receipt;
    private Product product;

    @BeforeEach
    public void setUp() {
        receipt = new ShoppingReceiptService();
    }

    @Test
    public void testCalculateTax_CA_FoodExempt() {
        // 食品在CA免税
        Product apple = new Product("Apple", 1.00, 2, "food");
        double tax = receipt.calculateTax(apple, "CA");
        assertEquals(0.00, tax, 0.01);
    }

    @Test
    public void testCalculateTax_CA_NonExempt() {
        // 非免税商品在CA計算税
        Product laptop = new Product("Laptop", 1000.00, 1, "electronics");
        double tax = receipt.calculateTax(laptop, "CA");
        assertEquals(97.50, tax, 0.01);
    }

    @Test
    public void testCalculateTax_NY_ClothingExempt() {
        //衣服在NY免稅
        product = new Product("T-shirt", 50.00, 1, "clothing");
        double tax = receipt.calculateTax(product, "NY");
        assertEquals(0.00, tax, 0.01);
    }

}
