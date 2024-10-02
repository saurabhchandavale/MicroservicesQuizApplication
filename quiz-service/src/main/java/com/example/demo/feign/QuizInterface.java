package com.example.demo.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.modal.QuestionWrapper;
import com.example.demo.modal.Response;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

	
	@PostMapping("questions/getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionId);
	
	@PostMapping("/questions/getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> response);


	
	@GetMapping("/questions/generate")
	public ResponseEntity<List<Integer>> generateQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer noOfQuestions);

}
