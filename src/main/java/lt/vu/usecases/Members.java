package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Member;
import lt.vu.persistence.MembersDAO;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Model
public class Members implements Serializable {

    @Inject
    private MembersDAO membersDAO;

    @Getter @Setter
    private Member memberToCreate = new Member();

    @Getter @Setter
    private List<Member> allMembers;

    @PostConstruct
    public void init() {
        loadAllMembers();
    }

    @Transactional
    public void createMember(){
        if(membersDAO.findByName(memberToCreate.getUsername()).isEmpty()) {
            this.membersDAO.persist(memberToCreate);
        }
    }

    public Member getMember()
    {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer memberId = Integer.parseInt(requestParameters.get("memberId"));
        return membersDAO.findOne(memberId);
    }

    private void loadAllMembers(){
        this.allMembers = membersDAO.loadAll();
    }
}