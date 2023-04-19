package lt.vu.persistence;

import lt.vu.entities.Task;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class TasksDAO {
    @Inject
    private EntityManager em;

    public void persist(Task task){
        this.em.persist(task);
    }

    public Task findOne(Integer id){
        return em.find(Task.class, id);
    }

    public Task update(Task task){
        return em.merge(task);
    }

    public void remove(Task task) { this.em.remove(task); }
}
