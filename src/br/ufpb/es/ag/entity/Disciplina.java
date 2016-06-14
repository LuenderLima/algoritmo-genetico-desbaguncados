package br.ufpb.es.ag.entity;


public class Disciplina {
	
	private String nome;
	private String id;
	private int frequencia; // quantidade de vezes que será executada durante a semana
	private Usuario encarregado;  
	private String prioridade;  
	private boolean concluida;  
	
	public Disciplina(String nome){
		this.nome = nome;
		this.concluida = false;
		this.id = GeradorDeID.getInstance().gerarID();
	}

	public boolean isConcluida() {
		return concluida;
	}

	public void setConcluida(boolean concluida) {
		this.concluida = concluida;
	}

	public void setSituacao(boolean concluida) {
		this.concluida = concluida;
	}

	public void setEncarregado(Usuario encarregado){
		this.encarregado = encarregado;
	}
	
	public Usuario getEncarregado(){
		return encarregado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(int frequencia) {
		this.frequencia = frequencia;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public String getId() {
		return id;
	}
	
}
