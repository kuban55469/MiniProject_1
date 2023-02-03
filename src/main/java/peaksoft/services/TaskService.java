package peaksoft.services;


import peaksoft.entity.Task;

import java.util.List;

public interface TaskService {
    String saveTask(Task task, Long lessonId);
    Task updateTask(Long taskId, Task task);
    List<Task> getAllTaskByLessonId(Long lessonId);
    String deleteTaskById(Long taskId);
}
