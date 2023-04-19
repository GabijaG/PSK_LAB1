package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Member;
import lt.vu.entities.TaskList;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.MembersDAO;
import lt.vu.persistence.TasksListsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Model
public class ListsForMembers implements Serializable {
    @Inject
    private MembersDAO membersDAO;
    @Inject
    private TasksListsDAO tasksListsDAO;

    @Getter @Setter
    private Member member;

    @Getter @Setter
    private TaskList listToCreate = new TaskList();

    @Getter @Setter
    private TaskList listToDelete = new TaskList();

    @Getter @Setter
    private List<TaskList> allLists;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer memberId = Integer.parseInt(requestParameters.get("memberId"));
        this.member = membersDAO.findOne(memberId);
        this.allLists = membersDAO.findTaskLists(this.member);
    }
    @Transactional
    @LoggedInvocation
    public void createTaskList() {
        if(allLists.stream().noneMatch(x -> x.getName().equals(listToCreate.getName()))) {
            listToCreate.setListCreator(this.member);
            tasksListsDAO.persist(listToCreate);
        }
        //TODO: print error otherwise.

    }
    @Transactional
    @LoggedInvocation
    public void deleteTaskList() {
        if(allLists.stream().anyMatch(x -> x.getName().equals(listToDelete.getName()))){
            listToDelete = tasksListsDAO.findByName(listToDelete.getName());
            tasksListsDAO.remove(listToDelete);
        }
        //TODO: print error otherwise.
    }
}
