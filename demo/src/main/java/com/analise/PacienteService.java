package com.analise;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped

public class PacienteService {
    @Inject
    private PacienteRepository pacienteRepository;

    @Transactional 
    public void adicionarPaciente(Paciente paciente) {
        pacienteRepository.adicionarPaciente(paciente);
    }

    public List<Paciente> listarPacientes() {
        return pacienteRepository.listarPacientes();
    }
public Paciente buscarPorId(int id) {
    return pacienteRepository.buscarPorId(id);
}

}
