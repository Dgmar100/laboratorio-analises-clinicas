package com.analise;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/analises")
public class AnaliseController {

    @Inject
    private AnaliseService analiseService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Analise> listar() {
        return analiseService.listarAnalises();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") int id) {

        Analise analise = analiseService.buscarPorId(id);

        if (analise == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(analise).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response criar(Analise analise) {

        if (analise == null || analise.getPaciente() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("É necessário indicar um paciente.")
                    .build();
        }

        try {
            analiseService.adicionarAnalise(analise);
            return Response.status(Response.Status.CREATED).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
