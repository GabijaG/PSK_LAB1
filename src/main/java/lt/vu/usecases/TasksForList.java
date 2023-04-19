package lt.vu.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Task;
import lt.vu.entities.TaskList;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.MembersDAO;
import lt.vu.persistence.TasksDAO;
import lt.vu.persistence.TasksListsDAO;

@Model
public class TasksForList implements Serializable {

    @Inject
    private TasksListsDAO taskListsDAO;

    @Inject
    private TasksDAO tasksDAO;

    @Getter @Setter
    private TaskList taskList;

    @Getter @Setter
    private Task taskToCreate = new Task();

    @Getter @Setter
    private Task taskToDelete = new Task();

    @PostConstruct
    public void init() {
    }

    @Transactional
    @LoggedInvocation
    public void createTask(Integer taskListId) {
        this.taskList = taskListsDAO.findOne(taskListId);
        if(taskList.getTasks().stream().noneMatch(task -> task.getName().equals(taskToCreate.getName()))){
            taskToCreate.setList(this.taskList);
            tasksDAO.persist(taskToCreate);
        }
    }

    @Transactional
    @LoggedInvocation
    public void deleteTask(Integer taskId) {
        taskToDelete = tasksDAO.findOne(taskId);
        tasksDAO.remove(taskToDelete);
    }
}