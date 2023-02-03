package peaksoft.services;


import peaksoft.entity.Course;

import java.util.List;

public interface CourseService {
    String saveCourse(Course course);
    Course gerCourseById(Long courseId);
    List<Course> getAllCourse(String askOrDesc);
    String updateCourse(Long courseId, Course course);
    String deleteCourseById(Long courseId);
    Course getCourseByName(String courseName);
}
