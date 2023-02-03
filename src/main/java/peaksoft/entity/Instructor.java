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
@Getter @Setter @NoArgsConstructor @ToString(exclude = "courses")
@Table(name = "instructors")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "instructor_id_generator")
    @SequenceGenerator(name = "instructor_id_generator",
            sequenceName = "instructor_seq",
            allocationSize = 1)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;


//1
//    @ManyToMany
//    @JoinTable(name = "instructors_courses",
//    joinColumns = @JoinColumn(name = "instructors_id"),
//    inverseJoinColumns = @JoinColumn(name = "courses_id"))
//    private List<Course> courses;


    @ManyToMany(mappedBy = "instructors",cascade = {DETACH, MERGE, REFRESH},fetch = FetchType.LAZY)
    private List<Course> courses = new ArrayList<>();

    public Instructor(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

}
