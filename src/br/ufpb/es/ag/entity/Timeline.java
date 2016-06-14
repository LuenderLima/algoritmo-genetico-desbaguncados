package br.ufpb.es.ag.entity;

import java.util.ArrayList;
import java.util.List;

public class Timeline {
	
	private String id;
	private List<ItemDeTimeline> itensDeTimeline;	

	public Timeline() {
		this.id = GeradorDeID.getInstance().gerarID();
		this.itensDeTimeline = new ArrayList<ItemDeTimeline>();
	}
	
	public void adicionarItem(ItemDeTimeline item) {
		this.itensDeTimeline.add(item);
	}
	
	public void excluirItem(ItemDeTimeline item) {
		this.itensDeTimeline.remove(item);
	}

	public List<ItemDeTimeline> getItensDeTimeline() {
		return itensDeTimeline;
	}

	public void setItensDeTimeline(List<ItemDeTimeline> itensDeTimeline) {
		this.itensDeTimeline = itensDeTimeline;
	}

	public String getId() {
		return id;
	}

}
