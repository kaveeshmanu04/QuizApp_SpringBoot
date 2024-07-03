package com.devkm.quizapp.controller;

import com.devkm.quizapp.model.Question;
import com.devkm.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService service;

    @GetMapping("/allQuestions")
   public ResponseEntity<List<Question>> getAllQuestions(){
        return service.getAllQuestions();

    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question> >getQuestionByCategory(@PathVariable String category){
        return service.getQuestionByCategory(category);

    }

    @PostMapping("/addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return service.addQuestion(question);

    }


}
