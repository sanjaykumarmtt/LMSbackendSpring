package com.LMS.LMSBsckend.Login.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.LMS.LMSBsckend.Login.Entity.LOginDto;
import com.LMS.LMSBsckend.Login.Service.LOgService;
import com.LMS.LMSBsckend.Login.Service.getLoginservice;

@RestController
@RequestMapping("/Login")
@CrossOrigin
public class Logincontroller {

	@Autowired
	LOgService LOgService;
	@Autowired
	getLoginservice Loginservice;

	@GetMapping("/get")
	public String Login() {
		return "Login LMS Spring";
	}

	@PostMapping("/send_Otp")
	public ResponseEntity<String> sendOTP(@RequestParam String email, String purpose) {
//		 System.out.println(email +" sedrtfg " +purpose);
		LOgService.sendOTP(email, purpose);
		return ResponseEntity.ok("OTP sent successfully");
	}

	@PostMapping("/verify_OTP")
	public ResponseEntity<String> verifyOTP(@RequestParam String otp) {
		if (LOgService.verifyOTP(otp)) {
			return ResponseEntity.ok("OTP verified successfully");
		}
		return ResponseEntity.badRequest().body("Invalid or expired OTP");
	}

	@PostMapping("/public/register")
	public ResponseEntity<String> Register(@RequestBody LOginDto LOginDto) {
		LOgService.Register(LOginDto);
		return ResponseEntity.ok().body("Register Successful go to Login");
	}

	@PostMapping("/public/Login")
	public ResponseEntity<String> LOgin(@RequestBody LOginDto LOginDto) {
		return ResponseEntity.ok().body(Loginservice.varicati(LOginDto));
	}
}
//		 System.out.println(LOginDto.getName());
//System.out.println(LOginDto.getOtp());
//System.out.println(LOginDto.getRole());
