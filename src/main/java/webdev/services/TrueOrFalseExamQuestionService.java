package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.BaseExamQuestion;
import webdev.models.Exam;
import webdev.models.TrueOrFalseExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.TrueOrFalseExamQuestionRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class TrueOrFalseExamQuestionService {

    @Autowired
    TrueOrFalseExamQuestionRepository trueFalseQuestionRepository;
    @Autowired
    ExamRepository examRepository;

    @PostMapping("/api/exam/{eid}/truefalse")
    public TrueOrFalseExamQuestion createTrueFalse(@PathVariable("eid") int eid,
                                                   @RequestBody TrueOrFalseExamQuestion trueFalseQuestion) {
        Optional<Exam> maybeExam = examRepository.findById(eid);
        if (maybeExam.isPresent()) {
            Exam exam = maybeExam.get();
            List<BaseExamQuestion> questions = exam.getQuestions();
            trueFalseQuestion.setExam(exam);
            questions.add(trueFalseQuestion);
            exam.setQuestions(questions);
            trueFalseQuestion.setExam(exam);
            trueFalseQuestion.setType("truefalse");
            examRepository.save(exam);
            trueFalseQuestionRepository.save(trueFalseQuestion);
            return trueFalseQuestion;
        }
        return null;
    }

    @GetMapping("/api/truefalse")
    public List<TrueOrFalseExamQuestion> findAllTrueFalseQuestions() {
        return (List<TrueOrFalseExamQuestion>) trueFalseQuestionRepository.findAll();
    }

    @GetMapping("/api/truefalse/{qid}")
    public TrueOrFalseExamQuestion findTrueFalseQuestionById(@PathVariable("qid") int questionId) {
        Optional<TrueOrFalseExamQuestion> optional = trueFalseQuestionRepository.findById(questionId);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @DeleteMapping("/api/truefalse/{qid}")
    public void deleteTrueFalseQuestionById(@PathVariable("qid") int questionId) {
        Optional<TrueOrFalseExamQuestion> optional = trueFalseQuestionRepository.findById(questionId);
        if (optional.isPresent()) {
            TrueOrFalseExamQuestion question = optional.get();
            trueFalseQuestionRepository.delete(question);
        }
    }
}
