package br.ufpb.es.ag.entity;

public class ItemDeTimeline {
	
	private String id;
	private String descricao;

	public ItemDeTimeline(String descricao) {
		this.id = GeradorDeID.getInstance().gerarID();
		this.descricao = descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

}
