package com.example.primeraEntrega.controller;

import com.example.primeraEntrega.model.Producto;
import com.example.primeraEntrega.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Producto> getAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductById(@PathVariable Long id) {
        Producto producto = productService.findProductById(id);
        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public Producto createProduct(@RequestBody Producto producto) {
        return productService.saveProduct(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProduct(@PathVariable Long id, @RequestBody Producto productDetails) {
        Producto producto = productService.findProductById(id);
        producto.setNombre(productDetails.getNombre());
        producto.setVolumen(productDetails.getVolumen());
        producto.setImagen(productDetails.getImagen());
        final Producto updatedProduct = productService.saveProduct(producto);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
