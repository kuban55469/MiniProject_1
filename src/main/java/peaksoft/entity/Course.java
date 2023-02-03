package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "course_id_generator")
    @SequenceGenerator(name = "course_id_generator",
            sequenceName = "course-seq",
            allocationSize = 1)
    private Long id;

    @Column(name = "course_name")
    private String courseName;
    private int duration;
    @Column(name = "create_at")
    private LocalDate createAt;
    @Column(name = "image_link")
    private String imageLink;
    private String description;



    @ManyToMany(cascade = {PERSIST, DETACH, MERGE, REFRESH}, fetch = FetchType.EAGER)
    private List<Instructor> instructors = new ArrayList<>();

    @OneToMany(cascade = {ALL}, fetch = FetchType.LAZY,mappedBy = "course")
    private List<Lesson> lessons;


    public Course(String courseName, int duration, LocalDate createAt, String imageLink, String description) {
        this.courseName = courseName;
        this.duration = duration;
        this.createAt = createAt;
        this.imageLink = imageLink;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Course{" +
               "courseName='" + courseName + '\'' +
               ", duration=" + duration +
               ", createAt=" + createAt +
               ", imageLink='" + imageLink + '\'' +
               ", description='" + description + '\'' +
               '}';
    }
}
