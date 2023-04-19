package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.TaskList;
import lt.vu.persistence.TasksListsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class TaskLists implements Serializable {
    @Inject
    private TasksListsDAO tasksListsDAO;

    @Getter @Setter
    private TaskList taskListToCreate = new TaskList();
    @Getter @Setter
    private List<TaskList> allLists;

    @PostConstruct
    public void init(){
        loadAllLists();
    }

    @Transactional
    public void createTaskList(){
        this.tasksListsDAO.persist(taskListToCreate);
    }

    private void loadAllLists(){
        this.allLists = tasksListsDAO.loadAll();
    }
}