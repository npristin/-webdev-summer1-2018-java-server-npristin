package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import webdev.repositories.BaseExamQuestionRepository;
import webdev.repositories.EssayExamQuestionRepository;

public class BaseExamQuestionService {

    @Autowired
    BaseExamQuestionRepository baseExamQuestionRepository;
    @Autowired
    EssayExamQuestionRepository essayExamQuestionRepository;

    
}
