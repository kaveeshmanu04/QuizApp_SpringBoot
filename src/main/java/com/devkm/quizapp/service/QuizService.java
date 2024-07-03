package com.devkm.quizapp.service;

import com.devkm.quizapp.model.Question;
import com.devkm.quizapp.model.QuestionWrapper;
import com.devkm.quizapp.model.Quiz;
import com.devkm.quizapp.model.Response;
import com.devkm.quizapp.repo.QuestionRepo;
import com.devkm.quizapp.repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizRepo quizDao;

    @Autowired
    QuestionRepo questionRepo;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questionList = questionRepo.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questionList);

        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int qId) {
        try {
            Optional<Quiz> quiz = quizDao.findById(qId);
            List<Question> questionsFromDb = quiz.get().getQuestions();
            List<QuestionWrapper> questionToUser = new ArrayList<>();
            for (Question q : questionsFromDb) {
                QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
                questionToUser.add(qw);

            }

            return new ResponseEntity<>(questionToUser, HttpStatus.OK);
        } catch (DataAccessException dae) {
            dae.printStackTrace();

        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);

    }


    public ResponseEntity<Integer> calculateResults(int id, List<Response> responses) {
        try {
            Quiz quiz = quizDao.findById(id).get();
            List<Question> questions = quiz.getQuestions();
            int right = 0;
            int i = 0;

            for (Response response : responses) {
                if (response.getResponse().equals(questions.get(i).getRightAnswer()))
                    right++;

                i++;


            }
            return new ResponseEntity<>(right, HttpStatus.OK);

        } catch (DataAccessException dae) {
            dae.printStackTrace();

        }
        return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
