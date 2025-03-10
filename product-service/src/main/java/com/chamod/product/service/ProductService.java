package com.chamod.product.service;

import com.chamod.product.entity.Product;
import com.chamod.product.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepo.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setInStock(product.isInStock());

        return productRepo.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }

    public List<Product> searchProducts(String name, String category, Double minPrice, Double maxPrice) {
        List<Product> products = productRepo.findAll();

        return products.stream()
                .filter(p -> name == null || p.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(p -> category == null || p.getCategory().equalsIgnoreCase(category))
                .filter(p -> minPrice == null || p.getPrice() >= minPrice)
                .filter(p -> maxPrice == null || p.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    public Map<String, Object> getProductStatistics() {
        List<Product> products = productRepo.findAll();

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalProducts", products.size());
        stats.put("productsInStock", products.stream().filter(Product::isInStock).count());
        stats.put("averagePrice", products.stream().mapToDouble(Product::getPrice).average().orElse(0.0));

        Map<String, Long> categoryDistribution = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
        stats.put("categoryDistribution", categoryDistribution);

        return stats;
    }
}
