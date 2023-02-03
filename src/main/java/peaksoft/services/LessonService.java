package peaksoft.services;

import peaksoft.entity.Lesson;

import java.util.List;

public interface LessonService {
    String saveLesson(Lesson lesson,Long courseId);
    Lesson updateLesson(Long lessonId, Lesson lesson, Long courseId);
    Lesson getLessonById(Long lessonId);
    List<Lesson> getLessonsByCourseId(Long courseId);
}
