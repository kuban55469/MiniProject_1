package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter @Setter @NoArgsConstructor @ToString
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "task_id_generator")
    @SequenceGenerator(name = "task_id_generator",
    sequenceName = "task_seq",
    allocationSize = 1)
    private Long id;
    private String name;
    private int deadline;
    private String task;


    public Task(String name, int deadline, String task) {
        this.name = name;
        this.deadline = deadline;
        this.task = task;
    }
}
