package com.LMS.LMSBsckend.lms.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="videourl")
public class Entvideose {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String videoname;
	private String videoAtername;
	private String videourl;
	private String childFolderName;
	
	public Entvideose(int id, String videoname, String videoAtername, String videourl, String childFolderName) {
		super();
		this.id = id;
		this.videoname = videoname;
		this.videoAtername = videoAtername;
		this.videourl = videourl;
		this.childFolderName = childFolderName;
	}
	public Entvideose() {
		super();
	}
	public String getChildFolderName() {
		return childFolderName;
	}
	public void setChildFolderName(String childFolderName) {
		this.childFolderName = childFolderName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVideoname() {
		return videoname;
	}
	public void setVideoname(String videoname) {
		this.videoname = videoname;
	}
	public String getVideoAtername() {
		return videoAtername;
	}
	public void setVideoAtername(String videoAtername) {
		this.videoAtername = videoAtername;
	}
	public String getVideourl() {
		return videourl;
	}
	public void setVideourl(String videourl) {
		this.videourl = videourl;
	}
}