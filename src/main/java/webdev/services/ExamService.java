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

    @GetMapping("/api/lesson/{lid}/exam")
    public List<Exam> findExamsByLesson(@PathVariable("lid") int lid) {
        Optional<Lesson> maybeLesson = lessonRepository.findById(lid);
        if (maybeLesson.isPresent()) {
            Lesson lesson = maybeLesson.get();
            List<Exam> exams = lesson.getExams();
            return exams;
        }
        return null;
    }

    @PostMapping("/api/lesson/{lid}/exam")
    public Exam createExam(@PathVariable("lid") int lid, @RequestBody Exam exam) {
        Optional<Lesson> maybeLesson = lessonRepository.findById(lid);
        if (maybeLesson.isPresent()) {
            Lesson lesson = maybeLesson.get();
            List<Exam> exams = lesson.getExams();
            exams.add(exam);
            exam.setLesson(lesson);
            exam.setLessonId(lid);
            lesson.setExams(exams);
            examRepository.save(exam);
            lessonRepository.save(lesson);
            return exam;
        }
        return null;
    }

    @DeleteMapping("/api/exam/{eid}")
    public void deleteExam(@PathVariable("eid") int eid) {
        examRepository.deleteById(eid);
    }

    @GetMapping("/api/exam/{examId}/question")
    public List<BaseExamQuestion> findAllQuestionsForExam(@PathVariable("examId") int examId) {
        Optional<Exam> optionalExam = examRepository.findById(examId);
        if(optionalExam.isPresent()) {
            Exam exam = optionalExam.get();
            List<BaseExamQuestion> questions = exam.getQuestions();
            return questions;
        }
        return null;
    }
}
