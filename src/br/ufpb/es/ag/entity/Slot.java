
package br.ufpb.es.ag.entity;

import br.ufpb.es.ag.entity.*;

public class Slot {

	private Disciplina tarefa;
	private Horario horario;
	private boolean apto;
	
	public Slot(Disciplina tarefa, Horario horario) {
		this.tarefa = tarefa;
		this.horario = horario;
	}
	
	public Disciplina getTarefa() {
		return tarefa;
	}
	
	public void setTarefa(Disciplina tarefa) {
		this.tarefa = tarefa;
	}
	
	public Horario getHorario() {
		return horario;
	}
	
	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public boolean isApto() {
		return apto;
	}

	public void setApto(boolean apto) {
		this.apto = apto;
	}
	
}
