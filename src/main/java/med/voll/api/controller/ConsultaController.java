package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendaDeConsultas;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosListagemConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key") //essa anotação faz parte da configuração do springdoc para receber token jwt para autorizar uma requisição http
public class ConsultaController {

    @Autowired //essa anotação faz o spring instanciar o objeto agenda ocorrendo a injeção de dependências
    private AgendaDeConsultas agenda;

    @Autowired
    private ConsultaRepository consultaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var dto = agenda.agendar(dados); //as regras de negócios não são feitas na classe controller, ela apenas chama essas regras através de objetos
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<Page<DadosListagemConsulta>> listarConsultas(
            @PathVariable Long idPaciente, @PageableDefault(size = 10, sort = {"data"}) Pageable paginacao
        ){
            var page = consultaRepository.findAllByPacienteId(idPaciente, paginacao).map(DadosListagemConsulta::new);
            return ResponseEntity.ok(page);
    }

}