package id.ac.ui.cs.advprog.eshop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaymentTest {
    private Order order;
    private Map<String, String> paymentData;
    
    @BeforeEach
    void setUp() {
        // Create a sample order
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("dd4a040e-f651-466b-86a2-f8d5ff6ce78a");
        product.setProductName("Dummy Product");
        product.setProductQuantity(5);
        products.add(product);
        
        this.order = new Order("0bb54544-f4de-4a38-a709-f0b070e112fe", products, 1821000000L, "Dummy User");
        
        // Create sample payment data
        this.paymentData = new HashMap<>();
        this.paymentData.put("voucherCode", "ESHOP12345678ABC");
    }
    
    @Test
    void testCreatePayment() {
        Payment payment = new Payment("dummy-payment-id", order, "VOUCHER", paymentData);
        
        assertEquals("dummy-payment-id", payment.getId());
        assertEquals(order, payment.getOrder());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals("PENDING", payment.getStatus()); // Default status
    }
    
    @Test
    void testSetStatus() {
        Payment payment = new Payment("dummy-payment-id", order, "VOUCHER", paymentData);
        payment.setStatus("SUCCESS");
        
        assertEquals("SUCCESS", payment.getStatus());
    }
}