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
        @NamedQuery(name = "TaskList.findAll", query = "select t from TaskList as t")
})
@Table(name="TASKLIST")
@Getter @Setter
public class TaskList implements Serializable {

    public TaskList(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "list")
    private List<Task> tasks = new ArrayList<>();

    @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name="MEMBER_ID")
    private Member listCreator;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskList list = (TaskList) o;
        return Objects.equals(name, list.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
