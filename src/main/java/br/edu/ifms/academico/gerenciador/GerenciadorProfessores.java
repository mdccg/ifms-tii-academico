package br.edu.ifms.academico.gerenciador;

import static br.edu.ifms.academico.main.Main.CSS;
import static br.edu.ifms.academico.main.Main.GERENCIADOR_CSS;
import static br.edu.ifms.academico.main.Main.HEADER;
import static br.edu.ifms.academico.main.Main.TABELA_CSS;
import static br.edu.ifms.academico.main.Main.input;
import static br.edu.ifms.academico.main.Main.inputHTML;
import static br.edu.ifms.academico.main.Main.print;
import static br.edu.ifms.academico.main.Main.printHTML;

import java.util.HashMap;
import java.util.List;

import br.edu.ifms.academico.dao.AlunoDAO;
import br.edu.ifms.academico.dao.DAO;
import br.edu.ifms.academico.dao.EnderecoDAO;
import br.edu.ifms.academico.dao.ProfessorDAO;
import br.edu.ifms.academico.modelo.Aluno;
import br.edu.ifms.academico.modelo.Disciplina;
import br.edu.ifms.academico.modelo.Endereco;
import br.edu.ifms.academico.modelo.Matricula;
import br.edu.ifms.academico.modelo.Professor;

public class GerenciadorProfessores {
	Professor professor;
	
	public void gerenciarProfessores() {
		ProfessorDAO professorDAO = new ProfessorDAO();
		
		String email = inputHTML("Endereço eletrônico do professor:");
	
		String senha = inputHTML("Senha do professor:");
		
		professor = professorDAO.sudoSu(email, senha);
		
		if(professor == null) {
			printHTML("Endereço eletrônico ou senha inválido.");
			return;
		}
		
		String nome_completo = professor.getPrimeiro_nome() + " " + professor.getUltimo_nome();
		
		while(true) {
			String opcao = input(""
				+ "<html>"
				+ "<head>"
					+ CSS
					+ GERENCIADOR_CSS
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
					+ "</ol>"
				+ "</body>"
				+ "</html>");
			
			switch(opcao.toLowerCase()) {
			case "1":
				listaDados();
				break;
			case "2":
				listaNotasPendentes();
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
			default:
				print("Opção inválida.");
			}
		}
	}
	
	public void listaDados() {
		Endereco endereco = professor.getEndereco();
		
		print(""
			+ "<html>"
			+ "<head>"
				+ CSS
				+ TABELA_CSS
			+ "</head>"
			+ "<body>"
				+ HEADER
				+ "<h3>Dados do professor</h3>"
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
							+ "<td>" + professor.getPrimeiro_nome() + " " + professor.getUltimo_nome()
								+ "</td>"
							+ "<td>" + professor.getEmail() + "</td>"
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
	
	public void listaNotasPendentes() {
		List<Disciplina> disciplinas = professor.getDisciplinas();
		
		String tables = new String();
		for(Disciplina disciplina : disciplinas) {
			List<Matricula> matriculas = disciplina.getMatriculas();
			
			tables += ""
				+ "<hr />"
				+ "<h3>" + disciplina.getNome() + "</h3>"
				+ "<table>"
					+ "<thead>"
						+ "<tr>"
							+ "<th>Estudante</th>"
							+ "<th>N1</th>"
							+ "<th>N2</th>"
						+ "</tr>"
					+ "</thead>"
					+ "<tbody>";
			
			for(Matricula matricula : matriculas) {
				Double nota1 = matricula.getNota1();
				Double nota2 = matricula.getNota2();
				
				boolean lancou = nota1 != null && nota2 != null;
				
				if(lancou)
					continue;
				
				Aluno aluno = matricula.getAluno();
				
				tables += ""
					+ "<tr>"
						+ "<td>" + aluno.getPrimeiro_nome() + " " + aluno.getUltimo_nome() +
							"</td>"
						+ "<td>" + (nota1 == null ? "--" : nota1) + "</td>"
						+ "<td>" + (nota2 == null ? "--" : nota2) + "</td>"
					+ "</tr>";
			}
			
			tables += ""
					+ "</tbody>"
				+ "</table>";
		}
		
		print(""
			+ "<html>"
			+ "<head>"
				+ CSS
				+ TABELA_CSS
			+ "</head>"
			+ "<body>"
				+ HEADER
				+ tables
			+ "</body>"
			+ "</html>");
	}
	
	public void listaDisciplinas() {
		List<Disciplina> disciplinas = professor.getDisciplinas();
		String ul = "<ul>";
		
		for(Disciplina disciplina : disciplinas)
			ul += "<li>" + disciplina.getNome() + "</li>";
		
		ul += "</ul>";
		
		print(""
			+ "<html>"
			+ "<head>"
				+ CSS
			+ "</head>"
			+ "<body>"
				+ HEADER
				+ ul
			+ "</body>"
			+ "</html>");
	}
	
	public void matriculaEstudante() {
		String primeiro_nome = inputHTML("Primeiro nome do estudante:");
		String ultimo_nome = inputHTML("Último nome do estudante:");
		String email = this.validaEmail(primeiro_nome, ultimo_nome);
		String senha = "mudar123";
		
		String rua = inputHTML("Rua do estudante:");
		String numero = inputHTML("Número do endereço do estudante:");
		String cep = inputHTML("CEP do estudante:");
		String bairro = inputHTML("Bairro do estudante:");
		String cidade = inputHTML("Cidade do estudante:");
		
		Endereco endereco = new Endereco();
		endereco.setBairro(bairro);
		endereco.setCep(cep);
		endereco.setCidade(cidade);
		endereco.setNumero(numero);
		endereco.setRua(rua);
		
		Endereco endereco1 = new EnderecoDAO().verificaExistente(endereco);
		
		if(endereco1 != null)
			endereco = endereco1;
		else
			new DAO<Endereco>().adiciona(endereco);
		
		Aluno aluno = new Aluno();
		aluno.setEmail(email);
		aluno.setPrimeiro_nome(primeiro_nome);
		aluno.setSenha(senha);
		aluno.setUltimo_nome(ultimo_nome);
		
		new DAO<Aluno>().adiciona(aluno);
		
		aluno = new AlunoDAO().buscaPorEmail(email);
		aluno.setEndereco(endereco);
		
		new DAO<Aluno>().atualiza(aluno);
		
		printHTML("Estudante cadastrado com êxito.");
	}
	
	public void consultaEstudante() {
		String email = inputHTML("Endereço eletrônico do estudante:");
		
		Aluno aluno = new AlunoDAO().buscaPorEmail(email);
		
		if(aluno == null)
			printHTML("Nenhum estudante foi encontrado.");
		else
			new GerenciadorAlunos().listaDados(aluno);
	}
	
	public void lancaNotas() {
		List<Disciplina> disciplinas = professor.getDisciplinas();
		HashMap<Integer, Disciplina> hashMap = new HashMap<>();
		
		String ol = "<ol>";
		int i = 0;
		
		for(Disciplina disciplina : disciplinas) {
			ol += "<li>" + disciplina.getNome() + "</li>";
			hashMap.put(++i, disciplina);
		}
		
		ol += "</ol>";
		
		Disciplina escolhida = null;
		boolean valido = false;
		do {
			try {
				Integer opcao = Integer.parseInt(input(""
					+ "<html>"
					+ "<head>"
						+ CSS
					+ "</head>"
					+ "<body>"
						+ HEADER
						+ ol
						+ "<h3>Digite o número da disciplina:</h3>"
					+ "</body>"
					+ "</html>"));
				if(hashMap.get(opcao) == null)
					valido = false;
				else {
					escolhida = hashMap.get(opcao);
					valido = true;
				}
				
			} catch(Exception exception) {
				printHTML("Entrada inválida (apenas números).");
			}
		} while(!valido);
		
		List<Matricula> matriculas = escolhida.getMatriculas();
		HashMap<Integer, Matricula> hashMap1 = new HashMap<>();
		
		ol = "<ol>";
		
		i = 0;
		for(Matricula matricula : matriculas) {
			Aluno aluno = matricula.getAluno();
			String nome_completo = aluno.getPrimeiro_nome() + " " + aluno.getUltimo_nome();
			
			ol += "<li>" + nome_completo + "</li>";
			hashMap1.put(++i, matricula);
		}
		
		ol += "</ol>";
		
		Matricula escolhida1 = null;
		valido = false;
		do {
			try {
				Integer opcao = Integer.parseInt(input(""
					+ "<html>"
					+ "<head>"
						+ CSS
					+ "</head>"
					+ "<body>"
						+ HEADER
						+ ol
						+ "<h3>Digite o número do estudante:</h3>"
					+ "</body>"
					+ "</html>"));
				if(hashMap.get(opcao) == null)
					valido = false;
				else {
					escolhida1 = hashMap1.get(opcao);
					valido = true;
				}
				
			} catch(Exception exception) {
				printHTML("Entrada inválida (apenas números).");
			}
		} while(!valido);
		
		Double nota1 = Double.valueOf(inputHTML("N1 do estudante:"));
		Double nota2 = Double.valueOf(inputHTML("N2 do estudante:"));
		escolhida1.setNota1(nota1);
		escolhida1.setNota2(nota2);
		
		new DAO<Matricula>().atualiza(escolhida1);
		
		printHTML("Notas lançadas com êxito.");
		
	}
	
	public String validaEmail(String primeiro_nome, String ultimo_nome) {
		String ultimo_nome1 = ultimo_nome;
		boolean valido = false;
		String email = null;

		do {
			int i = 1;
			email = primeiro_nome.toLowerCase() + "." + ultimo_nome1.toLowerCase() + "@estudante.ifms.edu.br";

			Aluno aluno = new AlunoDAO().buscaPorEmail(email);

			if (aluno == null)
				valido = true;
			else {
				ultimo_nome1 = ultimo_nome;
				ultimo_nome1 += ++i;
			}
		} while (!valido);

		if (ultimo_nome != null)
			return email = primeiro_nome.toLowerCase() + "." + ultimo_nome1.toLowerCase() + "@estudante.ifms.edu.br";
		return email;
	}
}
