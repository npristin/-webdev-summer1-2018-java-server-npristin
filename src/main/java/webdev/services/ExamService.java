package webdev.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import webdev.models.Exam;
import webdev.models.Lesson;
import webdev.models.MultipleChoiceExamQuestion;
import webdev.models.BaseExamQuestion;
import webdev.models.TrueOrFalseExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.LessonRepository;
import webdev.repositories.MultipleChoiceExamQuestionRepository;
import webdev.repositories.TrueOrFalseExamQuestionRepository;

@RestController
@CrossOrigin(origins = "*")
public class ExamService {

    @Autowired
    ExamRepository examRepository;
    @Autowired
    LessonRepository lessonRepository;

    @GetMapping("/api/exam")
    public List<Exam> findAllExams() {
        return (List<Exam>) examRepository.findAll();
    }

    @GetMapping("/api/exam/{eid}")
    public Exam findExamById(@PathVariable("eid") int eid) {
        Optional<Exam> maybeExam = examRepository.findById(eid);
        if (maybeExam.isPresent()) {
            return maybeExam.get();
        }
        return null;
    }

}
