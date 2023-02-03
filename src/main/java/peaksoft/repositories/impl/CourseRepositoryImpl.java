package peaksoft.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import peaksoft.config.HibernateConfig;
import peaksoft.entity.Course;
import peaksoft.repositories.CourseRepository;

import java.util.ArrayList;
import java.util.List;


public class CourseRepositoryImpl implements CourseRepository {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.entityManagerFactory();

    @Override
    public String saveCourse(Course course) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(course);
            entityManager.getTransaction().commit();
            entityManager.close();
            return course.getCourseName() + " is saved.";
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Course gerCourseById(Long courseId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
//            Course course1 = entityManager.createQuery("select c from Course  c  where c.id = :id", Course.class)
//                    .setParameter("id", courseId).getSingleResult();
            Course course = entityManager.find(Course.class, courseId);
            entityManager.getTransaction().commit();
            entityManager.close();
            return course;
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Course> getAllCourse(String orgOrDesc) {
        List<Course> coursesAscOrDesc = new ArrayList<>();
        try {
            switch (orgOrDesc) {
                case "asc" -> {
                    EntityManager entityManager = entityManagerFactory.createEntityManager();
                    entityManager.getTransaction().begin();
                    List<Course> courses = entityManager.createQuery("from Course order by createAt",
                            Course.class).getResultList();
                    entityManager.getTransaction().commit();
                    entityManager.close();
                    return courses;
                }
                case "desc" -> {
                    EntityManager entityManager = entityManagerFactory.createEntityManager();
                    entityManager.getTransaction().begin();
                    List<Course> courses = entityManager.createQuery("from Course order by createAt desc ",
                            Course.class).getResultList();
                    entityManager.getTransaction().commit();
                    entityManager.close();
                    return courses;
                }
                default -> System.out.println("Error type.");
            }
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return coursesAscOrDesc;
    }

    @Override
    public String updateCourse(Long courseId, Course course) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Course course1 = entityManager.find(Course.class, courseId);
            course1.setCourseName(course.getCourseName());
            course1.setDuration(course.getDuration());
            course1.setCreateAt(course.getCreateAt());
            course1.setImageLink(course.getImageLink());
            course1.setLessons(course.getLessons());
            course1.setInstructors(course.getInstructors());
            course1.setDescription(course.getDescription());
            entityManager.merge(course1);
            entityManager.getTransaction().commit();
            entityManager.close();
            return course.getCourseName() + " is updated in new course: " + course1.getCourseName();
        }catch (HibernateException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteCourseById(Long courseId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class, courseId);
            entityManager.remove(course);
            entityManager.getTransaction().commit();
            entityManager.close();
            return course.getCourseName() + " is deleted.";
        }catch (HibernateException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Course getCourseByName(String courseName) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            List<Course> courseList = entityManager.createQuery("from Course", Course.class).getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            for (Course course : courseList) {
                if (course.getCourseName().equals(courseName))
                    return course;
            }
            return null;
        }catch (HibernateException e){
            throw new RuntimeException(e);
        }

    }
}
