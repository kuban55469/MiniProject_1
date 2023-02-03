package peaksoft.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import peaksoft.config.HibernateConfig;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.repositories.InstructorRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 26.01.2023
 */
public class InstructorRepositoryImpl implements InstructorRepository {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.entityManagerFactory();
    @Override
    public String saveInstructor(Instructor instructor) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(instructor);
            entityManager.getTransaction().commit();
            entityManager.close();
            return instructor.getFirstName() + " is saved.";
        }catch (HibernateException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Instructor updateInstructor(Long instructorId, Instructor instructor) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            Instructor instructor1 = entityManager.createQuery("from Instructor i where i.id = :id",
                    Instructor.class).setParameter("id", instructorId).getSingleResult();
            instructor1.setFirstName(instructor.getFirstName());
            instructor1.setLastName(instructor.getLastName());
            instructor1.setEmail(instructor.getEmail());
            instructor1.setPhoneNumber(instructor.getPhoneNumber());
            instructor1.setCourses(instructor.getCourses());

            entityManager.merge(instructor);
            entityManager.getTransaction().commit();
            entityManager.close();
            return instructor1;
        }catch (HibernateException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Instructor getInstructorById(Long instructorId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Instructor instructor = entityManager.createQuery("from Instructor i where i.id = :id",
                            Instructor.class)
                    .setParameter("id", instructorId).getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();
            return instructor;
        }catch (HibernateException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Instructor> instructorsByCourseId(Long courseId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            List<Course> courses = entityManager.createQuery("from Course c where c.id = :id", Course.class)
                    .setParameter("id", courseId).getResultList();
            Course course = courses.get(0);
            List<Instructor> instructors = new ArrayList<>(course.getInstructors());
            entityManager.getTransaction().commit();
            entityManager.close();
            return instructors;
        }catch (HibernateException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteInstructorById(Long instructorId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Instructor instructor = entityManager.createQuery("from Instructor where id = :id",
                    Instructor.class).setParameter("id", instructorId).getSingleResult();
            entityManager.remove(instructor);
            entityManager.getTransaction().commit();
            entityManager.close();
            return instructor.getFirstName() + " is deleted.";
        }catch (HibernateException e){
            throw new RuntimeException();
        }
    }

    @Override
    public void assignInstructorToCourse(Long instructorId, Long courseId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
//            Instructor instructor = entityManager.createQuery("from Instructor where id = :id",
//                            Instructor.class)
//                    .setParameter("id", instructorId).getSingleResult();
            Instructor instructor = entityManager.find(Instructor.class, instructorId);
            Course course = entityManager.find(Course.class, courseId);
            instructor.getCourses().add(course);
            course.getInstructors().add(instructor);
            entityManager.merge(instructor);
            entityManager.merge(course);
            entityManager.getTransaction().commit();
            entityManager.close();
            System.out.println("Assign instructor to course");
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }
}
