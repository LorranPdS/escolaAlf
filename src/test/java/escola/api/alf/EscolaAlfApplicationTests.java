package escola.api.alf;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EscolaAlfApplicationTests {

	@Test
	public void testandoMediaPonderada() {

		Scanner sc = new Scanner(System.in);

		char[] respostasCertas = new char[10];
		char[] respostasDoAluno = new char[10];

		double testeComFlutuante = 9.4234235235;
		Math.ceil(testeComFlutuante);

		System.out.println(testeComFlutuante);

		System.out.println("Gabarito da prova:");
		for (int i = 0; i < respostasCertas.length; i++) {
			respostasCertas[i] = sc.next().charAt(0);
		}

		System.out.println("Respostas do aluno");
		for (int i = 0; i < respostasDoAluno.length; i++) {
			respostasDoAluno[i] = sc.next().charAt(0);
		}

		int nota = 0;
		int peso = 0;
		for (int i = 0; i < respostasCertas.length; i++) {
			if (respostasDoAluno[i] == respostasCertas[i]) {
				nota = 0;
				if (i != 0) {
					peso++;
				} else {
					peso = 1;
				}
				nota += 1 * peso;
			}
		}

		if (nota == 0) {
			nota++;
		} else if (nota == 10) {
			nota--;
		}
		System.out.println(nota);
		sc.close();
	}

	@Test
	public void initTesteListas() {

		List<Character> opcao1 = new ArrayList<>();
		opcao1.add('a');

		List<Character> opcao2 = new ArrayList<>();
		opcao2.add('b');

		if (opcao1.get(0) == opcao2.get(0)) {
			System.out.println("São iguais");
		} else {
			System.out.println("São diferentes");
		}
	}

	@Test
	public void initNotaFinal() {

		List<Double> notas = new ArrayList<>();
		notas.add(8.45435435);
		notas.add(5.6543534);
		notas.add(9.75236354345);
		notas.add(5.55379845387);

		double soma = 0.0;
		for (Double nota : notas) {
			soma += nota;
		}

		double media = soma / notas.size();

		DecimalFormat formatador = new DecimalFormat("0.00");

		System.out.println(formatador.format(media));
		System.out.println(media);
	}
}
