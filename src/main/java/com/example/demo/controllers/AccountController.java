package com.example.demo.controllers;

import com.example.demo.domain.AccountEntity;
import com.example.demo.domain.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
public class AccountController {

	@Autowired
	private AccountRepository repository;

	@RequestMapping("/account/{name}")
	public AccountEntity getAccount(@PathVariable("name") String name) {
		return repository.findByName(name);
	}
}
