package br.ufpb.es.ag.entity;

public class Dados {
	
	private static final String[] DIA_DA_SEMANA = {"Segunda-Feira", "Terça-Feira", "Quarta-Feira", "Quinta-Feira", "Sexta-Feira", "Sábado", "Domingo"};
	private static final int HORARIO_MAXIMO = 24;
	private static final int QUANTIDADE_DIAS = 7;
	private final static double TAXA_DE_MUTACAO  = 0.3;
	private final static double TAXA_DE_CROSSOVER = 0.6;
	private final static int TAMANHO_MAXIMO_POPULACAO = 100;
	private final static int NUMERO_MAXIMO_GERACOES = 5000;
		

	public static int getHorarioMaximo() {
		return HORARIO_MAXIMO;
	}

	public static String getDiaDaSemana(int indiceDia) {
		return DIA_DA_SEMANA[indiceDia];
	}
	
	public static int getIndiceDiaDaSemana(String diaDaSemana) {
		for(int i = 0; i < DIA_DA_SEMANA.length; i++) {
			if(DIA_DA_SEMANA[i].toUpperCase().equals(diaDaSemana.toUpperCase())) {
				return i;
			}
		}
		return -1;
	}
	
    public static int getQuantidadeDias() {
		return QUANTIDADE_DIAS;
	}
    
	public static double getTaxaDeMutacao() {
		return TAXA_DE_MUTACAO;
	}

	public static double getTaxaDeCrossover() {
		return TAXA_DE_CROSSOVER;
	}
	
	public static int getTamanhoMaximoPopulacao() {
		return TAMANHO_MAXIMO_POPULACAO;
	}

	public static int getNumeroMaximoGeracoes() {
		return NUMERO_MAXIMO_GERACOES;
	}
	
}
