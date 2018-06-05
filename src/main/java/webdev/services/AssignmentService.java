package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import webdev.models.Assignment;
import webdev.repositories.AssignmentRepository;
import webdev.repositories.ExamRepository;
import webdev.repositories.LessonRepository;

import java.util.List;

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
}
