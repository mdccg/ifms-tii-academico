package br.edu.ifms.academico.gerenciador;

import static br.edu.ifms.academico.main.Main.CSS;
import static br.edu.ifms.academico.main.Main.HEADER;
import static br.edu.ifms.academico.main.Main.input;
import static br.edu.ifms.academico.main.Main.print;

import br.edu.ifms.academico.dao.AlunoDAO;
import br.edu.ifms.academico.modelo.Aluno;

public class GerenciadorAlunos {
	public static void gerenciarAlunos() {
		AlunoDAO alunoDAO = new AlunoDAO();
		
		String email = input("<html>"
				+ "<head>"
					+ CSS
				+ "</head>"
				+ "<body>"
					+ HEADER
					+ "<h3>Endereço eletrônico:</h3>"
				+ "</body>"
				+ "</html>");
		
		String senha = input("<html>"
				+ "<head>"
					+ CSS
				+ "</head>"
				+ "<body>"
					+ HEADER
					+ "<h3>Senha:</h3>"
				+ "</body>"
				+ "</html>");
		
		Aluno aluno = alunoDAO.login(email, senha);
		
		if(aluno == null) {
			print("<html>"
					+ "<head>"
						+ CSS
						+ "<style>"
							+ "h3 {"
								+ "color: red;"
								+ "text-shadow: 2px 2px 2px white;"
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
			String opcao = input("<html>"
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
							+ "<li>Unidades Curriculares em Curso</li>"
							+ "<li>Notas e Frequência</li>"
							+ "<li>Sair</li>"
						+ "</ol>"
					+ "</body>"
					+ "</html>");
			
			switch (String.valueOf(opcao)) {
			case "1":
				listaDados(aluno);
				break;
				
			case "2":
				listaDisciplinas();
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
	
	public static void listaDados(Aluno aluno) {
		// Endereco endereco = new EnderecoDAO().buscaPorUsuario(aluno);
		
		print("<html>"
				+ "<head>"
					+ CSS
					+ "<style>"
						+ "table, th, td {"
							+ "border-bottom: 1px solid white;"
							+ "width: fit-content;"
						+ "}"
						+ ""
						+ "th, td {"
							+ "height: 32px;"
							+ "padding: 16px;"
							+ "text-align: left;"
						+ "}"
					+ "</style>"
				+ "</head>"
				+ "<body>"
					+ HEADER
					+ "<p>Dados do estudante</p>"
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
								+ "<td>" + aluno.getPrimeiro_nome() + " "
										+ aluno.getUltimo_nome() + "</td>"
								+ "<td>" + aluno.getEmail() + "</td>"
							+ "</tr>"
						+ "</tbody>"
					+ "</table>"
					+ ""/*
					+ "<p>Endereço</p>"
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
					+ "</table>"*/
				+ "</body>"
				+ "</html>");
	}
	
	public static void listaDisciplinas() {
		
	}
	
	public static void listaNotas() {
		
	}
}
