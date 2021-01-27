package com.app.pk.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name="student_Ang_Swg")
public class Student implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer stId;
	private String stdName;
	private String stdEmailId;
	private Double stdFee;
	private String stdCourse;
	

}
