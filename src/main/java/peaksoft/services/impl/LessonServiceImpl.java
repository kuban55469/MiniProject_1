package peaksoft.services.impl;

import peaksoft.entity.Lesson;
import peaksoft.repositories.LessonRepository;
import peaksoft.repositories.impl.LessonRepositoryImpl;
import peaksoft.services.LessonService;

import java.util.List;


public class LessonServiceImpl implements LessonService {
    LessonRepository lessonRepository = new LessonRepositoryImpl();
    @Override
    public String saveLesson(Lesson lesson,Long courseId) {
        return lessonRepository.saveLesson(lesson,courseId);
    }

    @Override
    public Lesson updateLesson(Long lessonId, Lesson lesson, Long courseId) {
        return lessonRepository.updateLesson(lessonId,lesson,courseId);
    }

    @Override
    public Lesson getLessonById(Long lessonId) {
        return lessonRepository.getLessonById(lessonId);
    }

    @Override
    public List<Lesson> getLessonsByCourseId(Long courseId) {
        return lessonRepository.getLessonsByCourseId(courseId);
    }
}
