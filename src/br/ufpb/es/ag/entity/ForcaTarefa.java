package br.ufpb.es.ag.entity;

import java.util.LinkedList;
import java.util.List;

import br.ufpb.es.ag.exception.ForcaTarefaInexistenteException;
import br.ufpb.es.ag.exception.HorarioExistenteException;
import br.ufpb.es.ag.exception.HorarioInexistenteException;
import br.ufpb.es.ag.exception.TarefaExistenteException;
import br.ufpb.es.ag.exception.TarefaInexistenteException;
import br.ufpb.es.ag.exception.UsuarioExistenteException;
import br.ufpb.es.ag.exception.UsuarioInexistenteException;

public class ForcaTarefa {
	
	private List<Usuario> participantes;
	private List<Disciplina> tarefas;
	private Horario horarioDisponivel; // horário disponível a realização de tarefas
	private String nome;
	private String id;
	private String idAdministrador;
	
	public ForcaTarefa(String nome, String idAdministrador, Horario horarioDisponivel) throws UsuarioInexistenteException, ForcaTarefaInexistenteException{
		this.id = GeradorDeID.getInstance().gerarID();
		this.nome = nome;
		this.idAdministrador = idAdministrador;
		this.participantes = new LinkedList<Usuario>();
		this.tarefas = new LinkedList<Disciplina>();
		this.horarioDisponivel = horarioDisponivel;
		this.participantes.add(Gerenciador.getInstance().getUsuario(idAdministrador));
	}
	
	public String getId(){
		return this.id;
	}
	
	public void adicionarParticipante(Usuario usuario) throws UsuarioExistenteException{
		for(Usuario u: this.participantes){
			if (u.getId().equals(usuario.getId())){
				throw new UsuarioExistenteException ("Este usuário já existe");
					
			}
		}
		this.participantes.add(usuario);
	}
	
	public void setTarefas(List<Disciplina> tarefas) {
		this.tarefas = tarefas;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public List<Usuario> getListaParticipantes() {
		return participantes;
	}
	
	public void removerParticipante(String id) throws UsuarioInexistenteException{
		for(Usuario u: this.participantes){
			if (u.getId().equals(id)){
				this.participantes.remove(u);
			}
			
		}
		throw new UsuarioInexistenteException("Este Usuário não Existe");
	}

	public List<Disciplina> getTarefas() {
		return tarefas;
	}
	
	public Horario getHorarioDisponivel() {
		return horarioDisponivel;
	}

	public void cadastrarTarefa(Disciplina tarefa) throws TarefaExistenteException{
		for(Disciplina t: this.tarefas) {
			if(t.getId().equals(tarefa.getId())) {
				throw new TarefaExistenteException("A tarefa em questão já existe");
			}
		}
		this.tarefas.add(tarefa);
	}
	
	public Disciplina getTarefa(String nomeTarefa) throws TarefaInexistenteException{
		for(Disciplina t: this.tarefas){
			if(t.getNome().equals(nomeTarefa)){
				return t;
			}
		}
		throw new TarefaInexistenteException("A tarefa pesquisada não foi encontrada.");
	}

	public void cadastrarHorarioDisponivelDeParticipante(Horario horario, String idParticipante) throws HorarioExistenteException, UsuarioInexistenteException {
		this.getParticipante(idParticipante).cadastrarHorarioDisponivel(horario);
	}
	
	public void removerHorarioDisponivelDeParticipante(String idParticipante, String idHorarioDisponivel) throws UsuarioInexistenteException, HorarioInexistenteException {
		this.getParticipante(idParticipante).removerHorarioDisponivel(idHorarioDisponivel);

	}
	
	public Usuario getParticipante(String id) throws UsuarioInexistenteException{
		for(Usuario usuario: this.participantes) {
			if(usuario.getId().equals(id)) {
				return usuario;
			}
		}
		throw new UsuarioInexistenteException("O usuário pesquisado não existe.");
	}
	
	public void excluirTarefa(String nomeTarefa) throws TarefaInexistenteException{
		this.tarefas.remove(this.getTarefa(nomeTarefa));
	}

	public Usuario getAdministrador() throws UsuarioInexistenteException {
		return getParticipante(this.idAdministrador);
	}


}
