package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import webdev.repositories.ExamRepository;
import webdev.repositories.LessonRepository;

@RestController
@CrossOrigin(origins = "*")
public class AssignmentService {

    @Autowired
    ExamRepository examRepository;
    @Autowired
    LessonRepository lessonRepository;
}
