package peaksoft.services.impl;


import peaksoft.entity.Course;
import peaksoft.repositories.CourseRepository;
import peaksoft.repositories.impl.CourseRepositoryImpl;
import peaksoft.services.CourseService;

import java.util.List;

public class CourseServiceImpl implements CourseService {
    CourseRepository courseRepository= new CourseRepositoryImpl();
    @Override
    public String saveCourse(Course course) {
        return courseRepository.saveCourse(course);
    }

    @Override
    public Course gerCourseById(Long courseId) {
        return courseRepository.gerCourseById(courseId);
    }

    @Override
    public List<Course> getAllCourse(String ascOrDesc) {
        return courseRepository.getAllCourse(ascOrDesc);
    }

    @Override
    public String updateCourse(Long courseId, Course course) {
       return courseRepository.updateCourse(courseId,course);
    }

    @Override
    public String deleteCourseById(Long courseId) {
        return courseRepository.deleteCourseById(courseId);
    }

    @Override
    public Course getCourseByName(String courseName) {
        return courseRepository.getCourseByName(courseName);
    }
}
