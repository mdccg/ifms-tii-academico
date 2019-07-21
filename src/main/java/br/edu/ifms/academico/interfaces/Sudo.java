package br.edu.ifms.academico.interfaces;

import br.edu.ifms.academico.modelo.Professor;

public interface Sudo {
	public Professor sudoSu(String email, String senha);
}
