package com.codesnippet.ecom.Controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codesnippet.ecom.Entity.ErrorResponse;
import com.codesnippet.ecom.Entity.Product;
import com.codesnippet.ecom.Service.ProductService;
import com.codesnippet.ecom.exceptions.ProductNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product/v1")
public class ProductController {
	@Autowired
	ProductService productService;

	@GetMapping("/getProductById/{productId}")
	public ResponseEntity<?> getBookById(@PathVariable("productId") Integer productId) {
		
		Product product = productService.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));
		return ResponseEntity.ok(product);

	}

	@GetMapping("/getProductByName/{name}")
	public ResponseEntity<?> getProductByName(@PathVariable String name) {

		Product product = productService.findByName(name)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with name: " + name));
		return ResponseEntity.ok(product);
	}

	@PostMapping("/addProduct")
	public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) throws InterruptedException {
		// print
		Product createdProduct = productService.addProduct(product);
		return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
	}

	

}
