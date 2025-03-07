package id.ac.ui.cs.advprog.eshop.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;

@Repository
public class PaymentRepository {
    private List<Payment> paymentData = new ArrayList<>();

    public Payment save(Payment payment) {
        // Implement this method
        return null;
    }

    public List<Payment> findAll() {
        // Implement this method
        return null;
    }
    
    public Payment findById(String id) {
        // Implement this method
        return null;
    }
}