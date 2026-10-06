package com.analise;

import java.util.List;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/pacientes")
public class PacienteController {

    @Inject
    private PacienteService pacienteService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paciente> listar() {
        return pacienteService.listarPacientes();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") int id) {
    Paciente paciente = pacienteService.buscarPorId(id);

    if (paciente == null) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    return Response.ok(paciente).build();
}

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void criar(Paciente paciente) {
        pacienteService.adicionarPaciente(paciente);
    }
}