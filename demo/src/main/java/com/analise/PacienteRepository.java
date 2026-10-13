package com.analise;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PacienteRepository {

    @PersistenceContext(unitName = "testPU")
    private EntityManager entityManager;

    @Transactional
    public void adicionarPaciente(Paciente paciente) {
        entityManager.persist(paciente);
    }

    public List<Paciente> listarPacientes() {
        return entityManager
                .createQuery("SELECT p FROM Paciente p", Paciente.class)
                .getResultList();
    }

    public Paciente buscarPorId(int id) {
        return entityManager.find(Paciente.class, id);
    }
}