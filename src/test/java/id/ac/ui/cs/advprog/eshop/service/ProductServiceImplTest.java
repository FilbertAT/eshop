package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testCreate() {
        Product product = new Product();
        Product result = productService.create(product);

        verify(productRepository, times(1)).create(product);
        assertEquals(product, result);
    }

    @Test
    void testFindAll() {
        Product product1 = new Product();
        Product product2 = new Product();

        List<Product> productList = Arrays.asList(product1, product2);
        Iterator<Product> iterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = productService.findAll();

        assertEquals(2, result.size());
        assertTrue(result.contains(product1));
        assertTrue(result.contains(product2));
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Product product = new Product();
        String productId = "123";

        when(productRepository.findById(productId)).thenReturn(product);

        Product result = productService.findById(productId);

        assertNotNull(result);
        assertEquals(product, result);
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testUpdate() {
        Product product = new Product();

        when(productRepository.update(product)).thenReturn(product);

        Product updatedProduct = productService.update(product);

        assertNotNull(updatedProduct);
        assertEquals(product, updatedProduct);
        verify(productRepository, times(1)).update(product);
    }

    @Test
    void testDelete() {
        String productId = "123";

        when(productRepository.delete(productId)).thenReturn(true);

        boolean isDeleted = productService.delete(productId);

        assertTrue(isDeleted);
        verify(productRepository, times(1)).delete(productId);
    }
}
