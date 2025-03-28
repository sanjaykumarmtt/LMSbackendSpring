package com.LMS.LMSBsckend.lms.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.LMS.LMSBsckend.Exception.HostelException;
import com.LMS.LMSBsckend.lms.Entity.Course_det;
import com.LMS.LMSBsckend.lms.Entity.Entvideose;
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
//		System.out.println(fail.getOriginalFilename());
//		System.out.println(Course_det.getCourse_name());
		LmsService.Save_Corse_data(Course_det, fail);
		return ResponseEntity.ok().body("Save Successful Data");
	}

	@PostMapping("/public/video_data")
	public ResponseEntity<String> video_data(@RequestPart("fail") MultipartFile fail,
			@RequestPart("Course") Entvideose Entvideose) throws IOException, HostelException {
//		System.out.println(fail.getOriginalFilename());
//		System.out.println(Entvideose.getVideoname());
//		System.out.println(Entvideose.getChildFolderName());

		return ResponseEntity.ok().body(LmsService.storeVideo(fail, Entvideose));
		// return ResponseEntity.ok().body("hello");
	}

	@GetMapping("/public/getCoruse_data") // get All Images Overall_dataEnt
	public ResponseEntity<List<Map<String, Object>>> GetAllcorude() throws IOException {
		return ResponseEntity.ok(LmsService.GetAllcourse());
	}

	@GetMapping("/public/Getvideo_data") // get strem one video
	public ResponseEntity<Resource> streamVideo(@RequestParam("parentFolderName") String parentFolderName,
			@RequestParam("childFolderName") String childFolderName, @RequestParam("url") String url)
			throws IOException {
		Path filePath = LmsService.getVideoPath(parentFolderName, childFolderName, url);
		Resource resource = new UrlResource(filePath.toUri());

		if (resource.exists() && resource.isReadable()) {
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
					.body(resource);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/user/getvideodata/{video}")
	public ResponseEntity<List<Entvideose>> getvideodata(@PathVariable("video") String video) {
		return ResponseEntity.ok().body(LmsService.getvideodataurl(video));

	}

//lnkhuyftdzdf
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