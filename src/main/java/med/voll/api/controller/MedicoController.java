package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder){ //A anotação @Valid garante que os dados recebidos sejam validados de acordo com as restrições especificadas na classe DadosCadastroMedico
        var medico = new Medico(dados);
        repository.save(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri(); //o URI apontará para o endpoint para acessar os dados do médico recém-criado.
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico)); //Retorna o código de status 201 Created, o URI do médico recém-criado no cabeçalho "Location" e os detalhes do médico criado no corpo da resposta.
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page); //a lista paginada de DadosListagemMedico é encapsulada em um ResponseEntity, que é retornado com o código de status HTTP 200 OK, indicando que a operação foi bem-sucedida, e a lista de médicos é incluída no corpo da resposta.
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico)); //os detalhes atualizados do médico são encapsulados em um objeto DadosDetalhamentoMedico e retornados como uma resposta HTTP com o código de status 200 OK, indicando que a operação de atualização foi bem-sucedida.
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build(); //retorna uma resposta HTTP com o código de status 204 No Content para indicar que a operação foi concluída com sucesso, mas sem conteúdo no corpo da resposta.
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico)); //retorna uma resposta HTTP com o código de status 200 OK, contendo os detalhes do médico em um objeto DadosDetalhamentoMedico
    }

    //Exclusão definitiva
    //@DeleteMapping("/{id}")
    //@Transactional
    //public void excluir(@PathVariable Long id){
    //    repository.deleteById(id);
    //}

}
