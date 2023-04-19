package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "MEMBER")
@Getter @Setter
public class Member implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    @OneToMany(targetEntity = TaskList.class, cascade = CascadeType.ALL, mappedBy = "listCreator")
    private List<TaskList> taskLists = new ArrayList<>();

    @ManyToMany(mappedBy="assignedMembers", cascade = CascadeType.PERSIST)
    private List<Task> assignedTasks = new ArrayList<>();

    public Member() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id) &&
                Objects.equals(username, member.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password);
    }
}