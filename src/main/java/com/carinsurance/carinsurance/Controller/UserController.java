package com.carinsurance.carinsurance.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carinsurance.carinsurance.Repository.UserRepository;
import com.carinsurance.carinsurance.model.Customer;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	// Get all users
	@GetMapping
	public List<Customer> getAllUsers() {
		return userRepository.findAll();
	}

	// Get a user by ID
	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> getUserById(@PathVariable Long customerId) {
		Optional<Customer> customer = userRepository.findById(customerId);
		return customer.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	// Create a new user
	@PostMapping
	public ResponseEntity<Customer> createUser(@RequestBody Customer user) {
		Customer savedUser = userRepository.save(user);
		return ResponseEntity.ok(savedUser);
	}

	// Update an existing user
	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateUser(@PathVariable Long id, @RequestBody Customer updatedUser) {
		Optional<Customer> existingUser = userRepository.findById(id);
		if (existingUser.isPresent()) {
			Customer user = existingUser.get();
			user.setName(updatedUser.getName());
			user.setEmail(updatedUser.getEmail());
			user.setPhone(updatedUser.getPhone());
			Customer savedUser = userRepository.save(user);
			return ResponseEntity.ok(savedUser);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Delete a user
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}

