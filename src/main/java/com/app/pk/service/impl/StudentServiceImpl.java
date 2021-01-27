package com.app.pk.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.pk.bean.Student;
import com.app.pk.repo.StudentRepository;
import com.app.pk.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private StudentRepository repo;
	
	public Integer saveStudent(Student s) {
		return repo.save(s).getStId();
	}

	public List<Student> getAllStudent() {
		return repo.findAll();
	}

	public Optional<Student> getOneStudent(Integer id) {
		return repo.findById(id);
	}

	public boolean isExist(Integer id) {
		return repo.existsById(id);
	}

	public void deleteStudent(Integer id) {
		 repo.deleteById(id);
	}

}
