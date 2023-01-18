package com.dany.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dany.app.entity.User;
import com.dany.app.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userSr;
	
	@PostMapping
	public ResponseEntity<?> create (@RequestBody User user){
		return ResponseEntity.status(HttpStatus.CREATED).body(userSr.save(user));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read (@PathVariable (value = "id") Long userId){
		Optional<User> opUser = userSr.findByid(userId);
		if(!opUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(opUser);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update (@RequestBody User userDetails, @PathVariable(value = "id") Long userId){
		Optional<User> user = userSr.findByid(userId);
		
		if(!user.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		user.get().setName(userDetails.getName());
		user.get().setSurname(userDetails.getSurname());
		user.get().setEmail(userDetails.getEmail());
		user.get().setEnabled(userDetails.getEnabled());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userSr.save(user.get()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable (value = "id") Long userId){
		
		if (!userSr.findByid(userId).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		userSr.deleteById(userId);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<User> readAll() {
		
		List<User> user = StreamSupport.stream(userSr.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return user;
	}
}
