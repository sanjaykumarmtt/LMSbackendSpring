package com.LMS.LMSBsckend.Login.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.LMS.LMSBsckend.Login.Entity.LoginEntity;

@Repository
public interface LogRepository extends JpaRepository<LoginEntity,Integer>{
	
	@Query(value="SELECT *FROM Login WHERE username= :userna",nativeQuery=true)
	LoginEntity getByusername(@Param("userna") String userna);

}
