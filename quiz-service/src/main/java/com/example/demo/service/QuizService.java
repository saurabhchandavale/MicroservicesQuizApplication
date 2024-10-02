package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dao.QuizDao;
import com.example.demo.feign.QuizInterface;
import com.example.demo.modal.QuestionWrapper;
import com.example.demo.modal.Quiz;
import com.example.demo.modal.QuizDto;
import com.example.demo.modal.Response;

@Service
public class QuizService {
	@Autowired
	QuizDao quizDao;
	@Autowired
	QuizInterface quizInterface;

	public ResponseEntity<String> createQuiz(String category, int noOfQuestions, String title) {
		List<Integer> questions = quizInterface.generateQuestionsForQuiz(category, noOfQuestions).getBody();
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIds(questions);
		quizDao.save(quiz);
		return new ResponseEntity<>("Success",HttpStatus.CREATED);
		
	}

	public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {
		Quiz quiz = quizDao.findById(id).get();
		List<Integer> questionIds = quiz.getQuestionIds();
		ResponseEntity<List<QuestionWrapper>> questionsFromId = quizInterface.getQuestionsFromId(questionIds);
		return questionsFromId;
	}

	public ResponseEntity<Integer> calculate(int id, List<Response> response) {
		ResponseEntity<Integer> score = quizInterface.getScore(response);
		return score;
	}

}
