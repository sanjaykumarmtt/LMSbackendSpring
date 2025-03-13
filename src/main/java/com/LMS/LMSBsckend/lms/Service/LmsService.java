package com.LMS.LMSBsckend.lms.Service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.LMS.LMSBsckend.lms.Entity.Course_det;
import com.LMS.LMSBsckend.lms.Repository.LmsCorse_Title_Reposirory;

@Service
public class LmsService {
	
	@Autowired
	LmsCorse_Title_Reposirory LmsCorse_Title_Reposirory;
	
	public void Save_Corse_data(Course_det Course_det,MultipartFile fail) throws IOException {
		
		Course_det.setBytdata(fail.getBytes());
		Course_det.setImgname(fail.getOriginalFilename());
		
		LmsCorse_Title_Reposirory.save(Course_det);
		
	}

}
