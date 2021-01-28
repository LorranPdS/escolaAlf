package escola.api.alf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import escola.api.alf.model.Aluno;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Long> {

	@Query("select a from Aluno a where a.aprovado = true")
	List<Aluno> findAllAprovados();
	
	@Query("select count (id) FROM Aluno a")
	Long findTotalRegistrados();
}