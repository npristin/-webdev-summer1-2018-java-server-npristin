package webdev.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name="widget")
public class Widget {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    private int widgetOrder;
    private String text;
    private String className;
    private String style;
    private String width;
    private String height;
    private int size;
    private String href;
    private String src;
    private String listItems;
    private ListType listType;
    @ManyToOne
    @JsonIgnore
    private Lesson lesson;
    private int lessonIdDup;

    public Widget(int id, String name, int widgetOrder, String text, String className, String style, String width,
                  String height, int size, String href, String src, String listItems, ListType listType, Lesson lesson,
                  int lessonIdDup) {
        this.id = id;
        this.name = name;
        this.widgetOrder = widgetOrder;
        this.text = text;
        this.className = className;
        this.style = style;
        this.width = width;
        this.height = height;
        this.size = size;
        this.href = href;
        this.src = src;
        this.listItems = listItems;
        this.listType = listType;
        this.lesson = lesson;
        this.lessonIdDup = lessonIdDup;
    }

    private Widget() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidgetOrder() {
        return widgetOrder;
    }

    public void setWidgetOrder(int widgetOrder) {
        this.widgetOrder = widgetOrder;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getListItems() {
        return listItems;
    }

    public void setListItems(String listItems) {
        this.listItems = listItems;
    }

    public ListType getListType() {
        return listType;
    }

    public void setListType(ListType listType) {
        this.listType = listType;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public int getLessonId() { return lessonIdDup; }

    public void setLessonId(int lessonId) { this.lessonIdDup = lessonId; }
}
