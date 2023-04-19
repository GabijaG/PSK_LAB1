package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Member;
import lt.vu.entities.TaskList;
import lt.vu.persistence.MembersDAO;
import lt.vu.persistence.TasksListsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@SessionScoped
public class MembersTasksLists implements Serializable {

    @Inject
    private MembersDAO membersDAO;
    @Inject
    private TasksListsDAO tasksListsDAO;
    @Getter @Setter
    private Member member;
    @Getter @Setter
    private List<TaskList> specificLists;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer memberId = Integer.parseInt(requestParameters.get("memberId")); //Numeric exception? null string?
        this.member = membersDAO.findOne(memberId);
        this.specificLists = membersDAO.findTaskLists(this.member);
    }
}
