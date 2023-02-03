package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter @Setter @NoArgsConstructor @ToString(exclude = "course")
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "lesson_id_generator")
    @SequenceGenerator(name = "lesson_id_generator",
    sequenceName = "lesson_seq",
    allocationSize = 1)
    private Long id;
    private String name;
    @Column(name = "video_link")
    private String videoLink;




    @ManyToOne(cascade = {DETACH, MERGE, PERSIST, REFRESH}, fetch = FetchType.LAZY)
    private Course course;

    @OneToMany(cascade = {ALL}, fetch = FetchType.EAGER)
    private List<Task> tasks =new ArrayList<>();


    public Lesson(String name, String videoLink) {
        this.name = name;
        this.videoLink = videoLink;
    }


}
