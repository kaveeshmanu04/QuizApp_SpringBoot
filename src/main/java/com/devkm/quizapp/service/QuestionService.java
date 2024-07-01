package com.devkm.quizapp.service;

import com.devkm.quizapp.model.Question;
import com.devkm.quizapp.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepo repo;
    public List<Question> getAllQuestions() {
        return repo.findAll();

    }
}
