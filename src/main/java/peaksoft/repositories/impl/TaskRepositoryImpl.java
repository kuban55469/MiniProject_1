package peaksoft.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import peaksoft.config.HibernateConfig;
import peaksoft.entity.Lesson;
import peaksoft.entity.Task;
import peaksoft.repositories.TaskRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 26.01.2023
 */
public class TaskRepositoryImpl implements TaskRepository {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.entityManagerFactory();
    @Override
    public String saveTask(Task task, Long lessonId) {
        List<Task> list = new ArrayList<>();
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Lesson lesson = entityManager.find(Lesson.class, lessonId);
            list.addAll(lesson.getTasks());
            list.add(task);
            lesson.setTasks(list);
            entityManager.persist(task);
            entityManager.merge(lesson);
            entityManager.getTransaction().commit();
            entityManager.close();
            return task.getName() + " is saved.";
        }catch (HibernateException e){
            return e.getMessage();
        }
    }

    @Override
    public Task updateTask(Long taskId, Task task) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Task task1 = entityManager.createQuery("from Task where id = :id", Task.class)
                    .setParameter("id", taskId).getSingleResult();
            task1.setName(task.getName());
            task1.setDeadline(task.getDeadline());
            task1.setTask(task.getTask());
            entityManager.merge(task1);
            entityManager.getTransaction().commit();
            entityManager.close();
            return task1;
        }catch (HibernateException e){
            throw new RuntimeException();
        }
    }

    @Override
    public List<Task> getAllTaskByLessonId(Long lessonId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            List<Lesson> lessons = entityManager.createQuery("from Lesson l  where l.id = :id", Lesson.class)
                    .setParameter("id", lessonId).getResultList();
            Lesson lesson = lessons.get(0);
            List<Task> tasks = new ArrayList<>(lesson.getTasks());
            entityManager.getTransaction().commit();
            entityManager.close();
            return tasks;
        }catch (HibernateException e){
            throw new RuntimeException();
        }
    }

    @Override
    public String deleteTaskById(Long taskId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Task task = entityManager.find(Task.class, taskId);
            entityManager.remove(task);
            entityManager.getTransaction().commit();
            entityManager.close();
            return task.getName() + " is deleted.";
        }catch (HibernateException e){
            return e.getMessage();
        }
    }
}
