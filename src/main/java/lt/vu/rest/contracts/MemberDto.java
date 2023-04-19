package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Task;

@Getter @Setter
public class MemberDto {
    private String Username;
    private Task task;
}
