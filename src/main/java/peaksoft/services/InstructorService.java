package peaksoft.services;

import peaksoft.entity.Course;
import peaksoft.entity.Instructor;

import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 26.01.2023
 */
public interface InstructorService {
    String saveInstructor(Instructor instructor);
    Instructor updateInstructor(Long instructorId, Instructor instructor);
    Instructor getInstructorById(Long instructorId);
    List<Instructor> instructorsByCourseId(Long courseId);
    String deleteInstructorById(Long instructorId);
    void assignInstructorToCourse(Long instructorId, Long courseId);

}
