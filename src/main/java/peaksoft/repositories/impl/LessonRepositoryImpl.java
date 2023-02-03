package peaksoft.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import peaksoft.config.HibernateConfig;
import peaksoft.entity.Course;
import peaksoft.entity.Lesson;
import peaksoft.repositories.LessonRepository;

import java.util.ArrayList;
import java.util.List;


public class LessonRepositoryImpl implements LessonRepository {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.entityManagerFactory();
    @Override
    public String saveLesson(Lesson lesson, Long courseId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class, courseId);
            lesson.setCourse(course );
            entityManager.persist(lesson);
            entityManager.merge(course);
            entityManager.getTransaction().commit();
            entityManager.close();
            return lesson.getName() + " is saved.";
        }catch (HibernateException e){
            throw new RuntimeException();
        }
    }

    @Override
    public Lesson updateLesson(Long lessonId, Lesson lesson, Long courseId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Lesson lesson1 = entityManager.createQuery("from Lesson where id = :id", Lesson.class)
                    .setParameter("id", lessonId).getSingleResult();
            Course course = entityManager.createQuery("from Course where id = :id", Course.class)
                    .setParameter("id", courseId).getSingleResult();
            lesson1.setName(lesson.getName());
            lesson1.setVideoLink(lesson.getVideoLink());
            lesson1.setCourse(lesson.getCourse());
            lesson1.setTasks(lesson.getTasks());
            lesson1.setCourse(course);
            entityManager.merge(lesson1);
            entityManager.getTransaction().commit();
            entityManager.close();
            return lesson;
        }catch (HibernateException e){
            throw new RuntimeException();
        }
    }

    @Override
    public Lesson getLessonById(Long lessonId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Lesson lesson = entityManager.createQuery("from Lesson where id = :id", Lesson.class)
                    .setParameter("id", lessonId).getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();
            return lesson;
        }catch (HibernateException e){
            throw new RuntimeException();
        }
    }

    @Override
    public List<Lesson> getLessonsByCourseId(Long courseId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            List<Course> courses = entityManager.createQuery("from Course c where c.id = :id", Course.class)
                    .setParameter("id", courseId).getResultList();
            Course course = courses.get(0);
            List<Lesson> lessonsList = new ArrayList<>(course.getLessons());
            entityManager.getTransaction().commit();
            entityManager.close();
            return lessonsList;
        }catch (HibernateException e){
            throw new RuntimeException();
        }
    }
}
