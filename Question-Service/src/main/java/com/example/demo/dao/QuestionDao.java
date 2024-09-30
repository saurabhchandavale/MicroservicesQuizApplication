package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.demo.modal.Question;

@Repository
@EnableJpaRepositories
public interface QuestionDao extends JpaRepository<Question,Integer>{

	public List<Question> findBycategory(String category);
	@Query("SELECT MAX(q.id) FROM Question q")
	public Integer findMaxId();
	@Query(value = "SELECT q.id FROM questions q WHERE category = :category ORDER BY RAND() LIMIT :noOfQuestions", nativeQuery = true)
	public List<Integer> findRandomQuestionsByCategory( String category,  int noOfQuestions);
}
