package com.LMS.LMSBsckend.Login.Service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.LMS.LMSBsckend.Exception.HostelException;
import com.LMS.LMSBsckend.Login.Entity.LOginDto;
import com.LMS.LMSBsckend.Login.Entity.LoginEntity;
import com.LMS.LMSBsckend.Login.Entity.OTP;
import com.LMS.LMSBsckend.Login.Repository.LogRepository;
import com.LMS.LMSBsckend.Login.Repository.OTPRepository;

@Service
public class LOgService implements UserDetailsService {

	@Autowired
	LogRepository LogRepository;
	BCryptPasswordEncoder psenc = new BCryptPasswordEncoder(12);
	@Autowired
	private OTPRepository otpRepository;

	@Autowired
	private JavaMailSender mailSender;

	private final int OTP_EXPIRATION_MINUTES = 5;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LoginEntity loginEntity = LogRepository.getByusername(username);
		if (loginEntity == null) {

			throw new UsernameNotFoundException("User not exist");
		}
		return new userprincipal(loginEntity);
	}

	
	public void Register(LOginDto LOginDto)throws HostelException {
		boolean cheakotp=verifyOTP(LOginDto.getOtp());
		if(cheakotp) {
			LoginEntity loge=new LoginEntity();
			loge.setName(LOginDto.getName());
			loge.setUsername(LOginDto.getUsername());
			loge.setPassword(psenc.encode(LOginDto.getPassword()));
			loge.setRole(LOginDto.getRole());
			LogRepository.save(loge);
		}else {
			throw new HostelException("Invalid or expired OTP");
		}
	}
	//verifyOTP
	 public boolean verifyOTP(String otp) {
	        return otpRepository.findByOtp(otp)
	                .filter(o -> o.getOtp().equals(otp) && o.getExpiryTime().isAfter(LocalDateTime.now()))
	                .isPresent();
	    }
	
	//gen otp meth
	public String generateOTP() {
	      return String.format("%06d", new Random().nextInt(999999));
	  }
	
  // create otp 
	public void sendOTP(String email,String purpose) throws HostelException {
		LoginEntity loginEntity = LogRepository.getByusername(email);
		if (loginEntity==null) {
			  String otp = generateOTP();
		        OTP otpEntity = new OTP();
		        otpEntity.setEmail(email);
		        otpEntity.setOtp(otp);
		        otpEntity.setPurpose(purpose);
		        otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(OTP_EXPIRATION_MINUTES));
		        //stor database in otp
		        otpRepository.save(otpEntity);

		        sendEmail(email, otp);
		} else {
			throw new HostelException("You already Register Please go To Login");
		}
	}
	//send  otp
	  private void sendEmail(String email, String otp) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(email);
	        message.setSubject("Your OTP Code");
	        message.setText("Your OTP code is: " + otp);
	        mailSender.send(message);
	    }
}
