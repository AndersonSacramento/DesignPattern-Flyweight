package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessadorTexto {

	public void lerTexto(String path, Texto texto, Contexto contexto)
			throws IOException {
		this.texto = texto;
		this.contexto = contexto;
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		String linha = "";
		iniciarPadroes();

		while ((linha = buffRead.readLine()) != null) {
			tratarFrase(linha);
			continuacaoFrase(linha);
		}
		contexto.inicio();
		texto.novoDestaque(contexto);
		buffRead.close();

	}

	public static String conteudo(String path) throws IOException {
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		StringBuffer conteudo = new StringBuffer("");
		String linha = "";

		while ((linha = buffRead.readLine()) != null) {
			conteudo.append(linha);
		}
		buffRead.close();
		return conteudo.toString();
	}

	private void tratarFrase(String linha) {
		Matcher matcherFrase = padraoFrase.matcher(linha);

		ultimoIndex = 0;
		while (matcherFrase.find()) {
			Character pontuacao = linha.charAt(matcherFrase.start());
			criarFrase(
					continuacao
							+ linha.substring(ultimoIndex, matcherFrase.start()),
					pontuacao);
			ultimoIndex = matcherFrase.end();
			continuacao = "";
		}
	}

	private void continuacaoFrase(String linha) {
		Matcher matcherContinuacao = padraoContinuacao.matcher(linha);

		if (matcherContinuacao.find()) {
			continuacao = linha.substring(ultimoIndex,
					matcherContinuacao.start() - 1);
		} else if (ultimoIndex < linha.length() - 1) {
			continuacao = linha.substring(ultimoIndex) + " ";
		}
	}

	private void iniciarPadroes() {
		padraoFrase = Pattern.compile("([?.,:!...])");
		padraoContinuacao = Pattern.compile("([-])");
	}

	private Palavra criarPalavra(String s) {
		Palavra palavra = new Palavra(s);
		numeroObjetosPalavra++;
		palavra.novoDestaque(contexto);
		palavra.setClasseGramatical(new ClasseGramatical(ClasseGramatical.DESCONHECIDO),
				contexto);

		return  palavra ;

	}

	private void criarFrase(String frase, char pontuacao) {
		String[] palavras = frase.split("\\b");
		Frase f = new Frase(pontuacao);

		contexto.primeiraPalavra();
		for (String s : palavras) {
			if (!s.isEmpty()) {
				if (!s.contains(" ")) {
					f.addPalavra(criarPalavra(s));
					contexto.proxPalavra();
					
				}else{
					for(int i = 0; i < s.length(); i++){
						f.addEspacoBranco();
					}
				}
			}

		}
		f.novoDestaque(contexto);
		contexto.proxFrase();
		texto.addFrase(f);
	}

	public int getNumeroObjetosPalavra() {
		return this.numeroObjetosPalavra;
	}

	
	int numeroObjetosPalavra = 0;
	private Texto texto;
	private Contexto contexto;
	private Pattern padraoFrase = null;
	private Pattern padraoContinuacao = null;
	private int ultimoIndex = 0;
	private String continuacao = "";
}
