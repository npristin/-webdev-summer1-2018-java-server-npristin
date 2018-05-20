package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Course;
import webdev.models.Module;
import webdev.repositories.CourseRepository;
import webdev.repositories.ModuleRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleService {

    @Autowired
    ModuleRepository moduleRepository;
    @Autowired
    CourseRepository courseRepository;

    @PostMapping("/api/course/{cid}/module")
    public Module createModule(@RequestBody Module module, @PathVariable("cid") int cid) {
        Optional<Course> maybeCourse = courseRepository.findById(cid);
        if (maybeCourse.isPresent()) {
            Course course = maybeCourse.get();
            List<Module> courseModules = course.getModules();
            courseModules.add(module);
            course.setModules(courseModules);
            module.setCourse(course);

            moduleRepository.save(module);
            courseRepository.save(course);
        }
        return module;
    }

    @DeleteMapping("/api/module/{id}")
    public void deleteModule(@PathVariable("id") int moduleId) {
        moduleRepository.deleteById(moduleId);
    }

    @GetMapping("/api/module")
    public List<Module> findAllModules() {
        return (List<Module>) moduleRepository.findAll();
    }

    @GetMapping("/api/module/{id}")
    public Module findModuleById(@PathVariable("id") int moduleId) {
        Optional<Module> potentialModule = moduleRepository.findById(moduleId);
        if (potentialModule.isPresent()) {
            return potentialModule.get();
        }
        return null;
    }

}
