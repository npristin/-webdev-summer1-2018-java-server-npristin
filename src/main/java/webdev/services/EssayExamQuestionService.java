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

    @PostMapping("/api/exam/{eid}/essay")
    public EssayExamQuestion createEssayQuestion(@PathVariable("eid") int eid,
                                                  @RequestBody EssayExamQuestion essayQuestion) {
        Optional<Exam> maybeExam = examRepository.findById(eid);
        if (maybeExam.isPresent()) {
            Exam exam = maybeExam.get();
            List<BaseExamQuestion> questions = exam.getQuestions();
            questions.add(essayQuestion);
            exam.setQuestions(questions);
            essayQuestion.setExam(exam);
            examRepository.save(exam);
            essayRepository.save(essayQuestion);
            return essayQuestion;
        }
        return null;
    }

    @GetMapping("/api/essay")
    public List<EssayExamQuestion> findAllEssayQuestions() {
        return (List<EssayExamQuestion>) essayRepository.findAll();
    }

    @GetMapping("/api/essay/{qid}")
    public EssayExamQuestion findEssayQuestionById(@PathVariable("qid") int questionId) {
        Optional<EssayExamQuestion> optional = essayRepository.findById(questionId);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @DeleteMapping("/api/essay/{qid}")
    public void deleteEssayById(@PathVariable("qid") int questionId) {
        Optional<EssayExamQuestion> optional = essayRepository.findById(questionId);
        if (optional.isPresent()) {
            EssayExamQuestion question = optional.get();
            essayRepository.delete(question);
        }
    }
}
