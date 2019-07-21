package br.edu.ifms.academico.gerenciador;

import static br.edu.ifms.academico.main.Main.CSS;
import static br.edu.ifms.academico.main.Main.HEADER;
import static br.edu.ifms.academico.main.Main.TABELA_CSS;
import static br.edu.ifms.academico.main.Main.input;
import static br.edu.ifms.academico.main.Main.print;

import java.util.List;

import br.edu.ifms.academico.dao.AlunoDAO;
import br.edu.ifms.academico.modelo.Aluno;
import br.edu.ifms.academico.modelo.Disciplina;
import br.edu.ifms.academico.modelo.Endereco;
import br.edu.ifms.academico.modelo.Matricula;
import br.edu.ifms.academico.modelo.Professor;

public class GerenciadorAlunos {
	Aluno aluno;
	
	public void gerenciarAlunos() {
		AlunoDAO alunoDAO = new AlunoDAO();
		
		String email = input(""
			+ "<html>"
			+ "<head>"
				+ CSS
			+ "</head>"
			+ "<body>"
				+ HEADER
				+ "<h3>Endereço eletrônico:</h3>"
			+ "</body>"
			+ "</html>");
	
		String senha = input(""
			+ "<html>"
			+ "<head>"
				+ CSS
			+ "</head>"
			+ "<body>"
				+ HEADER
				+ "<h3>Senha:</h3>"
			+ "</body>"
			+ "</html>");
		
		aluno = alunoDAO.login(email, senha);
		
		if(aluno == null) {
			print(""
				+ "<html>"
				+ "<head>"
					+ CSS
					+ "<style>"
						+ "h3 {"
							+ "color: red;"
						+ "}"
					+ "</style>"
				+ "</head>"
				+ "<body>"
					+ HEADER
					+ "<h3>Endereço eletrônico ou senha inválido.</h3>"
				+ "</body>"
				+ "</html>");
			return;
		}
		
		String nome_completo = aluno.getPrimeiro_nome() + " " + aluno.getUltimo_nome();
		
		while (true) {
			String opcao = input(""
				+ "<html>"
				+ "<head>"
					+ CSS
					+ "<style>"
						+ "h3 {"
							+ "font-size: 16px;"
						+ "}"
						+ ""
						+ "p {"
							+ "text-align: justify;"
						+ "}"
					+ "</style>"
				+ "</head>"
				+ "<body>"
					+ HEADER
					+ "<h3>Olá, <b>" + nome_completo.toUpperCase() + "</b>!</h3>"
					+ ""
					+ "<p>A partir de agora você passará a ter acesso aos seus dados no Sistema "
					+ "Acadêmico do IFMS.</p>"
					+ ""
					+ "<p>Essa é a segunda de várias etapas que virão. A primeira foi o acesso "
						+ "ao boletim, agora, nesta fase você poderá consultar em tempo real quais "
						+ "as unidades que está matriculado no semestre, ver horários de aula e "
						+ "permanência, bem como acessar notas e faltas, além do boletim.</p>"
					+ ""
					+ "<ol>"
						+ "<li>Dados Pessoais</li>"
						+ "<li>Notas Lançadas</li>"
						+ "<li>Boletim Escolar</li>"
						+ "<li>Sair</li>"
					+ "</ol>"
				+ "</body>"
				+ "</html>");
			
			switch (String.valueOf(opcao)) {
			case "1":
				listaDados();
				break;
			case "2":
				listaNotasLancadas();
				break;
			case "3":
				listaNotas();
				break;
			case "4":
				return;
				
			default:
				print("Opção inválida.");
			}
		}
	}
	
	public void listaDados() {
		Endereco endereco = aluno.getEndereco();
		
		print(""
			+ "<html>"
			+ "<head>"
				+ CSS
				+ TABELA_CSS
			+ "</head>"
			+ "<body>"
				+ HEADER
				+ "<h3>Dados do estudante</h3>"
				+ ""
				+ "<table>"
					+ "<thead>"
						+ "<tr>"
							+ "<th>Nome completo</th>"
							+ "<th>E-mail</th>"
						+ "</tr>"
					+ "</thead>"
					+ "<tbody>"
						+ "<tr>"
							+ "<td>" + aluno.getPrimeiro_nome() + " " + aluno.getUltimo_nome()
								+ "</td>"
							+ "<td>" + aluno.getEmail() + "</td>"
						+ "</tr>"
					+ "</tbody>"
				+ "</table>"
				+ ""
				+ "<h3>Endereço</h3>"
				+ ""
				+ "<table>"
					+ "<thead>"
						+ "<tr>"
							+ "<th>Rua</th>"
							+ "<th>Número</th>"
							+ "<th>CEP</th>"
							+ "<th>Bairro</th>"
							+ "<th>Cidade</th>"
						+ "</tr>"
					+ "</thead>"
					+ "<tbody>"
						+ "<tr>"
							+ "<td>" + endereco.getRua() + "</td>"
							+ "<td>" + endereco.getNumero() + "</td>"
							+ "<td>" + endereco.getCep() + "</td>"
							+ "<td>" + endereco.getBairro() + "</td>"
							+ "<td>" + endereco.getCidade() + "</td>"
						+ "</tr>"
					+ "</tbody>"
				+ "</table>"
			+ "</body>"
			+ "</html>");
	}
	
	public void listaNotasLancadas() {
		List<Matricula> matriculas = aluno.getMatriculas();
		
		String table = new String();
		for(Matricula matricula : matriculas) {
			Disciplina disciplina = matricula.getDisciplina();
			
			Double nota1 = matricula.getNota1();
			Double nota2 = matricula.getNota2();
			
			Professor professor = disciplina.getProfessor();
			String nome_completo = professor.getPrimeiro_nome() + " " + professor.getUltimo_nome();
			
			boolean lancou = nota1 != null && nota2 != null;
			
			if(lancou) {
				table += ""
					+ "<tr>"
						+ "<td>" + disciplina.getNome() + "</td>"
						+ "<td>" + (nota1 == null ? "--" : nota1.toString()) + "</td>"
						+ "<td>" + (nota2 == null ? "--" : nota2.toString()) + "</td>"
						+ "<td>" + ((nota1 + nota2) / 2.0) + "</td>"
						+ "<td>" + nome_completo + "</td>"
						+ "<td>Em curso.</td>"
					+ "</tr>";
			}
		}
		
		String warning = new String();
		if(table.equals(""))
			warning = "<h3>Estudante sem notas lançadas!</h3>";
		
		print(""
			+ "<html>"
			+ "<head>"
				+ CSS
				+ TABELA_CSS
			+ "</head>"
			+ "<body>"
				+ HEADER
				+ "<h3>Notas lançadas</h3>"
				+ warning
				+ "<table>"
					+ "<thead>"
						+ "<tr>"
							+ "<th>Unidade curricular</th>"
							+ "<th>N1</th>"
							+ "<th>N2</th>"
							+ "<th>Média</th>"
							+ "<th>Docente</th>"
							+ "<th>Situação</th>"
						+ "</tr>"
					+ "</thead>"
					+ table
				+ "</table>"
			+ "</body>");
	}
	
	public void listaNotas() {
		List<Matricula> matriculas = aluno.getMatriculas();
		
		String table = new String();
		for(Matricula matricula : matriculas) {
			Disciplina disciplina = matricula.getDisciplina();
			
			Double nota1 = matricula.getNota1();
			Double nota2 = matricula.getNota2();
			
			boolean lancou = nota1 != null && nota2 != null;
			System.out.println(nota1);
			System.out.println(nota2);
			String situacao = new String();
			
			Double media = null;
			if(lancou) {
				media = (nota1 + nota2) / 2.0;
				situacao = (media >= 7.0) ? "Aprovado por nota." : "Reprovado por nota";
			} else
				situacao = "Em curso.";
			
			table += ""
				+ "<tr>"
					+ "<td>" + disciplina.getNome() + "</td>"
					+ "<td>" + (nota1 == null ? "--" : nota1) + "</td>"
					+ "<td>" + (nota2 == null ? "--" : nota2) + "</td>"
					+ "<td>" + (lancou ? media : "--") + "</td>"
					+ "<td>" + situacao + "</td>"
				+ "</tr>";
		}
		
		print(""
			+ "<html>"
			+ "<head>"
				+ CSS
				+ TABELA_CSS
			+ "</head>"
			+ "<body>"
				+ HEADER
				+ "<h3>Boletim escolar</h3>"
				+ "<table>"
					+ "<thead>"
						+ "<tr>"
							+ "<th>Nome</th>"
							+ "<th>N1</th>"
							+ "<th>N2</th>"
							+ "<th>Média</th>"
							+ "<th>Situação</th>"
						+ "</tr>"
					+ "</thead>"
					+ "<tbody>"
						+ table
					+ "</tbody>"
				+ "</table>"
			+ "</body>");
	}
}