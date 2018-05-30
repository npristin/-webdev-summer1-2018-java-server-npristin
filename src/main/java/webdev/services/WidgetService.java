package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Lesson;
import webdev.models.Widget;
import webdev.repositories.LessonRepository;
import webdev.repositories.WidgetRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {

    @Autowired
    WidgetRepository widgetRepository;
    @Autowired
    LessonRepository lessonRepository;

    @GetMapping("/api/widget")
    public List<Widget> findAllWidgets() {
        return (List<Widget>) widgetRepository.findAllOrderByWidgetOrder();
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
            widget.setLessonId(lessonId);

            widgetRepository.save(widget);
            lessonRepository.save(lesson);
        }
        return widget;
    }

    @PostMapping("/api/lesson/{lessonId}/widget/save")
    public List<Widget> saveWidgets(@RequestBody List<Widget> widgets, @PathVariable("lessonId") int lessonId) {
        Optional<Lesson> maybeLesson = lessonRepository.findById(lessonId);
        if (maybeLesson.isPresent()) {
            Lesson lesson = maybeLesson.get();
            lesson.setWidgets(widgets);

            // get the number of widgets that have an assigned size
            int orderedWidgets = 0;
            for (Widget w : widgets) {
                if (w.getWidgetOrder() > 0) {
                    orderedWidgets += 1;
                }
            }

            for (Widget w : widgets) {
                if (w.getLessonId() == 0) {
                    w.setLesson(lesson);
                    w.setLessonId(lessonId);
                }
                if (w.getWidgetOrder() == 0) {
                    w.setWidgetOrder(orderedWidgets + 1);
                    orderedWidgets += 1;
                }
                widgetRepository.save(w);
            }
            lessonRepository.save(lesson);
        }
        return widgets;
    }

    @PutMapping("/api/widget/{widgetId}")
    public Widget updateWidget(@RequestBody Widget newWidget, @PathVariable("widgetId") int widgetId) {
        Optional<Widget> maybeWidget = widgetRepository.findById(widgetId);
        if (maybeWidget.isPresent()) {
            Widget widget = maybeWidget.get();
            widget.setName(newWidget.getName());
            widget.setWidgetOrder(newWidget.getWidgetOrder());
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

    @PostMapping("/api/lesson/{lessonId}/widget/{widgetId}/order/decrement")
    public List<Widget> decrementOrder(@RequestBody List<Widget> widgets, @PathVariable("widgetId") int widgetId,
                               @PathVariable("lessonId") int lessonId) {
        List<Widget> lessonWidgets = widgets.stream().filter(w -> w.getLessonId() == lessonId)
                .collect(Collectors.toList());

        for (int i = 0; i < lessonWidgets.size(); i++) {
            Widget w = lessonWidgets.get(i);
            if (w.getId() == widgetId && i > 0) {
                // replace the order of the two widgets
                int originalOrder = w.getWidgetOrder();
                Widget priorWidget = lessonWidgets.get(i - 1);
                w.setWidgetOrder(priorWidget.getWidgetOrder());
                priorWidget.setWidgetOrder(originalOrder);

                widgetRepository.save(w);
                widgetRepository.save(priorWidget);
                break;
            }
        }
        return (List<Widget>) widgetRepository.findAllOrderByWidgetOrder();
    }

}
