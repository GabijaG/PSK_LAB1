package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Task.findAll", query = "select a from Task as a")
})
@Table(name = "TASK")
@Getter @Setter
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @Column(name = "STATUS")
    private Boolean status;

    @Column(name = "PRIORITY")
    private Integer priority;

    @ManyToOne
    @JoinColumn(name="LIST_ID")
    private TaskList list;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="TASK_MEMBER",
    joinColumns = @JoinColumn(name="TASK_ID"),
    inverseJoinColumns = @JoinColumn(name="MEMBER_ID"))
    private List<Member> assignedMembers = new ArrayList<>();

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    public Task(){
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(name, task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}