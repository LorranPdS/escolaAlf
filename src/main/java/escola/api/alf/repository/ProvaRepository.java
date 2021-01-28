package escola.api.alf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import escola.api.alf.model.Prova;

@Repository
public interface ProvaRepository extends CrudRepository<Prova, Long> {
	
	@Query("select respostas from Prova p where aluno_id = ?1")
	List<String> findByRespostas(Long id);
}