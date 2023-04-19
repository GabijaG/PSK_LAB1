package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Member;
import lt.vu.entities.Task;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.MembersDAO;
import lt.vu.persistence.TasksDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class MembersForTask {

    @Inject
    private MembersDAO membersDAO;

    @Inject
    private TasksDAO tasksDAO;

    @Getter @Setter
    private Member memberToCreate = new Member();

    @Getter @Setter
    private Task task;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer taskId = Integer.parseInt(requestParameters.get("taskId"));
        this.task = tasksDAO.findOne(taskId);
    }

    @Transactional
    @LoggedInvocation
    public void assignMember() {
        List<Member> membersToAssign = membersDAO.findByName(this.memberToCreate.getUsername());
        if(membersToAssign.isEmpty()){
            System.out.println("No member found");
            return; //TODO: return an error message
        }
        Member memberToAssign = membersToAssign.get(0);
        if (task.getAssignedMembers().contains(memberToAssign))
            return; //FacesContext.getCurrentInstance().addMessage("Member already assigned", new FacesMessage("Member already assigned"));
        System.out.println("Member found. Should work.");
        task.getAssignedMembers().add(memberToAssign);
        tasksDAO.persist(task);
    }
}
