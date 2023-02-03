package peaksoft.services.impl;

import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.repositories.InstructorRepository;
import peaksoft.repositories.impl.InstructorRepositoryImpl;
import peaksoft.services.InstructorService;

import java.util.List;


public class InstructorServiceImpl implements InstructorService {
    InstructorRepository instructorRepository = new InstructorRepositoryImpl();
    @Override
    public String saveInstructor(Instructor instructor) {
        return instructorRepository.saveInstructor(instructor);
    }

    @Override
    public Instructor updateInstructor(Long instructorId, Instructor instructor) {
        return instructorRepository.updateInstructor(instructorId,instructor);
    }

    @Override
    public Instructor getInstructorById(Long instructorId) {
        return instructorRepository.getInstructorById(instructorId);
    }

    @Override
    public List<Instructor> instructorsByCourseId(Long courseId) {
        return instructorRepository.instructorsByCourseId(courseId);
    }

    @Override
    public String deleteInstructorById(Long instructorId) {
        return instructorRepository.deleteInstructorById(instructorId);
    }

    @Override
    public void assignInstructorToCourse(Long instructorId, Long courseId) {
        instructorRepository.assignInstructorToCourse(instructorId,courseId);
    }
}
