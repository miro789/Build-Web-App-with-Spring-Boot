package com.example.demo.user;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface UserHttpClient<USer> {

	@GetExchange("/users")
	List<User> findAll();

	@GetExchange("/users/{id}")
	USer findById(@PathVariable Integer id);

}
