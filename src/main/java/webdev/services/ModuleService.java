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

}
