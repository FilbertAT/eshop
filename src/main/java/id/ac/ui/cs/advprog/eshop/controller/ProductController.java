package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController extends AbstractItemController<Product> {
    
    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        super(
            service,
            "redirect:list",
            "createProduct",
            "productList",
            "editProduct",
            "product",
            "products"
        );
        this.service = service;
    }

    @GetMapping("/create")
    public String createProductPage(Model model) {
        return createPage(model, new Product());
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return "redirect:list";
    }

    @GetMapping ("/list")
    public String productListPage(Model model) {
        return listPage(model);
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(@PathVariable("id") String id, Model model) {
        Product product = service.findById(id);
        
        if (product == null) {
            // Use "../list" for the redirect instead of just "list"
            return "redirect:../list";
        }
        
        model.addAttribute("product", product);
        return "EditProduct";
    }

    @PostMapping("/edit")
    public String editProductPost(@ModelAttribute Product product, Model model) {
        service.update(product);
        return "redirect:list";
    }

    @PostMapping("/delete")
    public String deleteProduct(@RequestParam("id") String productId) {
        return delete(productId);
    }
    
}