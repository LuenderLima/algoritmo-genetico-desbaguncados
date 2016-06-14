package br.ufpb.es.ag.exception;

public class ForcaTarefaExistenteException extends Exception {

	private static final long serialVersionUID = 1L;

	public ForcaTarefaExistenteException(String message){
		super(message);
	}
}
