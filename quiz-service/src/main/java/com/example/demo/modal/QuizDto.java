package com.example.demo.modal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuizDto {

	String categoryName;
	Integer noOfQuestions;
	String title;
}
