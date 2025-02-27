package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import id.ac.ui.cs.advprog.eshop.service.IdGeneratorService;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    @Autowired
    private IdGeneratorService idGeneratorService;

    public Product create(Product product) {
        if (product.getProductId() == null) {
            product.setProductId(idGeneratorService.generateId());
        }
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(String productId) {
        for (Product product : productData) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null; // if the product is not found
    }

    public Product update(Product updatedProduct) {
        for (Product product : productData) {
            if (product.getProductId().equals(updatedProduct.getProductId())) {
                product.setProductName(updatedProduct.getProductName());
                product.setProductQuantity(updatedProduct.getProductQuantity());
                return product;
            }
        }
        return null; // if the product is not found
    }

    public boolean delete(String productId) {
        Iterator<Product> iterator = productData.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductId() != null && product.getProductId().equals(productId)) {
                iterator.remove();
                return true;  // if the product is Successfully deleted
            }
        }
        return false;  // if the product is not found
    }
    
}
