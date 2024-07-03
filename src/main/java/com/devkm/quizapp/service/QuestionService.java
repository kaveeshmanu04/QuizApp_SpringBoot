package com.devkm.quizapp.service;

import com.devkm.quizapp.model.Question;
import com.devkm.quizapp.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo repo;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            List<Question> questions = repo.findAll();
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (DataAccessException dae) {
            dae.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        if (category == null || category.isEmpty()) {
            throw new IllegalArgumentException("Category must not be null or empty");
        }
        try {
            List<Question> questions = repo.findByCategory(category);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (DataAccessException dae) {
            dae.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addQuestion(Question question) {
        if (question == null) {
            throw new IllegalArgumentException("Question must not be null");
        }

        try {
            Question savedQuestion = repo.save(question);
            return new ResponseEntity<>(savedQuestion.toString(), HttpStatus.CREATED);
        } catch (DataAccessException dae) {
            dae.printStackTrace();
            return new ResponseEntity<>("Failed to add question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
