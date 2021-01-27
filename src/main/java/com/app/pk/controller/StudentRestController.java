package com.app.pk.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pk.bean.Student;
import com.app.pk.service.IStudentService;

@RestController
@RequestMapping("/rest/student")
@CrossOrigin(origins ="http://localhost:4200")
public class StudentRestController {
	
	@Autowired
	private IStudentService service;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveStduentData(
			@RequestBody Student student){
		  ResponseEntity<String> resp=null; 
		  try {  
			  Integer id=service.saveStudent(student);  
			  resp = new ResponseEntity<String>(
						"Student '"+id+"' created Successfully!",
						HttpStatus.OK
						);
			
          } catch (Exception e) {
			resp = new ResponseEntity<String>(
					"Unable to Save Product",
					HttpStatus.INTERNAL_SERVER_ERROR
					);
			e.printStackTrace();
		}
		return resp;
	}

	@GetMapping("/all") 
	public ResponseEntity<?> getAllStudents(){ 
	ResponseEntity<?> resp=null;  
	try {   
	List<Student> list=service.getAllStudent();   
	if(list!=null && !list.isEmpty())   
	resp=new ResponseEntity<List<Student>>(list,HttpStatus.OK); 
	else   
	resp=new ResponseEntity<String>("No Data Found",HttpStatus.OK); 
	} catch (Exception e) {  
	resp=new ResponseEntity<String>("Unable to fetch Data",HttpStatus.INTERNAL_SERVER_ERROR);    
	e.printStackTrace();  
	}      
	return resp; 
	}
	
	@GetMapping("/one/{id}") 
	public ResponseEntity<?> getOneStudent( 
			@PathVariable Integer id)  {   
		ResponseEntity<?> resp=null;   
		try {  
			Optional<Student> opt=service.getOneStudent(id);  
			if(opt.isPresent()) 
				resp=new ResponseEntity<Student>(opt.get(),HttpStatus.OK);  
			else  
				resp=new ResponseEntity<String>("No Data Found",HttpStatus.BAD_REQUEST);  
			} catch (Exception e) {  
				resp=new ResponseEntity<String>("Unable to Fetch Data",HttpStatus.INTERNAL_SERVER_ERROR);  
				e.printStackTrace();  
				
				}  
		return resp;  
		}   
	@DeleteMapping("/remove/{id}")  
	public ResponseEntity<String> deleteStudent(   
			@PathVariable Integer id)  {  
		System.out.println("welcome");  
		ResponseEntity<String> resp=null; 
		
		try {  
			boolean exist=service.isExist(id); 
			if(exist) {   
				service.deleteStudent(id);    
				resp=new ResponseEntity<String>(
						"Product '"+id+"' Deleted Successfully!",
						HttpStatus.OK
						);  
				}else
				{ 
					resp=new ResponseEntity<String>("FAIL"+id+"-Not Exist",HttpStatus.BAD_REQUEST); 
					} 
			} catch (Exception e) {  
				resp=new ResponseEntity<String>("FAIL Unable to Delete",HttpStatus.INTERNAL_SERVER_ERROR);   
				e.printStackTrace();  
				}     
		return resp; 
		}
			
	@PutMapping("/update")  
	public ResponseEntity<String> updateStudent(  
			@RequestBody Student student) {  
		ResponseEntity<String> resp=null;   
		try {
			boolean exist=service.isExist(student.getStId()); 
			if(exist) {  
				service.saveStudent(student);   
				resp=new ResponseEntity<String>("OK"+ student.getStId()+"-Updated",HttpStatus.OK);    
				}else { 
					resp=new ResponseEntity<String>("OK "+student.getStId()+"-Not Exist",HttpStatus.BAD_REQUEST);
					}   
			} catch (Exception e) {   
				resp=new ResponseEntity<String>("OK Unable to Update",HttpStatus.INTERNAL_SERVER_ERROR);   
				e.printStackTrace();  
				}   
		return resp;
		}
	}
	

