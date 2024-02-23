package com.grofers.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grofers.Entity.Orders;
import com.grofers.Entity.Product;
import com.grofers.Entity.User;
import com.grofers.Exception.UserRegistrationException;
import com.grofers.Repository.OrderRepository;
import com.grofers.Repository.UserRepository;
import com.grofers.config.JwtHelper;
import com.grofers.config.JwtRequest;
import com.grofers.config.JwtResponse;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private JwtHelper jwtHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private void doAuthenticate(String username, String password) {
		Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
		try {
			manager.authenticate(authentication);

		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(" Invalid Username or Password  !!");
		}
	}

	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionHandler() {
		return "Credentials Invalid !!";
	}

	// Register a new user
	@PostMapping("/")
	public ResponseEntity<String> registerUser(@RequestBody User user) {
		// Example check that might throw a custom exception
		if (userRepository.findByUsername(user.getUsername()).isPresent()) {
			throw new UserRegistrationException("Username already exists");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return ResponseEntity.ok("User Registered Successfully");
	}

	// Authenticate a user and return JWT
	@PostMapping("/auth/login")
	public JwtResponse login(@RequestBody JwtRequest request) {
		doAuthenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		String token = jwtHelper.generateToken(userDetails);
		return new JwtResponse(userDetails.getUsername(), token);
	}

	// Allow a user to place an order
	@PostMapping("/orders/{userId}")
	@PreAuthorize("hasAuthority('USER') and #userId == authentication.principal.id")
	public ResponseEntity<Orders> placeOrder(@PathVariable Long userId, @RequestBody Orders order) {
		Orders savedOrder = orderRepository.save(order);
		return ResponseEntity.ok().body(savedOrder);
	}

	// Allow a user to add products to an order
	@PutMapping("/orders/{orderId}/products")
	public ResponseEntity<Orders> addProductsToOrder(@PathVariable Long orderId, @RequestBody List<Long> productIds) {
		Orders updatedOrder = new Orders();
		return ResponseEntity.ok().body(updatedOrder);
	}

	// Allow a user to update the order details
	@PutMapping("/orders/{orderId}")
	public ResponseEntity<Orders> updateOrder(@PathVariable Long orderId, @RequestBody Orders orderDetails) {
		Orders updatedOrder = new Orders();
		return ResponseEntity.ok().body(updatedOrder);
	}

	// Fetch a user's order history
	@GetMapping("/users/{userId}/orders")
	@PreAuthorize("hasAuthority('USER') and #userId == authentication.principal.id")
	public ResponseEntity<List<Orders>> fetchUserOrderHistory(@PathVariable Long userId) {
		List<Orders> orderHistory = new ArrayList<>();
		return ResponseEntity.ok().body(orderHistory);
	}

	// Recommend products to a user based on their order history
	@GetMapping("/users/{userId}/recommended-products")
	@PreAuthorize("authentication.principal.username == #userId")
	public ResponseEntity<List<Product>> recommendProducts(@PathVariable Long userId) {
		List<Product> recommendedProducts = new ArrayList<>();
		return ResponseEntity.ok().body(recommendedProducts);
	}

}
