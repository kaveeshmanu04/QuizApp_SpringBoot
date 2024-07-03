package com.devkm.quizapp.controller;
import com.devkm.quizapp.model.QuestionWrapper;
import com.devkm.quizapp.model.Response;
import com.devkm.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService service;
    @GetMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return service.createQuiz(category,numQ,title);

    }

    @GetMapping("get/{qId}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int qId){
        return service.getQuizQuestions(qId);

    }
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<Response> response){
        return service.calculateResults(id,response);

    }



}
