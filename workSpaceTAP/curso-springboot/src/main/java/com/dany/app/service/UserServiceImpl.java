package com.dany.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dany.app.entity.User;
import com.dany.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<User> findAll() {
		
		return userRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<User> findAll(Pageable pageable) {
		
		return userRepo.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findByid(Long id) {
		
		return userRepo.findById(id);
	}

	@Override
	@Transactional
	public User save(User user) {
		
		return userRepo.save(user);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		userRepo.deleteById(id);
		
	}

	
}
