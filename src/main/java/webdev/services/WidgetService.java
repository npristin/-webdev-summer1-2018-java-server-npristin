package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Lesson;
import webdev.models.Widget;
import webdev.repositories.LessonRepository;
import webdev.repositories.WidgetRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {

    @Autowired
    WidgetRepository widgetRepository;
    @Autowired
    LessonRepository lessonRepository;

    @GetMapping("/api/widget")
    public List<Widget> findAllWidgets() {
        return (List<Widget>) widgetRepository.findAll();
    }

    @GetMapping("/api/widget/{widgetId}")
    public Widget findWidgetById(@PathVariable("widgetId") int widgetId) {
        Optional<Widget> maybeWidget = widgetRepository.findById(widgetId);
        if (maybeWidget.isPresent()) {
            return maybeWidget.get();
        }
        return null;
    }

    @GetMapping("/api/lesson/{lessonId}/widget")
    public List<Widget> findAllWidgetsForLesson(@PathVariable("lessonId") int lessonId) {
        Optional<Lesson> maybeLesson = lessonRepository.findById(lessonId);
        if (maybeLesson.isPresent()) {
            Lesson lesson = maybeLesson.get();
            return lesson.getWidgets();
        }
        return null;
    }

    @PostMapping("/api/lesson/{lessonId}/widget")
    public Widget createWidget(@RequestBody Widget widget, @PathVariable("lessonId") int lessonId) {
        Optional<Lesson> maybeLesson = lessonRepository.findById(lessonId);
        if (maybeLesson.isPresent()) {
            Lesson lesson = maybeLesson.get();
            List<Widget> lessonWidgets = lesson.getWidgets();
            lessonWidgets.add(widget);
            lesson.setWidgets(lessonWidgets);
            widget.setLesson(lesson);

            widgetRepository.save(widget);
            lessonRepository.save(lesson);
        }
        return widget;
    }

    @PutMapping("/api/widget/{widgetId}")
    public Widget updateWidget(@RequestBody Widget newWidget, @PathVariable("widgetId") int widgetId) {
        Optional<Widget> maybeWidget = widgetRepository.findById(widgetId);
        if (maybeWidget.isPresent()) {
            Widget widget = maybeWidget.get();
            widget.setName(newWidget.getName());
            widget.setOrder(newWidget.getOrder());
            widget.setText(newWidget.getText());
            widget.setClassName(newWidget.getClassName());
            widget.setStyle(newWidget.getStyle());
            widget.setHeight(newWidget.getHeight());
            widget.setWidth(newWidget.getWidth());
            widget.setSize(newWidget.getSize());
            widget.setHref(newWidget.getHref());
            widget.setSrc(newWidget.getSrc());
            widget.setListItems(newWidget.getListItems());
            widget.setListType(newWidget.getListType());

            widgetRepository.save(widget);
            return widget;
        }
        return null;
    }

    @DeleteMapping("/api/widget/{widgetId}")
    public void deleteWidget(@PathVariable("widgetId") int widgetId) {
        widgetRepository.deleteById(widgetId);
    }
}
