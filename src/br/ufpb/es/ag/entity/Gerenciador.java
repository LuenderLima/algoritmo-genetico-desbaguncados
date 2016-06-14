package br.ufpb.es.ag.entity;

import java.util.LinkedList;
import java.util.List;

import br.ufpb.es.ag.exception.ForcaTarefaExistenteException;
import br.ufpb.es.ag.exception.ForcaTarefaInexistenteException;
import br.ufpb.es.ag.exception.HorarioExistenteException;
import br.ufpb.es.ag.exception.HorarioInexistenteException;
import br.ufpb.es.ag.exception.TarefaExistenteException;
import br.ufpb.es.ag.exception.TarefaInexistenteException;
import br.ufpb.es.ag.exception.UsuarioExistenteException;
import br.ufpb.es.ag.exception.UsuarioInexistenteException;

public class Gerenciador {
	
	private static Gerenciador singleton;
	private List<Usuario> usuarios; 
	private List<ForcaTarefa> forcasTarefa;
      	
	private Gerenciador(){
		this.usuarios = new LinkedList<Usuario>();
		this.forcasTarefa = new LinkedList<ForcaTarefa>();
	}
	 
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public List<ForcaTarefa> getForcasTarefa() {
		return forcasTarefa;
	}

	public static Gerenciador getInstance(){
		if (singleton == null){
			singleton = new Gerenciador();
		}
		return singleton;
	}
	
	public ForcaTarefa getForcaTarefa(String id) throws ForcaTarefaInexistenteException{
		for(ForcaTarefa f: this.forcasTarefa){
			if(f.getId().equals(id)){
				return f;
			}
		}
		throw new ForcaTarefaInexistenteException("A força-tarefa pesquisada não existe.");
	}
	
	public void sairDeForcaTarefa(String id, String idForcaTarefa) throws ForcaTarefaInexistenteException, UsuarioInexistenteException{
		for (ForcaTarefa f: this.forcasTarefa){
			if (f.getId().equals(idForcaTarefa)){
				f.removerParticipante(id);
			}
		} 
	}
	
	public void cadastrarForcaTarefa(String nome, String idAdministrador, String horaInicio, String horaFim) throws ForcaTarefaExistenteException, UsuarioInexistenteException, ForcaTarefaInexistenteException{
		for(ForcaTarefa f: this.forcasTarefa){
			if(f.getNome().equals(nome)){
				throw new ForcaTarefaExistenteException("O nome do grupo de força-tarefa já existe.");
			}
		}
		Horario horario = new Horario(horaInicio, horaFim);
		this.forcasTarefa.add(new ForcaTarefa(nome, idAdministrador, horario));
	}
	
	public void cadastrarTarefa(String idForcaTarefa, Disciplina tarefa) throws ForcaTarefaInexistenteException, TarefaExistenteException{
		this.getForcaTarefa(idForcaTarefa).cadastrarTarefa(tarefa);	
	}
	
	public void cadastrarHorarioDisponivel(Horario horario, String idForcaTarefa, String idParticipante) throws ForcaTarefaInexistenteException, HorarioExistenteException, UsuarioInexistenteException{
		this.getForcaTarefa(idForcaTarefa).cadastrarHorarioDisponivelDeParticipante(horario, idParticipante);
	}
	
	public void removerHorarioDisponivel(String idForcaTarefa, String idParticipante, String idHorarioDisponivel) throws ForcaTarefaInexistenteException, UsuarioInexistenteException, HorarioInexistenteException{
		this.getForcaTarefa(idForcaTarefa).getParticipante(idParticipante).removerHorarioDisponivel(idHorarioDisponivel);
	}
	
	public void excluirGrupoDeForcaTarefa(String id) throws ForcaTarefaInexistenteException{
		this.forcasTarefa.remove(this.getForcaTarefa(id));
	}
	
	public void excluirTarefa(String nomeForcaTarefa, String nomeTarefa) throws TarefaInexistenteException, ForcaTarefaInexistenteException{
		this.getForcaTarefa(nomeForcaTarefa).excluirTarefa(nomeTarefa);
	}
	
	public List<ForcaTarefa> getListaForcasTarefa(){
		return this.forcasTarefa;
	}
	
	public Usuario getUsuario(String idParticipante) throws UsuarioInexistenteException, ForcaTarefaInexistenteException {
		for(Usuario u: this.usuarios) {
			if(u.getId().equals(idParticipante)) {
				return u;
			}
		}
		throw new UsuarioInexistenteException("O usuário pesquisado não existe.");
	}
	
	public void cadastrarParticipanteDeForcaTarefa(String idForcaTarefa, String email) throws UsuarioExistenteException, ForcaTarefaInexistenteException, UsuarioInexistenteException {
		this.getForcaTarefa(idForcaTarefa).adicionarParticipante(this.getUsuarioPorEmail(email));
	}
	
	public Usuario getUsuarioPorEmail(String email) throws UsuarioInexistenteException {
		for(Usuario u: this.usuarios) {
			if(u.getEmail().equals(email)) {
				return u;
			}
		}
		throw new UsuarioInexistenteException("O usuário pesquisado não existe.");
	}
	
	public void cadastrarUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}
	
	public void removerUsuario(String email) throws UsuarioInexistenteException {
		this.usuarios.remove(this.getUsuarioPorEmail(email));
	}
	
}
