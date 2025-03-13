package com.LMS.LMSBsckend.lms.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("/public/Coruse_data")
	public ResponseEntity<String> Coruse_data(@RequestPart("overall") Course_det Course_det,
			  @RequestPart("fail") MultipartFile fail ) throws IOException {
		System.out.println(Course_det.getCourse_name());
//		LmsService.Save_Corse_data(Course_det, fail);
		return ResponseEntity.ok().body("Save Successful Data");	
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
