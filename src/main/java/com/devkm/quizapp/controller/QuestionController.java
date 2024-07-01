package com.devkm.quizapp.controller;

import com.devkm.quizapp.model.Question;
import com.devkm.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService service;

    @GetMapping("/allQuestions")
   public List<Question> getAllQuestions(){
        return service.getAllQuestions();

    }
}
