package com.devkm.quizapp.repo;

import com.devkm.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  QuizRepo extends JpaRepository<Quiz,Integer> {
}
