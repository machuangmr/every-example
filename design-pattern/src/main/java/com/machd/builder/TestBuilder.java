package com.machd.builder;



/**
 * 测试构建者模式
 *
 * @author macd
 * @version 3
 * @since 3.0
 */
public class TestBuilder {
    public static void main(String[] args) {
        CourseBuilder courseBuilder = new CourseBuilder();
        Course course = courseBuilder.addHomeWork("待做")
                .addName("p6")
                .addNote("正在整理")
                .builder();
        System.out.println(course);
    }
}
