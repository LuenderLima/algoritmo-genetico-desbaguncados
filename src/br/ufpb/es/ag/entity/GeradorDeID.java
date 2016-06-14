package br.ufpb.es.ag.entity;

public class GeradorDeID {
	
	private Integer contador = 0;
	private static GeradorDeID singleton;
	
	public static GeradorDeID getInstance(){
		if (singleton == null){
			return singleton = new GeradorDeID();
		}
		return singleton;
	}
	
	public String gerarID(){
			contador++;
			return contador.toString();
	}

}
