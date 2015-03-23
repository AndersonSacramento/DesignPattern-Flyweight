package Model;

import java.io.IOException;

public class GerenciadorTextual {

	public void carregarTexto(String nomeTexto) throws IOException {
		this.contexto = new Contexto();
		this.texto = new Texto(nomeTexto, this.contexto);
		this.arquivo = nomeTexto;
		

	}
	
	public String textoOriginal() throws IOException{
		return ProcessadorTexto.conteudo(this.arquivo);
	}
	
	public String visualizarTextoDestaque() {
		return texto.visualizarComDestaque(contexto);
		
	}
	public String visualizarPalavraDestaque(){
		Frase frase = texto.getFrases().get(contexto.getPosicaoFrase());
		Palavra palavra = frase.getPalavras().get(contexto.getPosicaoPalavra());
		return palavra.visualizarComDestaque(contexto);
	}
	public String visualizarFraseDestaque(){
		Frase frase = texto.getFrases().get(contexto.getPosicaoFrase());	
		return frase.visualizarComDestaque(contexto);
	}
	public boolean editarClasseGramatical(byte tipo) {
		Frase frase = texto.getFrases().get(contexto.getPosicaoFrase());
		Palavra palavra = frase.getPalavras().get(contexto.getPosicaoPalavra());
		palavra.setClasseGramatical(new ClasseGramatical(tipo), contexto);
		
		return true;
	}
	public boolean  editarDestaque(byte tipo) {
		Frase frase = texto.getFrases().get(contexto.getPosicaoFrase());
		Palavra palavra = frase.getPalavras().get(contexto.getPosicaoPalavra());
		palavra.setDestaque(palavra.getDestaque(contexto).setDestaque(tipo) , contexto);
		frase.atualizarDestaque(contexto);
		texto.atualizarDestaque(contexto);
		return true;
	}

	public int getNumeroTotalPalavras() {
		return texto.getNumeroPalavras();
	}

	public int getNumeroTotalFrases() {
		return texto.getNumeroFrases();
	}

	public int getNumeroPalavrasFrase() {
		Frase frase = texto.getFrases().get(contexto.getPosicaoFrase());
		return frase.getNumeroPalavras();
	}
	public int getNumeroObjetosPalavra(){
		return texto.getNumeroObjetosPalavra();
	}
	public ClasseGramatical getClasseGramatical() {
		return contexto.getClasseGramatical();
	}

	public Destaque getDestaque() {
		return contexto.getDestaque();
	}
	public void proxPalavra() {
		Frase frase = texto.getFrases().get(contexto.getPosicaoFrase());
		if(frase.getNumeroPalavras()-1 > contexto.getPosicaoPalavra()){
			contexto.proxPalavra();
		}
	}

	public void proxFrase() {
		if(texto.getNumeroFrases()-1 > contexto.getPosicaoFrase()){
			contexto.proxFrase();
		}
	}

	public void antPalavra() {
		if(contexto.getPosicaoPalavra() > 0){
			contexto.antPalavra();
		}else{
			contexto.primeiraPalavra();
		}
	}

	public void antFrase() {
		if(contexto.getPosicaoFrase() > 0){
			contexto.antFrase();
		}
	}
	public Localizacao getLocalizacaoFrase(){
		return contexto.getLocalizacaoFrase(texto);
	}
	public Localizacao getLocalizacaoPalavra(){
		return contexto.getLocalizacaoPalavra(texto);
	}
	public static long memoriaUsada() {
        Runtime r = Runtime.getRuntime();
        return (r.totalMemory() - r.freeMemory())  ;
    }
	public void inicioTexto(){
		contexto.primeiraFrase();
		
	}

	public static void main(String args[]) throws IOException {

		GerenciadorTextual f = new GerenciadorTextual();
		f.carregarTexto("teste.txt");
		
		System.out.println(f.getNumeroTotalPalavras());
		System.out.println(f.getNumeroObjetosPalavra());
		
		 long used = GerenciadorTextual.memoriaUsada();
	     System.out.println("\nMemoria usada:" + used + "Bytes");
	}

	private Texto texto;
	private Contexto contexto;
	private String arquivo;
	
}
