package com.app.pk.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pk.bean.Student;

public interface StudentRepository extends JpaRepository<Student,Integer>{

}
