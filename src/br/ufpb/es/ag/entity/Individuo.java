package br.ufpb.es.ag.entity;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.ufpb.es.ag.algorithm.Algoritmo;

public class Individuo {

	//Os genes do indivï¿½duo ï¿½ uma lista bidimensional, que representa uma grade horï¿½ria composta por slots 	
	private List<List<Slot>> genes;
	private int aptidao = 0;   

    /**
     * Gera um indivï¿½duo aleatï¿½rio
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
    	
    	//Aloca todas as tarefas de modo aleatório na grade horária
    	for (int i = 0; i < Algoritmo.getQuantidadeTarefas(); i++) {
    		horario = geraHorario();
	        
	        //Adiciona o slot contendo a tarefa e seu respectivo horário de realização
	        this.genes.get(Dados.getIndiceDiaDaSemana(horario.getDiaDaSemana())).add(new Slot(tarefasARealizar.remove(random.nextInt(tarefasARealizar.size())), horario));
        }
        geraAptidao(); 
    	 
    }

	/**
     *  Cria um indivï¿½duo com os genes definidos
     */
    public Individuo(List<List<Slot>> genes) {   
    	Random random = new Random();
        this.genes = genes;
    
        //Se for mutar, inverte a posição entre dois genes
        if (random.nextDouble() <= Algoritmo.getTaxaDeMutacao()) {
        	
        	int indiceDia1 = random.nextInt(Dados.getQuantidadeDias());
        	//Loop enquanto o dia sorteado não possuir nenhum slot alocado
        	while(genes.get(indiceDia1).size() == 0){
        		indiceDia1 = random.nextInt(Dados.getQuantidadeDias());
        	}
        	int indiceDia2 = random.nextInt(Dados.getQuantidadeDias());
        	//Loop enquanto o dia sorteado não possuir nenhum slot alocado
        	while(genes.get(indiceDia2).size() == 0){
        		indiceDia2 = random.nextInt(Dados.getQuantidadeDias());
        	}
        	
        	int posicaoAleatoria1 = random.nextInt(genes.get(indiceDia1).size());
        	int posicaoAleatoria2 = random.nextInt(genes.get(indiceDia2).size());
        	
        	Slot slot1 = genes.get(indiceDia1).get(posicaoAleatoria1);
        	Slot slot2 = genes.get(indiceDia2).get(posicaoAleatoria2);
        	
        	//Substitui a posição de dois genes aleatórios
        	genes.get(indiceDia1).set(posicaoAleatoria1, slot2);
        	genes.get(indiceDia2).set(posicaoAleatoria2, slot1);
        	
        }
        this.genes = genes;
        geraAptidao();
    }

    /**
     * Calcula o valor de aptidão do indivíduo
     */
    private void geraAptidao() {
    	this.aptidao = 0;
    	boolean acertou;
    	for(int i = 0; i < this.genes.size(); i++) {
    		for(Slot slot: this.genes.get(i)) {
    			acertou = false;
    			//Varre a lista de horários disponíveis do encarregado da tarefa
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
     * @return verdadeiro, se já existir um slot alocado naquele determinado horário. Falso, caso contrário
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
     * Gera um novo horário, em uma posição ainda não ocupada na grade horária, para um slot
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

		//Verifica se o horário gerado não está ocupado
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