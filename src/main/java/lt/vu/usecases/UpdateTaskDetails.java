package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Member;
import lt.vu.entities.Task;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.TasksDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter @Setter
public class UpdateTaskDetails implements Serializable {

    private Task task;

    @Inject
    private TasksDAO tasksDAO;

    @PostConstruct
    private void init(){
        System.out.println("UpdateTaskDetails INIT CALLED");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer taskId = Integer.parseInt(requestParameters.get("taskId"));
        this.task = tasksDAO.findOne(taskId);
    }

    @Transactional
    @LoggedInvocation
    public String updateTaskName() {
        try{
            tasksDAO.update(this.task);
        } catch (OptimisticLockException e) {
            return "/taskDetails.xhtml?faces-redirect=true&taskId=" + this.task.getId() + "&error=optimistic-lock-exception";
        }
        return "members.xhtml?faces-redirect=true";
    }
}