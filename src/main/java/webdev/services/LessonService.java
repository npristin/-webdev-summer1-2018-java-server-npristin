package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Lesson;
import webdev.models.Module;
import webdev.models.Widget;
import webdev.repositories.LessonRepository;
import webdev.repositories.ModuleRepository;
import webdev.repositories.WidgetRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonService {

    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    ModuleRepository moduleRepository;
    @Autowired
    WidgetRepository widgetRepository;

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
        Optional<Lesson> maybeLesson = lessonRepository.findById(lessonId);
        if (maybeLesson.isPresent()) {
            Lesson lesson = maybeLesson.get();
            for(Widget w : lesson.getWidgets()) {
                widgetRepository.delete(w);
            }
        }
        lessonRepository.deleteById(lessonId);
    }

    @GetMapping("/api/lesson")
    public List<Lesson> findAllLessons() {
        return (List<Lesson>) lessonRepository.findAll();
    }

    @GetMapping("/api/lesson/{id}")
    public Lesson findLessonById(@PathVariable("id") int lessonId) {
        Optional<Lesson> potentialLesson = lessonRepository.findById(lessonId);
        if (potentialLesson.isPresent()) {
            return potentialLesson.get();
        }
        return null;
    }

    @GetMapping("/api/course/{cid}/module/{mid}/lesson")
    public List<Lesson> findAllLessonsForModule(@PathVariable("mid") int moduleId) {
        Optional<Module> potentialModule = moduleRepository.findById(moduleId);
        if (potentialModule.isPresent()) {
            Module module = potentialModule.get();
            return module.getLessons();
        }
        return null;
    }

    @PutMapping("/api/lesson/{id}")
    public Lesson updateLesson(@RequestBody Lesson newLesson, @PathVariable("id") int lessonId) {
        Optional<Lesson> potentialLesson = lessonRepository.findById(lessonId);
        if (potentialLesson.isPresent()) {
            Lesson lesson = potentialLesson.get();
            lesson.setTitle(newLesson.getTitle());
            lesson.setModule(newLesson.getModule());

            lessonRepository.save(lesson);
            return lesson;
        }
        return null;
    }
}
