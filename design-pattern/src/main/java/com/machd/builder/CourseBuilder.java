package com.machd.builder;

/**
 * 课程的构建者类
 *
 * @author macd
 * @version 3
 * @since 3.0
 */
public class CourseBuilder {
    private Course course = new Course();
    public Course builder() {
        return course;
    }

    // 返回当前的对象，形成一个执行链路
    public CourseBuilder addName(String name) {
        course.setName(name);
        return this;
    }

    public CourseBuilder addNote(String note) {
        course.setNote(note);
        return this;
    }

    public CourseBuilder addHomeWork(String homework) {
        course.setHomework(homework);
        return this;
    }

}
