package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

//A interface repository faz o acesso ao banco de dados integrando a entidade jpa com sua respectiva tabela no banco de dados
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);
}

/*
* A interface JpaRepository é uma interface genérica fornecida pelo Spring Data JPA que possui métodos pré-definidos para
* realizar operações de CRUD (Create, Read, Update, Delete) em um repositório de entidades.
*
* No caso específico dessa interface MedicoRepository, ela é usada para acessar e manipular dados relacionados à entidade
* Medico. O tipo Medico é especificado como o primeiro parâmetro genérico da interface JpaRepository, indicando que os métodos
* fornecidos por essa interface serão aplicados à entidade Medico. O segundo parâmetro genérico Long especifica o tipo do
* identificador único da entidade Medico.
*
* Essa interface MedicoRepository herda todos os métodos da interface JpaRepository, como save, findById, findAll, delete,
* entre outros. Esses métodos permitem realizar operações comuns de persistência de dados, como salvar um médico, buscar
* um médico por ID, buscar todos os médicos, excluir um médico, etc.
* */