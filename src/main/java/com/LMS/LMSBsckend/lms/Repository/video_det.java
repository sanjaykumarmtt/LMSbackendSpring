package com.LMS.LMSBsckend.lms.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.LMS.LMSBsckend.lms.Entity.Entvideose;

@Repository
public interface video_det extends JpaRepository<Entvideose,Integer>{
	
	@Query(value="SELECT *FROM videourl WHERE videoname= :videoname",nativeQuery=true)
	List<Entvideose> getByvideo(@Param("videoname") String videoname);
}