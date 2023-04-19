package lt.vu.persistence;

import lt.vu.entities.Member;
import lt.vu.entities.TaskList;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class MembersDAO {
    @Inject
    private EntityManager em;

    public List<Member> loadAll() {
        return em.createQuery("SELECT m FROM Member AS m", Member.class).getResultList();
    }

    public List<Member> findByNamePassword(String name, String password){
        return em.createQuery("SELECT m FROM Member as m WHERE m.username LIKE :name AND m.password LIKE :password", Member.class)
                .setParameter("name", name)
                .setParameter("password", password).getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("SELECT m FROM Member as m WHERE m.username LIKE :name", Member.class)
                .setParameter("name", name).getResultList();
    }

    public List<TaskList> findTaskLists(Member member){
        return em.createQuery("SELECT l FROM TaskList as l WHERE l.listCreator.username LIKE :username", TaskList.class)
                .setParameter("username", member.getUsername()).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    public void persist(Member member) {this.em.persist(member);}
    public void detach(Member member) {this.em.detach(member);}

    public Member findOne(Integer id) {return em.find(Member.class, id);}

    public Member update(Member member) {return em.merge(member);}
}
