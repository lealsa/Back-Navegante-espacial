package com.example.primeraEntrega.service;

import com.example.primeraEntrega.model.Producto;
import com.example.primeraEntrega.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Producto> findAllProducts() {
        return productRepository.findAll();
    }

    public Producto findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Producto saveProduct(Producto producto) {
        return productRepository.save(producto);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
