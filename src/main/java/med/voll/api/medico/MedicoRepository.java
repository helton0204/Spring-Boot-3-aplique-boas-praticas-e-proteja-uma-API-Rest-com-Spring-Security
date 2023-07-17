package med.voll.api.medico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
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