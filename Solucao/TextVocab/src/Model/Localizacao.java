package Model;

public class Localizacao {
	public Localizacao(Integer inicio, Integer fim){
		this.inicio = inicio;
		this.fim = fim;
	}
	public Localizacao(int inicio, int fim){
		this.inicio = new Integer(inicio);
		this.fim = new Integer(fim);
	}
	
	public Integer getInicio() {
		return inicio;
	}
	public Integer getFim() {
		return fim;
	}

	private final Integer inicio;
	private final Integer fim;
}
