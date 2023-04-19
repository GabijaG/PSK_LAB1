package lt.vu.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Task;
import lt.vu.persistence.TasksDAO;
import lt.vu.rest.contracts.TaskDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/tasks")
public class TasksController {
    @Inject
    @Getter @Setter
    private TasksDAO tasksDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Task task = tasksDAO.findOne(id);
        if (task == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        TaskDto taskDto = new TaskDto();
        taskDto.setName(task.getName());
        taskDto.setTaskListName(task.getList().getName());
        taskDto.setStatus(task.getStatus());

        return Response.ok(taskDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer taskId, TaskDto taskData) {
        try {
            Task existingTask = tasksDAO.findOne(taskId);
            if (existingTask == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingTask.setName(taskData.getName());
            tasksDAO.update(existingTask);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}