package Model;

import java.util.ArrayList;
import java.util.List;

public class Frase {

	Frase(char pontuacao) {
		this.pontuacao = pontuacao;

	}
	public void novoDestaque(Contexto contexto){
		Destaque destaque = criarDestaque(contexto);
		setDestaque(destaque,contexto);
	}
	
	public String visualizar(Contexto contexto) {
		StringBuffer strFrase = new StringBuffer();
		contexto.primeiraPalavra();
		for (Object obj : conteudo) {
			if (obj instanceof Palavra) {
				strFrase.append( ((Palavra)obj).visualizar(contexto));
				contexto.proxPalavra();
			}else{
				strFrase.append(obj.toString());
			}	
		}
		strFrase.append(pontuacao);
		contexto.inicioPalavra();
		return strFrase.toString();
	}

	
	public String visualizarComDestaque(Contexto contexto) {

		return contexto.getDestaque() + visualizar(contexto)
				+ contexto.getDestaque();
	}

	
	
	public void setDestaque(Destaque destaque, Contexto contexto) {
		
		contexto.setDestaque(destaque);

	}

	public void addPalavra(Palavra palavra) {
		palavras.add(palavra);
		conteudo.add(palavra);
		numeroPalavras++;
	}

	
	public Destaque getDestaque(Contexto contexto) {
		return contexto.getDestaque();
	}


	public void atualizarDestaque(Contexto contexto){
		Index atual = contexto.getContextoAtual().clone();
		contexto.inicioPalavra();
		this.setDestaque(this.getDestaque(contexto).setDestaque(defineDestaque(contexto)),
				contexto);
		contexto.setContextoAtual(atual);
	}
	
	protected Destaque criarDestaque(Contexto contexto) {
		DestaqueFrase destaqueFrase = new DestaqueFrase(
				defineDestaque(contexto));
		return destaqueFrase;
	}

	public ArrayList<Palavra> getPalavras() {
		return palavras;
	}

	public int getNumeroPalavras() {
		return numeroPalavras;
	}

	
	public byte defineDestaque(Contexto contexto) {
		 contexto.primeiraPalavra();
		for (Palavra palavra : palavras) {
			if (palavra.getDestaque(contexto).getTipo() == Destaque.DESCONHECIDO) {
				 contexto.inicioPalavra();
				return Destaque.DESCONHECIDO;
			}
			contexto.proxPalavra();
		}
		contexto.inicioPalavra();
		return Destaque.CONHECIDO;
	}

	
	public int getTamanho() {
		int tamanho = 0;
		for (Object obj : conteudo) {
			tamanho += obj.toString().length();
		}
		tamanho += 1; // Pontuacao
		return tamanho;
	}

	public int inicioPalavra(String palavra, int posicao) {
		int tamanho = 0;
		int i = -1;
		String espBranco = EspacoBranco.getInstancia().toString();
		for (Object obj : conteudo) {
			if (!obj.toString().equals(espBranco)) {
				i++;
				if (i == posicao) {
					return tamanho;
				}
			}
			tamanho += obj.toString().length();
		}
		return tamanho;
	}

	public void addEspacoBranco() {
		conteudo.add(EspacoBranco.getInstancia());
	}

	private ArrayList<Palavra> palavras = new ArrayList<Palavra>();
	private List<Object> conteudo = new ArrayList<Object>();
	private int numeroPalavras = 0;
	private char pontuacao;

}
