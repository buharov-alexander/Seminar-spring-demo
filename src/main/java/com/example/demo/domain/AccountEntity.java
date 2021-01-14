package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "accounts")
public class AccountEntity {

	@Id
	private Integer id;
	private String name;
	private int balance;
}
