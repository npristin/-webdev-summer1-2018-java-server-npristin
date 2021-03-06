package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.BaseExamQuestion;
import webdev.models.Exam;
import webdev.models.FillInTheBlanksExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.FillInTheBlanksExamQuestionRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class FillInTheBlanksExamQuestionService {

    @Autowired
    FillInTheBlanksExamQuestionRepository fillInTheBlanksRepository;
    @Autowired
    ExamRepository examRepository;

    @PostMapping("/api/exam/{eid}/blanks")
    public FillInTheBlanksExamQuestion createFillInBlanksQuestion(@PathVariable("eid") int eid,
                                                       @RequestBody FillInTheBlanksExamQuestion fillInBlanksQuestion) {
        Optional<Exam> maybeExam = examRepository.findById(eid);
        if (maybeExam.isPresent()) {
            Exam exam = maybeExam.get();
            List<BaseExamQuestion> questions = exam.getQuestions();
            fillInBlanksQuestion.setExam(exam);
            questions.add(fillInBlanksQuestion);
            exam.setQuestions(questions);
            fillInBlanksQuestion.setExam(exam);
            fillInBlanksQuestion.setType("blanks");
            examRepository.save(exam);
            fillInTheBlanksRepository.save(fillInBlanksQuestion);
            return fillInBlanksQuestion;
        }
        return null;
    }

    @GetMapping("/api/blanks")
    public List<FillInTheBlanksExamQuestion> findAllFillInBlanksQuestions() {
        return (List<FillInTheBlanksExamQuestion>) fillInTheBlanksRepository.findAll();
    }

    @GetMapping("/api/blanks/{qid}")
    public FillInTheBlanksExamQuestion findFillInBlankQuestionById(@PathVariable("qid") int questionId) {
        Optional<FillInTheBlanksExamQuestion> optional = fillInTheBlanksRepository.findById(questionId);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @DeleteMapping("/api/blanks/{qid}")
    public void deleteFillInBlanksById(@PathVariable("qid") int questionId) {
        Optional<FillInTheBlanksExamQuestion> optional = fillInTheBlanksRepository.findById(questionId);
        if (optional.isPresent()) {
            FillInTheBlanksExamQuestion question = optional.get();
            fillInTheBlanksRepository.delete(question);
        }
    }
}
