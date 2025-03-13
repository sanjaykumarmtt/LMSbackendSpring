package com.LMS.LMSBsckend.lms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.LMS.LMSBsckend.lms.Entity.Course_det;

@Repository
public interface LmsCorse_Title_Reposirory extends JpaRepository<Course_det,Integer>{

}
