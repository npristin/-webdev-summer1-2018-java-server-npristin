package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Lesson;
import webdev.models.Module;
import webdev.repositories.LessonRepository;
import webdev.repositories.ModuleRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonService {

    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    ModuleRepository moduleRepository;

    @PostMapping("/api/course/{cid}/module/{mid}/lesson")
    public Lesson createLesson(@RequestBody Lesson lesson, @PathVariable("mid") int mid) {
        Optional<Module> maybeModule = moduleRepository.findById(mid);
        if (maybeModule.isPresent()) {
            Module module = maybeModule.get();
            List<Lesson> moduleLessons = module.getLessons();
            moduleLessons.add(lesson);
            module.setLessons(moduleLessons);
            lesson.setModule(module);

            lessonRepository.save(lesson);
            moduleRepository.save(module);
        }
        return lesson;
    }

    @DeleteMapping("/api/lesson/{id}")
    public void deleteLesson(@PathVariable("id") int lessonId) {
        lessonRepository.deleteById(lessonId);
    }

}
