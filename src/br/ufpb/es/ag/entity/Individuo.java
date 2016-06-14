package br.ufpb.es.ag.entity;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.ufpb.es.ag.algorithm.Algoritmo;

public class Individuo {

	//Os genes do indiv�duo � uma lista bidimensional, que representa uma grade hor�ria composta por slots 	
	private List<List<Slot>> genes;
	private int aptidao = 0;   

    /**
     * Gera um indiv�duo aleat�rio
     */
    public Individuo(int numGenes) {  
    	this.genes = new ArrayList<List<Slot>>();
    	Random random = new Random();
    	List<Disciplina> tarefasARealizar = Algoritmo.getTarefas();
    	Horario horario;
    	
    	//Inicializa as listas que representam os dias da semana
    	for(int i = 0; i < Dados.getQuantidadeDias(); i++) {
    		this.genes.add(new ArrayList<Slot>());
    	} 
    	
    	//Aloca todas as tarefas de modo aleat�rio na grade hor�ria
    	for (int i = 0; i < Algoritmo.getQuantidadeTarefas(); i++) {
    		horario = geraHorario();
	        
	        //Adiciona o slot contendo a tarefa e seu respectivo hor�rio de realiza��o
	        this.genes.get(Dados.getIndiceDiaDaSemana(horario.getDiaDaSemana())).add(new Slot(tarefasARealizar.remove(random.nextInt(tarefasARealizar.size())), horario));
        }
        geraAptidao(); 
    	 
    }

	/**
     *  Cria um indiv�duo com os genes definidos
     */
    public Individuo(List<List<Slot>> genes) {   
    	Random random = new Random();
        this.genes = genes;
    
        //Se for mutar, inverte a posi��o entre dois genes
        if (random.nextDouble() <= Algoritmo.getTaxaDeMutacao()) {
        	
        	int indiceDia1 = random.nextInt(Dados.getQuantidadeDias());
        	//Loop enquanto o dia sorteado n�o possuir nenhum slot alocado
        	while(genes.get(indiceDia1).size() == 0){
        		indiceDia1 = random.nextInt(Dados.getQuantidadeDias());
        	}
        	int indiceDia2 = random.nextInt(Dados.getQuantidadeDias());
        	//Loop enquanto o dia sorteado n�o possuir nenhum slot alocado
        	while(genes.get(indiceDia2).size() == 0){
        		indiceDia2 = random.nextInt(Dados.getQuantidadeDias());
        	}
        	
        	int posicaoAleatoria1 = random.nextInt(genes.get(indiceDia1).size());
        	int posicaoAleatoria2 = random.nextInt(genes.get(indiceDia2).size());
        	
        	Slot slot1 = genes.get(indiceDia1).get(posicaoAleatoria1);
        	Slot slot2 = genes.get(indiceDia2).get(posicaoAleatoria2);
        	
        	//Substitui a posi��o de dois genes aleat�rios
        	genes.get(indiceDia1).set(posicaoAleatoria1, slot2);
        	genes.get(indiceDia2).set(posicaoAleatoria2, slot1);
        	
        }
        this.genes = genes;
        geraAptidao();
    }

    /**
     * Calcula o valor de aptid�o do indiv�duo
     */
    private void geraAptidao() {
    	this.aptidao = 0;
    	boolean acertou;
    	for(int i = 0; i < this.genes.size(); i++) {
    		for(Slot slot: this.genes.get(i)) {
    			acertou = false;
    			//Varre a lista de hor�rios dispon�veis do encarregado da tarefa
    			for(Horario horarioDisponivel: slot.getTarefa().getEncarregado().getHorariosDisponiveis()) {
    				if(horarioDisponivel.getDiaDaSemana().toUpperCase().equals(slot.getHorario().getDiaDaSemana().toUpperCase())
    					&&	horarioDisponivel.getHoraInicio().equals(slot.getHorario().getHoraInicio())) {
    					
    					slot.setApto(true);
    					acertou = true;
    					this.aptidao += 1;
    					
    				}
    			}
    			if(!acertou) {
    				slot.setApto(false);
    				this.aptidao -= 1;
    			}
    		}
    	}
       
    }
    
    /**
     * @return verdadeiro, se j� existir um slot alocado naquele determinado hor�rio. Falso, caso contr�rio
     */
    public boolean validarSlot(Integer indiceDia, String horaInicio) {
	    for(Slot slot: this.genes.get(indiceDia)) {
	    	if(slot.getHorario().getHoraInicio().equals(horaInicio)) {
	    		return true;
	    	}
	    }
    	return false;
    }
    
    /**
     * Gera um novo hor�rio, em uma posi��o ainda n�o ocupada na grade hor�ria, para um slot
     */
    public Horario geraHorario() {
    	Random random = new Random();
    	
    	Integer indiceDia;
    	Integer horaI;
    	Integer horaF;
    	String horaInicio;
    	String horaFim;
    	
    	do {
			indiceDia = random.nextInt(Dados.getQuantidadeDias());
			horaI = random.nextInt(Dados.getHorarioMaximo());
			horaF = horaI+1;
			
			horaInicio = horaI.toString();
			horaFim = horaF.toString();

		//Verifica se o hor�rio gerado n�o est� ocupado
    	} while (validarSlot(indiceDia, horaInicio));
    	
    	return new Horario(horaInicio, horaFim, Dados.getDiaDaSemana(indiceDia));
    }
    
    public int getAptidao() {
        return this.aptidao;
    }
    
    public List<List<Slot>> getGenes() {
        return this.genes;
    }
    
}