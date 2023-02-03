package peaksoft;

import peaksoft.config.HibernateConfig;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.entity.Lesson;
import peaksoft.entity.Task;
import peaksoft.services.CourseService;
import peaksoft.services.InstructorService;
import peaksoft.services.LessonService;
import peaksoft.services.TaskService;
import peaksoft.services.impl.CourseServiceImpl;
import peaksoft.services.impl.InstructorServiceImpl;
import peaksoft.services.impl.LessonServiceImpl;
import peaksoft.services.impl.TaskServiceImpl;

import javax.script.ScriptContext;
import java.time.LocalDate;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
//        HibernateConfig.entityManagerFactory();
        methods();

    }
    static void commands(){
        System.out.println("""
                -------------Commands--------------
                Press 1 to save course.
                Press 2 to get course by id.
                Press 3 to get all courses.
                Press 4 to delete course by id.
                Press 5 to get course by name.
                Press 6 to update course.
                
                Press 7 to save instructor.
                Press 8 to update instructor.
                Press 9 to get instructor by id.
                Press 10 to get instructor by course id.
                Press 11 to delete by instructor id.
                Press 12 to assign instructor to course.
                
                Press 13 to save lesson.
                Press 14 to update lesson.
                Press 15 to get lesson by id.
                Press 16 to get lesson by course id.
                
                Press 17 to save task.
                Press 18 to update task.
                Press 19 to get all task by lesson id.
                Press 20 to delete task by id.
                """);
    }

    static void methods(){
        CourseService courseService = new CourseServiceImpl();
        InstructorService instructorService = new InstructorServiceImpl();
        LessonService lessonService = new LessonServiceImpl();
        TaskService taskService = new TaskServiceImpl();
        String number = "null";
        while (!number.equals("x")){
            commands();
            System.out.print("Choose a command: ");
            number = new Scanner(System.in).nextLine();
            try {
                if (Character.isDigit(number.charAt(0))){
                    switch (number){
                        case "1" -> {
                            Course course = new Course();
                            System.out.print("Write course name: ");
                            course.setCourseName(new Scanner(System.in).nextLine());
                            System.out.print("Write description: ");
                            course.setDescription(new Scanner(System.in).nextLine());
                            System.out.print("Write image link: ");
                            course.setImageLink(new Scanner(System.in).nextLine());
                            System.out.print("Enter duration: ");
                            course.setDuration(new Scanner(System.in).nextInt());
                            System.out.print("Write year when course create at: ");
                            int year = new Scanner(System.in).nextInt();
                            while(year<1990){
                                year = new Scanner(System.in).nextInt();
                            }
                            int month = new Random().nextInt(12);
                            int day = new Random().nextInt(30);
                            course.setCreateAt(LocalDate.of(year, month, day));
                            courseService.saveCourse(course);
                        }
                        case "2" -> {
                            System.out.print("Write course id: ");
                            Long id = new Scanner(System.in).nextLong();
                            System.out.println(courseService.gerCourseById(id));
                        }
                        case "3" -> {
                            System.out.print("Write asc or desc: ");
                            String ascOrDesc = new Scanner(System.in).nextLine();
                            courseService.getAllCourse(ascOrDesc).forEach(System.out::println);
                        }
                        case "4" -> {
                            System.out.print("Write course id: ");
                            System.out.println(courseService.deleteCourseById(new Scanner(System.in).nextLong()));
                        }
                        case "5" -> {
                            System.out.print("Write course name: ");
                            System.out.println(courseService.getCourseByName(new Scanner(System.in).nextLine()));
                        }
                        case "6" -> {
                            System.out.print("Write course id from update: ");
                            Long id = new Scanner(System.in).nextLong();
                            System.out.print("Write course name: ");
                            String  courseName = (new Scanner(System.in).nextLine());
                            System.out.print("Write description: ");
                            String description = (new Scanner(System.in).nextLine());
                            System.out.print("Write image link: ");
                            String imageLink = (new Scanner(System.in).nextLine());
                            System.out.print("Enter duration: ");
                            int duration = (new Scanner(System.in).nextInt());
                            System.out.print("Write year when course create at: ");
                            int year = new Scanner(System.in).nextInt();
                            int month = new Random().nextInt(12);
                            int day = new Random().nextInt(30);
                            LocalDate createAt = (LocalDate.of(year, month, day));
                            Course course = new Course(courseName,duration,createAt,imageLink,description);
                            System.out.println(courseService.updateCourse(id, course));
                        }
                        case "7" -> {
                            System.out.print("Write first name: ");
                            String name = new Scanner(System.in).nextLine();
                            System.out.print("Write last name: ");
                            String lastName = new Scanner(System.in).nextLine();
                            System.out.print("Write email: ");
                            String email = new Scanner(System.in).nextLine();
                            System.out.print("Write phone number: ");
                            String phoneNumber = new Scanner(System.in).nextLine();
                            Instructor instructor = new Instructor(name,lastName,email,phoneNumber);
                            System.out.println(instructorService.saveInstructor(instructor));
                        }
                        case "8" -> {
                            System.out.print("Write instructor id from update: ");
                            Long id = new Scanner(System.in).nextLong();
                            System.out.print("Write first name: ");
                            String name = new Scanner(System.in).nextLine();
                            System.out.print("Write last name: ");
                            String lastName = new Scanner(System.in).nextLine();
                            System.out.print("Write email: ");
                            String email = new Scanner(System.in).nextLine();
                            System.out.print("Write phone number: ");
                            String phoneNumber = new Scanner(System.in).nextLine();
                            Instructor instructor = new Instructor(name,lastName,email,phoneNumber);
                            System.out.println(instructorService.updateInstructor(id,instructor));
                        }
                        case "9" -> {
                            System.out.print("Write instructor id: ");
                            Long id = new Scanner(System.in).nextLong();
                            System.out.println(instructorService.getInstructorById(id));
                        }
                        case "10" -> {
                            System.out.print("Write course id get instructors: ");
                            instructorService.instructorsByCourseId(new Scanner(System.in).nextLong()).forEach(System.out::println);
                        }
                        case "11" -> {
                            System.out.print("Write instructor id: ");
                            System.out.println(instructorService.deleteInstructorById(new Scanner(System.in).nextLong()));
                        }
                        case "12" -> {
                            System.out.print("Write course id: ");
                            Long courseId  = new Scanner(System.in).nextLong();
                            System.out.print("Write instructor id: ");
                            Long instructorId  = new Scanner(System.in).nextLong();
                            instructorService.assignInstructorToCourse(instructorId,courseId);
                        }
                        case "13" ->{
                            System.out.print("Write lesson name: ");
                            String name = new Scanner(System.in).nextLine();
                            System.out.print("Write video link: ");
                            String link = new Scanner(System.in).nextLine();
                            System.out.print("Write course id: ");
                            Long courseId = new Scanner(System.in).nextLong();
                            System.out.println(lessonService.saveLesson(new Lesson(name, link), courseId));
                        }
                        case "14" -> {
                            System.out.print("Write lesson id: ");
                            Long id = new Scanner(System.in).nextLong();
                            System.out.print("Write new lesson name: ");
                            String name = new Scanner(System.in).nextLine();
                            System.out.print("Write new lesson link: ");
                            String link = new Scanner(System.in).nextLine();
                            System.out.print("Write course id: ");
                            Long courseId = new Scanner(System.in).nextLong();
                            System.out.println(lessonService.updateLesson(id, new Lesson(name, link), courseId));
                        }
                        case "15" -> {
                            System.out.print("Write lesson id: ");
                            System.out.println(lessonService.getLessonById(new Scanner(System.in).nextLong()));
                        }
                        case "16" -> {
                            System.out.print("Write course id to get all lessons: ");
                            Long courseId = new Scanner(System.in).nextLong();
                            lessonService.getLessonsByCourseId(courseId).forEach(System.out::println);
                        }
                        case "17" -> {
                            System.out.print("Write task name: ");
                            String name = new Scanner(System.in).nextLine();
                            System.out.print("Write deadline: ");
                            int deadline = new Scanner(System.in).nextInt();
                            System.out.print("Write task: ");
                            String nameTask = new Scanner(System.in).nextLine();
                            System.out.print("Write lesson id: ");
                            Long lessonId = new Scanner(System.in).nextLong();
                            System.out.println(taskService.saveTask(new Task(name, deadline, nameTask), lessonId));
                        }
                        case "18" -> {
                            System.out.print("Write task id: ");
                            Long taskId = new Scanner(System.in).nextLong();
                            System.out.print("Write task name: ");
                            String name = new Scanner(System.in).nextLine();
                            System.out.print("Write deadline: ");
                            int ded = new Scanner(System.in).nextInt();
                            System.out.print("Write task: ");
                            String task = new Scanner(System.in).nextLine();
                            System.out.println(taskService.updateTask(taskId, new Task(name, ded, task)));
                        }
                        case "19" -> {
                            System.out.print("Write lesson id to get all tasks: ");
                            Long id = new Scanner(System.in).nextLong();
                            taskService.getAllTaskByLessonId(id).forEach(System.out::println);
                        }
                        case "20" -> {
                            System.out.print("Write task id: ");
                            Long id = new Scanner(System.in).nextLong();
                            System.out.println(taskService.deleteTaskById(id));
                        }
                        default -> System.out.println("Please write remember.");
                    }
                }
                else {
                    throw new RuntimeException();
                }
            }catch (RuntimeException e){
                System.out.println("It is not a button.");
            }
        }
    }
}
