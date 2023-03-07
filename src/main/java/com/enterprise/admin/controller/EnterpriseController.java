package com.enterprise.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnterpriseController {
	@GetMapping("/list")
	public String getList() {
		return "Hello world";
	}
}
