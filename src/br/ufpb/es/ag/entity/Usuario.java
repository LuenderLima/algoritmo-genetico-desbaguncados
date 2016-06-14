package br.ufpb.es.ag.entity;

import java.util.LinkedList;
import java.util.List;

import br.ufpb.es.ag.exception.HorarioExistenteException;
import br.ufpb.es.ag.exception.HorarioInexistenteException;

public class Usuario {
	
	private String nome;
	private String id;
	private String senha;
	private String email;
	private List<Horario> horariosDisponiveis;
	
	public Usuario(){
		this.horariosDisponiveis = new LinkedList<Horario>();
		this.id = GeradorDeID.getInstance().gerarID();
	}
	
	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Horario> getHorariosDisponiveis() {
		return horariosDisponiveis;
	}

	public void setHorariosDisponivel(List<Horario> horariosViaveis) {
		this.horariosDisponiveis = horariosViaveis;
	}
	
	public void cadastrarHorarioDisponivel(Horario horario) throws HorarioExistenteException {
		this.horariosDisponiveis.add(horario);
	}

	public void removerHorarioDisponivel(String idHorarioDisponivel) throws HorarioInexistenteException {
		boolean removeu = false;
		for(Horario h: this.horariosDisponiveis) {
			if(h.getId().equals(idHorarioDisponivel)) {
				this.horariosDisponiveis.remove(h);
				removeu = true;
			}
		}
		if(!removeu) {
			throw new HorarioInexistenteException("O horário disponível não existe.");
		}
		
	}
		

}
