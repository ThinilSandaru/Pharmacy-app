package com.example.Pharmacy.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class RoleController {

@GetMapping("public/hello")
public String publicHello() {
	return "Hello from Public!";
}

@GetMapping("user/hello")
public String userHello() {
	return "Hello USER!";
}

@GetMapping("admin/hello")
public String adminHello() {
	return "Hello ADMIN!";
}

@GetMapping("pharmacy/hello")
public String pharmacyHello() {
	return "Hello PHARMACY!";
}
}
