package lt.vu.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Member;
import lt.vu.persistence.MembersDAO;
import lt.vu.rest.contracts.MemberDto;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/members")
public class MembersController {
    @Inject
    @Getter @Setter
    private MembersDAO membersDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Member member = membersDAO.findOne(id);
        if (member == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        MemberDto memberDto = new MemberDto();
        memberDto.setUsername(member.getUsername());

        return Response.ok(memberDto).build();
    }
}