package com.analise;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AnaliseRepository {

    @PersistenceContext(unitName = "testPU")
    private EntityManager em;

    @Transactional
    public void adicionarAnalise(Analise analise) {
        em.persist(analise);
    }

    public List<Analise> listarAnalises() {
        return em.createQuery(
            "SELECT a FROM Analise a", Analise.class
        ).getResultList();
    }

    public Analise buscarPorId(int id) {
        return em.find(Analise.class, id);
    }
}