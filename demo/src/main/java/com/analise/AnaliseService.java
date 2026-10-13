package com.analise;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AnaliseService {

    @Inject
    private AnaliseRepository analiseRepository;

    @Inject
    private PacienteRepository pacienteRepository;

    @Transactional
    public void adicionarAnalise(Analise analise) {

        if (analise.getPaciente() == null) {
            throw new IllegalArgumentException(
                "É necessário indicar um paciente."
            );
        }

        int pacienteId = analise.getPaciente().getId();

        Paciente paciente = pacienteRepository.buscarPorId(pacienteId);

        if (paciente == null) {
            throw new IllegalArgumentException(
                "Paciente não encontrado."
            );
        }

        analise.setPaciente(paciente);

        if (analise.getEstado() == null ||
            analise.getEstado().isBlank()) {
            analise.setEstado("Pendente");
        }

        analiseRepository.adicionarAnalise(analise);
    }

    public List<Analise> listarAnalises() {
        return analiseRepository.listarAnalises();
    }

    public Analise buscarPorId(int id) {
        return analiseRepository.buscarPorId(id);
    }
}
