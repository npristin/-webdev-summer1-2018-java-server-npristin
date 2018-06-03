package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.BaseExamQuestion;
import webdev.models.Exam;
import webdev.models.MultipleChoiceExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.MultipleChoiceExamQuestionRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class MultipleChoiceExamQuestionService {

    @Autowired
    MultipleChoiceExamQuestionRepository multiChoiceQuestionRepository;
    @Autowired
    ExamRepository examRepository;

    @PostMapping("/api/exam/{eid}/choice")
    public MultipleChoiceExamQuestion createMultipleChoice(@PathVariable("eid") int eid,
                                                           @RequestBody MultipleChoiceExamQuestion choiceQuestion) {
        Optional<Exam> maybeExam = examRepository.findById(eid);
        if (maybeExam.isPresent()) {
            Exam exam = maybeExam.get();
            List<BaseExamQuestion> questions = exam.getQuestions();
            choiceQuestion.setExam(exam);
            questions.add(choiceQuestion);
            examRepository.save(exam);
            multiChoiceQuestionRepository.save(choiceQuestion);
            return choiceQuestion;
        }
        return null;
    }


}
