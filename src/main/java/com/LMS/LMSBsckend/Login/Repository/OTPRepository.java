package com.LMS.LMSBsckend.Login.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.LMS.LMSBsckend.Login.Entity.LoginEntity;
import com.LMS.LMSBsckend.Login.Entity.OTP;

public interface OTPRepository extends JpaRepository<OTP, Long> {
	
	
//	@Query(value="SELECT *FROM otp WHERE email= :userna",nativeQuery=true)
//	Optional<OTP> findByEmail(@Param("userna") String userna);
	 Optional<OTP> findByOtp(String otp);
     
}
