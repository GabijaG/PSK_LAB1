package lt.vu.persistence;

import lt.vu.entities.Member;
import lt.vu.entities.TaskList;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class TasksListsDAO {
    @Inject
    private EntityManager em;

    public List<TaskList> loadAll() {
        return em.createNamedQuery("TaskList.findAll", TaskList.class).getResultList();
    }
//    public List<TaskList> loadAlike(String fragment) {
//        return em.createQuery("select t from Team as t where t.name like :fragment", Team.class)
//                .setParameter("fragment", fragment)
//                .getResultList();
//    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(TaskList taskList){
        this.em.persist(taskList);
    }

    public TaskList findOne(Integer id) {
        return em.find(TaskList.class, id);
    }

    public TaskList findByName(String name){
        return em.createQuery("SELECT l FROM TaskList as l WHERE l.name LIKE :name", TaskList.class)
            .setParameter("name", name).getSingleResult();}

    public void remove(TaskList taskList) {this.em.remove(taskList);}
}
