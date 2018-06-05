package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import webdev.models.Assignment;
import webdev.models.Lesson;
import webdev.repositories.AssignmentRepository;
import webdev.repositories.ExamRepository;
import webdev.repositories.LessonRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class AssignmentService {

    @Autowired
    AssignmentRepository assignmentRepository;
    @Autowired
    LessonRepository lessonRepository;

    @GetMapping("/api/assignment")
    public List<Assignment> findAllAssignments() {
        return (List<Assignment>) assignmentRepository.findAll();
    }

    @GetMapping("/api/assignment/{aid}")
    public Assignment findAssignmentByid(@PathVariable("aid") int aid) {
        Optional<Assignment> maybeAssignment = assignmentRepository.findById(aid);
        if (maybeAssignment.isPresent()) {
            return maybeAssignment.get();
        }
        return null;
    }

    @GetMapping("/api/lesson/{lid}/assignment")
    public List<Assignment> findAssignmentsByLesson(@PathVariable("lid") int lid) {
        Optional<Lesson> maybeLesson = lessonRepository.findById(lid);
        if (maybeLesson.isPresent()) {
            Lesson lesson = maybeLesson.get();
            List<Assignment> assignments = lesson.getAssignments();
            return assignments;
        }
        return null;
    }


}
