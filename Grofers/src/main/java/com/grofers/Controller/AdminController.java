package com.grofers.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grofers.Entity.Category;
import com.grofers.Entity.Product;
import com.grofers.Entity.Supplier;
import com.grofers.Entity.User;
import com.grofers.Repository.CategoryRepository;
import com.grofers.Repository.OrderRepository;
import com.grofers.Repository.ProductRepository;
import com.grofers.Repository.SupplierRepository;
import com.grofers.Repository.UserRepository;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	// Add new suppliers
	@PostMapping("/suppliers")
	public ResponseEntity<Supplier> addSupplier(@RequestBody Supplier supplier) {
		Supplier savedSupplier = supplierRepository.save(supplier);
		return ResponseEntity.ok(savedSupplier);
	}

	// Add new products for a particular supplier
	@PostMapping("/products/{supplierId}")
	public ResponseEntity<Product> addProduct(@PathVariable Long supplierId, @RequestBody Product product) {
		Supplier supplier = supplierRepository.findById(supplierId)
				.orElseThrow(() -> new RuntimeException("Supplier not found"));
		product.setSupplier(supplier);
		Product savedProduct = productRepository.save(product);
		return ResponseEntity.ok(savedProduct);
	}

	// Add new categories
	@PostMapping("/categories")
	public ResponseEntity<Category> addCategory(@RequestBody Category category) {
		Category savedCategory = categoryRepository.save(category);
		return ResponseEntity.ok(savedCategory);
	}

	// Add new users (admin)
	@PostMapping("/users/admin")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User savedUser = userRepository.save(user);
		return ResponseEntity.ok(savedUser);
	}

	// Delete an order (admin)
	@DeleteMapping("/orders/{orderId}/admin")
	public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
		orderRepository.deleteById(orderId);
		return ResponseEntity.ok().build();
	}

	// Fetch all users (admin)
	@GetMapping("/users/admin")
	public ResponseEntity<List<User>> fetchAllUsers() {
		List<User> users = userRepository.findAll();
		return ResponseEntity.ok(users);
	}

	// Fetch all products (admin)
	@GetMapping("/products/admin")
	public ResponseEntity<List<Product>> fetchAllProducts() {
		List<Product> products = productRepository.findAll();
		return ResponseEntity.ok(products);
	}

	// Fetch all categories (admin)
	@GetMapping("/categories/admin")
	public ResponseEntity<List<Category>> fetchAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		return ResponseEntity.ok(categories);
	}

	
}
