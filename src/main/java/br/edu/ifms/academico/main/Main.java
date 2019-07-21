/*
DROP TABLE alunos, disciplinas, enderecos, matriculas, professores CASCADE;

TRUNCATE alunos, disciplinas, enderecos, matriculas, professores CASCADE;
INSERT INTO enderecos VALUES (1, 'Guanandi', '79200-000', 'Aquidauana' , 1337, 'Marechal Aquino');
INSERT INTO alunos VALUES (2, 'matheus.gomes@estudante.ifms.edu.br', 'Matheus', '1234567890', 'Gomes', 1);
INSERT INTO professores VALUES (3, 'vinicius.moraes@ifms.edu.br', 'Vinicius', '0987654321', 'Moraes', 1);
INSERT INTO disciplinas VALUES (4, 'Linguagem de Programação 3', 3);
INSERT INTO matriculas (id, nota1, aluno, disciplina) VALUES (5, 6.75, 2, 4);

*/

package br.edu.ifms.academico.main;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import br.edu.ifms.academico.gerenciador.GerenciadorAlunos;
import br.edu.ifms.academico.gerenciador.GerenciadorProfessores;
import br.edu.ifms.academico.utils.JPAUtil;

public class Main {
	public static void main(String[] args) {
		while(true) {
			String opcao = input(""
				+ "<html>"
				+ "<head>"
					+ CSS
				+ "</head>"
				+ "<body>"
					+ HEADER
					+ "<h3>Autenticação de acesso</h3>"
					+ ""
					+ "<p>Insira seus dados para acessar:</p>"
					+ ""
					+ "<p>Entrar como:</p>"
					+ ""
					+ "<ol>"
						+ "<li>Estudante</li>"
						+ "<li>Servidor</li>"
						+ "<li>Shutdown</li>"
					+ "</ol>"
				+ "</body>"
				+ "</html>");
			
			switch (String.valueOf(opcao)) {
			case "1":
				new GerenciadorAlunos().gerenciarAlunos();
				break;
			case "2":
				new GerenciadorProfessores().gerenciarProfessores();
				break;
			case "3":
				JPAUtil.closeEntityManagerFactory();
				return;
			default:
				print("Opção inválida.");
			}
		}
	}
	
	public static final String CSS = ""
		+ "<style>"
			+ "body {"
				+ "background-color: green;"
				+ "color: white;"
				+ "width: 512px;"
				+ "font: sans-serif;"
			+ "}"
			+ ""
			+ "h1, h2 {"
				+ "text-align: center;"
			+ "}"
			+ ""
			+ "h1, h2, h3, p {"
				+ "margin: 8px 4px 8px 4px;"
			+ "}"
		+ "</style>";
	
	public static final String HEADER = ""
		+ "<header>"
			+ "<h2>Instituto Federal de"
			+ "<br />"
			+ "Educação, Ciência e Tecnologia de"
			+ "<br />"
			+ "Mato Grosso do Sul</h2>"
			+ "<h1>Sistema Acadêmico</h1>"
			+ "<hr />"
		+ "</header>";
	
	public static final String TABELA_CSS = ""
		+ "<style>"
			+ "table, th, td {"
			+ "border-bottom: 1px solid white;"
			+ "width: 100%;"
			+ "}"
			+ ""
			+ "th, td {"
			+ "height: 32px;"
			+ "padding: 16px;"
			+ "text-align: left;"
			+ "}"
		+ "</style>";

	public static void print() {
		JOptionPane.showMessageDialog(null, "");
	}

	public static void print(String message) {
		JOptionPane.showMessageDialog(null, new JLabel(message));
	}

	public static String input() {
		return JOptionPane.showInputDialog("");
	}

	public static String input(Object message) {
		return JOptionPane.showInputDialog(message);
	}

	public static String input(String message) {
		return JOptionPane.showInputDialog(new JLabel(message));
	}

	public static String input(Object message, Object initialSelectionValue) {
		return JOptionPane.showInputDialog(message, initialSelectionValue);
	}

	public static String input(String message, Object initialSelectionValue) {
		return JOptionPane.showInputDialog(new JLabel(message), initialSelectionValue);
	}
}
