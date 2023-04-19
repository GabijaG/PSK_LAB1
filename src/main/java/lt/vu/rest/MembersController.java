package lt.vu.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Member;
import lt.vu.entities.Task;
import lt.vu.persistence.MembersDAO;
import lt.vu.rest.contracts.MemberDto;
import lt.vu.rest.contracts.TaskDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
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

//    @Path("/{id}")
//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Transactional
//    public Response update(
//            @PathParam("id") final Integer memberId, MemberDto memberData) {
//        try {
//            Member existingMember = membersDAO.findOne(memberId);
//            if (existingMember == null) {
//                return Response.status(Response.Status.NOT_FOUND).build();
//            }
//            existingMember.getTaskLists().add()
//            membersDAO.update(existingMember);
//            return Response.ok().build();
//        } catch (OptimisticLockException ole) {
//            return Response.status(Response.Status.CONFLICT).build();
//        }
//    }
}
