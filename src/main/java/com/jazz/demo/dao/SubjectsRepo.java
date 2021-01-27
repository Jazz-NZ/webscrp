package com.jazz.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.jazz.demo.Subject;

public interface SubjectsRepo extends CrudRepository<Subject, Integer> {

	
}
