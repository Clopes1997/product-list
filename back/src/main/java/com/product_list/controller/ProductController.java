package com.product_list.controller;

import com.product_list.repository.ProductRepository;
import com.product_list.model.Product;
import com.product_list.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;
import java.util.Comparator;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*") // Adjust as needed for Angular
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/filter")
    public List<Product> filterProducts(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String sortBy
    ) {
        List<Product> products = productRepository.findAll();

        if (name != null && !name.isEmpty()) {
            products = products.stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        }

        if ("price".equals(sortBy)) {
            products.sort(Comparator.comparing(Product::getPrice));
        } else if ("name".equals(sortBy)) {
            products.sort(Comparator.comparing(Product::getName));
        }

        return products;
    }


    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updated = productService.updateProduct(id, product);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}