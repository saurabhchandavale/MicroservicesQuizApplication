package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dao.QuestionDao;
import com.example.demo.modal.Question;
import com.example.demo.modal.QuestionWrapper;
import com.example.demo.modal.Response;

@Service
public class QuestionService {

	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<List<Question>> getAllQuestions() {
		// TODO Auto-generated method stub
		try {
			return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public List<Question> getAllquestionsByCategory(String category) {
		// TODO Auto-generated method stub
		return questionDao.findBycategory(category);
	}

	public ResponseEntity<Question> addQuestion(Question question) {
		try {
			Integer maxId = questionDao.findMaxId();
			// Increment the ID
			question.setId(maxId != null ? maxId + 1 : 1);
			Question save = questionDao.save(question);
			return new ResponseEntity<>(save, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

	}

	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer noOfQuestions) {
		List<Integer> questions = questionDao.findRandomQuestionsByCategory(categoryName, noOfQuestions);
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionId) {
		List<QuestionWrapper> wrapper = new ArrayList<>();
		List<Question> questions = new ArrayList<>();

		for (Integer id : questionId) {
			questions.add(questionDao.findById(id).get());
		}

		for (Question q : questions) {
			QuestionWrapper question = new QuestionWrapper(q.getId(), q.getCategory(), q.getDifficultylevel(),
					q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
			wrapper.add(question);
		}
		return new ResponseEntity<>(wrapper, HttpStatus.OK);

	}

	public ResponseEntity<Integer> getScore(List<Response> response) {

		int right = 0;

		for (Response r : response) {
			Question question = questionDao.findById(r.getId()).get();
			if (r.getResponse().equals(question.getRightAnswer())) {
				right++;
			}

		}
		return new ResponseEntity<>(right, HttpStatus.OK);

	}

}
