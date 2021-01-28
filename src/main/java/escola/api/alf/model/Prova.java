package escola.api.alf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Prova implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "geradorId_prova")
	private Long id;

	private Character[] respostas;

	@SuppressWarnings("deprecation")
	@JsonIgnore
	@org.hibernate.annotations.ForeignKey(name = "aluno_id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Aluno aluno;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Character[] getRespostas() {
		return respostas;
	}

	public void setRespostas(Character[] respostas) {
		this.respostas = respostas;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Double> getNotaProva(List<String> respostasAluno, List<String> gabarito) {

		List<Double> notasProvas = new ArrayList<>();

		String[] resposAluno = new String[10];
		String[] resposGabarito = new String[10];

		// Percorrendo a lista
		int cont = 0;
		for (String respAluno : respostasAluno) {
			String[] camposAluno = respAluno.split(",");
			String[] camposGabarito = gabarito.get(cont).split(",");

			double notaTemp = 0.0;
			int peso = 0;

			// Inserindo os caracteres das respostas do aluno e do gabarito nos vetores
			for (int i = 0; i < resposAluno.length; i++) {

				resposAluno[i] = camposAluno[i];
				resposGabarito[i] = camposGabarito[i];

				// Comparando os caracteres através dos vetores
				if (resposAluno[i].equalsIgnoreCase(resposGabarito[i])) {
					peso++;
					notaTemp++;
				}
			}
			notaTemp *= peso / (peso);

			// Garantindo que a nota da prova seja sempre maior que 0 e menor que 10
			if (notaTemp == 0.0) {
				notaTemp = 0.1;
			} else if (notaTemp >= 10.0) {
				notaTemp = 9.9;
			}

			notasProvas.add(notaTemp);
			cont++;
		}

		return notasProvas;
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
		Prova other = (Prova) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Prova [id=" + id + ", resposta=" + Arrays.toString(respostas) + ", aluno=" + aluno + "]";
	}

	public String toString2() {
		StringBuilder sb = new StringBuilder();
		for (Character resp : respostas) {
			sb.append(resp + ",");
		}
		sb.append(" ");
		return sb.toString();
	}

}