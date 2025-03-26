package com.LMS.LMSBsckend.lms.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.LMS.LMSBsckend.Exception.HostelException;
import com.LMS.LMSBsckend.lms.Entity.Course_det;
import com.LMS.LMSBsckend.lms.Entity.Entvideose;
import com.LMS.LMSBsckend.lms.Repository.LmsCorse_Title_Reposirory;
import com.LMS.LMSBsckend.lms.Repository.video_det;

@Service
public class LmsService {
	

	  
	    private LmsCorse_Title_Reposirory LmsCorse_Title_Reposirory;
		private video_det video_det;
		private Path videoStorageLocation;

		public LmsService(LmsCorse_Title_Reposirory lmsCorse_Title_Reposirory, video_det video_det) {
			super();
			LmsCorse_Title_Reposirory = lmsCorse_Title_Reposirory;
			this.video_det = video_det;
			this.videoStorageLocation = Paths.get("");
		}
	public void Save_Corse_data(Course_det Course_det,MultipartFile fail) throws IOException {
		
		videoStorageLocation= Paths.get(Course_det.getCourse_name());
		Files.createDirectories(videoStorageLocation);
		Course_det.setBytdata(fail.getBytes());
		Course_det.setImgname(fail.getOriginalFilename());
		
		LmsCorse_Title_Reposirory.save(Course_det);
	}
	public String storeVideo(MultipartFile file, Entvideose Entvideose) throws HostelException {
		String fileName = Entvideose.getVideoname() + "_" + file.getOriginalFilename();
		try {
			
			Course_det Course_det = LmsCorse_Title_Reposirory.getByusername(Entvideose.getVideoname());

			if (Course_det != null) {
				videoStorageLocation = Paths.get(Entvideose.getVideoname());
				 Path childFolderPath = videoStorageLocation.resolve(Entvideose.getChildFolderName());
				
				 // பெற்றோர் கோப்புறையை உருவாக்குதல்
	            if (!Files.exists(videoStorageLocation)) {
	                Files.createDirectories(videoStorageLocation);
	            }

	            // குழந்தை கோப்புறையை உருவாக்குதல்
	            if (!Files.exists(childFolderPath)) {
	                Files.createDirectories(childFolderPath);
	            }
				
				Path targetLocation = childFolderPath.resolve(fileName);
				Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

				Entvideose.setVideourl(fileName);
				Entvideose.setVideoAtername(Course_det.getCourse_Provider());

				video_det.save(Entvideose);
				return fileName + "sucessfull store video";
			} else {
				throw new HostelException("Not video name");
			}

		} catch (IOException e) {
			throw new RuntimeException("வீடியோவை சேமிக்க முடியவில்லை", e);
		}
	}
	
	 public Path getVideoPath(String parentFolderName,String childFolderName,String url) {
			videoStorageLocation = Paths.get(parentFolderName,childFolderName);
	        return videoStorageLocation.resolve(url);
	    }
	// get All Student Image St_imag
		public List<Map<String, Object>> GetAllcourse() throws IOException {
			return LmsCorse_Title_Reposirory.findAll().stream().map(course -> {
				Map<String, Object> imgdata = new HashMap<>();
				imgdata.put("id", course.getId());
				imgdata.put("course_name", course.getCourse_name());
				imgdata.put("course_Provider", course.getCourse_Provider());
				imgdata.put("price", course.getPrice());
				imgdata.put("deuration", course.getDeuration());
				imgdata.put("description", course.getDescription());
				imgdata.put("what_you_learn1",course.getWhat_you_learn1());
				imgdata.put("what_you_learn2", course.getWhat_you_learn2());
				imgdata.put("what_you_learn3", course.getWhat_you_learn3());
				imgdata.put("what_you_learn4", course.getWhat_you_learn4());
				imgdata.put("course_image_name", course.getImgname());
			
				imgdata.put("data", Base64.getEncoder().encodeToString(course.getBytdata()));
				return imgdata;
			}).toList();
		}
	    public List<Entvideose> getvideodataurl(String coname){
			List<Entvideose> Entvideose=video_det.getByvideo(coname);
			
			return Entvideose;
		}
//		, course_name,
//	     ,,,,
//	    what_you_learn2,what_you_learn3,what_you_learn4
}
