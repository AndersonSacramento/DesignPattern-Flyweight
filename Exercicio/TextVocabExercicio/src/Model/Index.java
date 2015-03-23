package Model;


public class Index {


	public Index clone(){
		Index index = new Index();
		index.setPosicaoFrase(posicaoFrase);
		index.setPosicaoPalavra(posicaoPalavra);
		return index;
	}
	public void primeiraPalavra(){
		posicaoPalavra = 0;
	}
	public void primeiraFrase(){
		posicaoFrase = 0;
	}
	public void proxPalavra(){
		posicaoPalavra++;
	}
	public void proxFrase(){
		posicaoFrase++;
		posicaoPalavra = -1;
	}
	public void antPalavra(){
		posicaoPalavra--;
	}
	public void antFrase(){
		posicaoFrase--;
		posicaoPalavra = -1;
	}
	public Integer getPosicaoFrase() {
		return posicaoFrase;
	}
	public Integer getPosicaoPalavra() {
		return posicaoPalavra;
	}
	public void setPosicaoFrase(Integer posicaoFrase) {
		this.posicaoFrase = posicaoFrase;
	}
	public void setPosicaoPalavra(Integer posicaoPalavra) {
		this.posicaoPalavra = posicaoPalavra;
	}
	public String toString(){
		return posicaoFrase.toString() + posicaoPalavra.toString();
	}
	public void inicioPalavra(){
		posicaoPalavra = -1;
	}
	public void inicioFrase(){
		posicaoFrase = -1;
		posicaoPalavra = -1;
	}
	private Integer posicaoFrase = new Integer(0);
	private Integer posicaoPalavra = new Integer(0);
}
