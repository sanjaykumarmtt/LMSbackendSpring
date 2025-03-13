package com.LMS.LMSBsckend.lms.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Course_det {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private  String Course_name;
	private  String Course_Provider;
	private  String Price;
	private  String Deuration;
	private  String Description;
	private  String what_you_learn1;
	private  String what_you_learn2;
	private  String what_you_learn3;
	private  String what_you_learn4;
	
	private String imgname;
	@Lob
	@Column(name="Imgbytdata",columnDefinition="LONGBLOB")
	private byte[] bytdata;
	
	public Course_det() {
		super();
	}
	
	public Course_det(int id, String course_name, String course_Provider, String price, String deuration,
			String description, String what_you_learn1, String what_you_learn2, String what_you_learn3,
			String what_you_learn4, String imgname, byte[] bytdata) {
		super();
		this.id = id;
		Course_name = course_name;
		Course_Provider = course_Provider;
		Price = price;
		Deuration = deuration;
		Description = description;
		this.what_you_learn1 = what_you_learn1;
		this.what_you_learn2 = what_you_learn2;
		this.what_you_learn3 = what_you_learn3;
		this.what_you_learn4 = what_you_learn4;
		this.imgname = imgname;
		this.bytdata = bytdata;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourse_name() {
		return Course_name;
	}

	public void setCourse_name(String course_name) {
		Course_name = course_name;
	}

	public String getCourse_Provider() {
		return Course_Provider;
	}

	public void setCourse_Provider(String course_Provider) {
		Course_Provider = course_Provider;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public String getDeuration() {
		return Deuration;
	}

	public void setDeuration(String deuration) {
		Deuration = deuration;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getWhat_you_learn1() {
		return what_you_learn1;
	}

	public void setWhat_you_learn1(String what_you_learn1) {
		this.what_you_learn1 = what_you_learn1;
	}

	public String getWhat_you_learn2() {
		return what_you_learn2;
	}

	public void setWhat_you_learn2(String what_you_learn2) {
		this.what_you_learn2 = what_you_learn2;
	}

	public String getWhat_you_learn3() {
		return what_you_learn3;
	}

	public void setWhat_you_learn3(String what_you_learn3) {
		this.what_you_learn3 = what_you_learn3;
	}

	public String getWhat_you_learn4() {
		return what_you_learn4;
	}

	public void setWhat_you_learn4(String what_you_learn4) {
		this.what_you_learn4 = what_you_learn4;
	}

	public String getImgname() {
		return imgname;
	}

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}

	public byte[] getBytdata() {
		return bytdata;
	}

	public void setBytdata(byte[] bytdata) {
		this.bytdata = bytdata;
	}
}
