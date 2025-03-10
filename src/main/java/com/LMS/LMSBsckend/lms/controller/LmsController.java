package com.LMS.LMSBsckend.lms.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LmsController {

	@GetMapping("/public/get")
	public String Login() {
		return "publi Hello Login LMS Spring";	
	}
	@GetMapping("/admin/get")
	public String admin() {
		return "Admin Login LMS Spring";	
	}
	@GetMapping("/user/get")
	public String user() {
		return "User Login LMS Spring";	
	}
}
