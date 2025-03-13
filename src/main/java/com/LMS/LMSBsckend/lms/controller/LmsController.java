package com.LMS.LMSBsckend.lms.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.LMS.LMSBsckend.lms.Entity.Course_det;
import com.LMS.LMSBsckend.lms.Service.LmsService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LmsController {

	@Autowired
	LmsService LmsService;
	
	@PostMapping("/public/postCoruse_data")
	public ResponseEntity<String> Coruse_data(@RequestPart("fail") MultipartFile fail,
			                                  @RequestPart("Course") Course_det Course_det) throws IOException {
		System.out.println(fail.getOriginalFilename());
		System.out.println(Course_det.getCourse_name());
		LmsService.Save_Corse_data(Course_det, fail); 
		return ResponseEntity.ok().body("Save Successful Data");	
	}
	
	
	@GetMapping("/public/getCoruse_data") // get All Images Overall_dataEnt
	public ResponseEntity<List<Map<String, Object>>> GetAllcorude() throws IOException {
		return ResponseEntity.ok(LmsService.GetAllcourse());
	}

	@GetMapping("/admin/get")
	public String admin() {
		return "Admin Login LMS Spring";	
	}
	@GetMapping("/user/get")
	public String user() {
		return "User1 Login LMS Spring";	
	}
	@GetMapping("/user2/get")
	public String user1() {
		return "User2 Login LMS Spring";	
	}
}
