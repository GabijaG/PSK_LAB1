package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TaskDto {
    private String Name;

    private Boolean Status;

    private String TaskListName;
}
