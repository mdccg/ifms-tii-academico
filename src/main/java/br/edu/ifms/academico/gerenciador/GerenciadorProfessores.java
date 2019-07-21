package br.edu.ifms.academico.gerenciador;

import static br.edu.ifms.academico.main.Main.CSS;
import static br.edu.ifms.academico.main.Main.HEADER;
import static br.edu.ifms.academico.main.Main.input;
import static br.edu.ifms.academico.main.Main.print;

import br.edu.ifms.academico.dao.ProfessorDAO;
import br.edu.ifms.academico.modelo.Professor;

public class GerenciadorProfessores {
	Professor professor;
	
	public void gerenciarProfessores() {
		ProfessorDAO professorDAO = new ProfessorDAO();
		
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
		
		professor = professorDAO.sudoSu(email, senha);
		
		if(professor == null) {
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
		
		String nome_completo = professor.getPrimeiro_nome() + " " + professor.getUltimo_nome();
		
		while(true) {
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
						+ ""
						+ ".lower-roman {"
							+ "list-style-type: lower-roman;"
						+ "}"
						+ ""
						+ ".italic {"
							+ "font-style: italic;"
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
					+ "<p>No Moodle, um professor tem responsabilidade pelos materiais de seu "
						+ "próprio curso. Eles geralmente também administram as inscrições e podem "
						+ "mudar o <span class=\"italic\">layout</span> da página do curso. Esta "
						+ "página apresenta os recursos do Moodle para aqueles com o Papel de "
						+ "professor.</p>"
					+ ""
					+ "<ol>"
						+ "<li>Dados Pessoais</li>"
						+ "<li>Notas a lançar</li>"
						+ "<li>Disciplinas</li>"
						+ "<li>Sair</li>"
					+ "</ol>"
					+ ""
					+ "<ol class=\"lower-roman\">"
						+ "<li>Matricular estudante</li>"
						+ "<li>Consultar estudante</li>"
						+ "<li>Lançar notas</li>"
						+ "<li>Desligar estudante</li>"
					+ "</ol>"
				+ "</body>"
				+ "</html>");
			
			switch(opcao) {
			case "1":
				listaDados();
				break;
			case "2":
				listaNotas();
				break;
			case "3":
				listaDisciplinas();
				break;
			case "4":
				return;
				
			case "i":
				matriculaEstudante();
				break;
			case "ii":
				consultaEstudante();
				break;
			case "iii":
				lancaNotas();
				break;
			case "iv":
				desligaEstudante();
				break;
			default:
				print("Opção inválida.");
			}
		}
	}
	
	public void listaDados() {
	}
	
	public void listaNotas() {
	}
	
	public void listaDisciplinas() {
	}
	
	
	public void matriculaEstudante() {
	}
	
	public void consultaEstudante() {
	}
	
	public void lancaNotas() {
	}
	
	public void desligaEstudante() {
	}
}
