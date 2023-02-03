package peaksoft.services.impl;

import peaksoft.entity.Task;
import peaksoft.repositories.TaskRepository;
import peaksoft.repositories.impl.TaskRepositoryImpl;
import peaksoft.services.TaskService;

import java.util.List;


public class TaskServiceImpl implements TaskService {
    TaskRepository taskRepository = new TaskRepositoryImpl();
    @Override
    public String saveTask(Task task, Long lessonId) {
        return taskRepository.saveTask(task,lessonId);
    }

    @Override
    public Task updateTask(Long taskId, Task task) {
        return taskRepository.updateTask(taskId,task);
    }

    @Override
    public List<Task> getAllTaskByLessonId(Long lessonId) {
        return taskRepository.getAllTaskByLessonId(lessonId);
    }

    @Override
    public String deleteTaskById(Long taskId) {
        return taskRepository.deleteTaskById(taskId);
    }
}
