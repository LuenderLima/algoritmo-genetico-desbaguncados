package br.ufpb.es.ag.entity;

public class Horario {
	
	private String id;
	private String horaInicio;
	private String horaFim;
	private String diaDaSemana;
	
	public Horario(String horaInicio, String horaFim, String diaDaSemana) {
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.diaDaSemana = diaDaSemana;
	}
	
	public Horario(String horaInicio, String horaFim) {
		this.id = GeradorDeID.getInstance().gerarID();
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;

	}

	public String getId() {
		return id;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}

	public void setDiaDaSemana(String diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}
	
	public String getDiaDaSemana() {
		return diaDaSemana;
	}

}
