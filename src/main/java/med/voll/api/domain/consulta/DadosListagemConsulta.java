package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;

import java.time.LocalDateTime;

public record DadosListagemConsulta(Long id, String nomeMedico, Especialidade especialidadeMedico, String nomePaciente, LocalDateTime data) {
    public DadosListagemConsulta(Consulta consulta){
        this(consulta.getId(), consulta.getMedico().getNome(), consulta.getMedico().getEspecialidade(), consulta.getPaciente().getNome(), consulta.getData());
    }
}

