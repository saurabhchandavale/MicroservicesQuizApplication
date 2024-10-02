package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modal.QuestionWrapper;
import com.example.demo.modal.Quiz;
import com.example.demo.modal.QuizDto;
import com.example.demo.modal.Response;
import com.example.demo.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	QuizService quizService;
	//http://localhost:8003/quiz/createquiz?category=java&noOfQuestions=5&title=quiz
	@PostMapping("/createquiz")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
		{
			System.out.println("SCE32''''''");
			return quizService.createQuiz(quizDto.getCategoryName(), quizDto.getNoOfQuestions(), quizDto.getCategoryName());
		}

	}
	@GetMapping("/getquiz/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id){
		ResponseEntity<List<QuestionWrapper>> quiz = quizService.getQuiz(id);
		return quiz;
	}
	/*[
  {
    "id": 5,
    "response": 0
  },
  {
    "id": 13,
    "response": 0
  },
  {
    "id": 21,
    "response": 0
  },
  {
    "id": 15,
    "response": "implements"
  },
  {
    "id": 1,
    "response": "Java SE"
  }
]
*/
	
	//http://localhost:8003/quiz/submit/4
	@PostMapping("/submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable int id,@RequestBody List<Response> response) {
		ResponseEntity<Integer> calculate = quizService.calculate(id,response);
		return calculate;
	}
}
