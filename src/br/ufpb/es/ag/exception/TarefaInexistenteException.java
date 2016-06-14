package br.ufpb.es.ag.exception;

public class TarefaInexistenteException extends Exception {

	private static final long serialVersionUID = 1L;

	public TarefaInexistenteException(String message){
		super(message);
	}
}
