package peaksoft.repositories;

import peaksoft.entity.Course;

import java.util.List;


public interface CourseRepository {
    String saveCourse(Course course);
    Course gerCourseById(Long courseId);
    List<Course> getAllCourse(String orgOrDesc);
    String updateCourse(Long courseId, Course course);
    String deleteCourseById(Long courseId);
    Course getCourseByName(String courseName);
}
