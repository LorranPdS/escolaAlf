package escola.api.alf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Aluno implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "geradorId_aluno")
	private Long id;

	private String nome;
	
	@OneToMany(mappedBy = "aluno", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Prova> provas = new ArrayList<Prova>();

	private Double notaFinal;

	private Boolean aprovado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Prova> getProvas() {
		return provas;
	}

	public void setProvas(List<Prova> provas) {
		this.provas = provas;
	}

	public Double getNotaFinal() {
		return notaFinal;
	}

	public void setNotaFinal(Double notaFinal) {
		this.notaFinal = notaFinal;
	}

	public void setAprovado(Boolean aprovado) {
		this.aprovado = aprovado;
	}

	public List<Double> setSomaNotas(List<Double> notasProvas) {

		double soma = 0.0;
		for (Double nota : notasProvas) {
			soma += nota;
		}

		double media = soma / notasProvas.size();
		this.setNotaFinal(media);
		return notasProvas;
	}

	public boolean getAprovado() {
		boolean resultadoFinal = this.getNotaFinal() > 7.0 ? true : false;
		this.setAprovado(resultadoFinal);
		return resultadoFinal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", provas=" + provas + ", notaFinal=" + notaFinal + ", aprovado="
				+ aprovado + "]";
	}

	public String toString2() {
		return "[" + provas + "]";
	}

}