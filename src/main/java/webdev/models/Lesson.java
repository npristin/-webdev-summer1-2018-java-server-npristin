package webdev.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name="lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String title;
    @ManyToOne
    @JsonIgnore
    private Module module;
    @OneToMany(mappedBy="lesson")
    @JsonIgnore
    private List<Widget> widgets;
    @OneToMany(mappedBy = "lesson")
    @JsonIgnore
    private List<Exam> exams;

    public Lesson(int id, String title, Module module, List<Widget> widgets, List<Exam> exams) {
        this.id = id;
        this.title = title;
        this.module = module;
        this.widgets = widgets;
        this.exams = exams;
    }

    public Lesson() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public List<Widget> getWidgets() {
        return widgets;
    }

    public void setWidgets(List<Widget> widgets) {
        this.widgets = widgets;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
}
