package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.BaseExamQuestion;
import webdev.models.EssayExamQuestion;
import webdev.models.Exam;
import webdev.repositories.EssayExamQuestionRepository;
import webdev.repositories.ExamRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class EssayExamQuestionService {

    @Autowired
    EssayExamQuestionRepository essayRepository;
    @Autowired
    ExamRepository examRepository;

    
}
