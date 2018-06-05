package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Assignment;
import webdev.models.Lesson;
import webdev.repositories.AssignmentRepository;
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

    @PostMapping("/api/lesson/{lid}/assignment")
    public Assignment createAssignment(@PathVariable("lid") int lid, @RequestBody Assignment assignment) {
        Optional<Lesson> maybeLesson = lessonRepository.findById(lid);
        if (maybeLesson.isPresent()) {
            Lesson lesson = maybeLesson.get();
            List<Assignment> assignments = lesson.getAssignments();
            assignments.add(assignment);
            assignment.setLesson(lesson);
            assignment.setLessonId(lid);
            lesson.setAssignments(assignments);
            assignmentRepository.save(assignment);
            lessonRepository.save(lesson);
            return assignment;
        }
        return null;
    }

}
