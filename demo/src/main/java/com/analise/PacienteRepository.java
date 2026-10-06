package com.analise;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class PacienteRepository {
    @PersistenceContext
    private EntityManager em;

    public void adicionarPaciente(Paciente paciente) {
        em.persist(paciente);
    }

    public List<Paciente> listarPacientes() {
        return em.createQuery("select p from Paciente p", Paciente.class).getResultList();

    }

    public Paciente buscarPorId(int id) {
       return em.find(Paciente.class, id);
    }
}
