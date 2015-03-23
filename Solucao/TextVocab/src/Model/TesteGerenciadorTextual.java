package Model;

import java.io.IOException;

import junit.framework.TestCase;

public class TesteGerenciadorTextual extends TestCase {

	public TesteGerenciadorTextual(String name) {
		super(name);
	}

	protected void setUp() {
		System.out.println("Início do Teste\n\n");
	}

	public void testeDestaqueFrase() {
		GerenciadorTextual facade = new GerenciadorTextual();
		try {
			facade.carregarTexto("teste.txt");
		} catch (IOException e) {

			e.printStackTrace();
		}
		facade.inicioTexto();
		facade.proxFrase();
		facade.proxPalavra();
		facade.editarDestaque(Destaque.CONHECIDO);
		facade.antFrase();
		facade.proxFrase();
		assertEquals("[f*]" + "      Republicans!" + "[f*]", facade
				.visualizarFraseDestaque());
	}

	public void testeModificarClasseGramatical() {
		GerenciadorTextual facade = new GerenciadorTextual();
		try {
			facade.carregarTexto("teste.txt");
		} catch (IOException e) {

			e.printStackTrace();
		}
		facade.inicioTexto();
		facade.proxFrase();
		facade.proxPalavra();
		facade.editarClasseGramatical(ClasseGramatical.SUBSTANTIVO);
		ClasseGramatical cg = facade.getClasseGramatical();
		facade.editarClasseGramatical(ClasseGramatical.SUBSTANTIVO);
		boolean igual =  facade.getClasseGramatical() == cg;
		assertEquals(true, igual);
	}
	public void testeFlyweightPalavra(){
		GerenciadorTextual gTextual = new GerenciadorTextual();
		try {
			gTextual.carregarTexto("teste.txt");
		} catch (IOException e) {

			e.printStackTrace();
		}
		int numTotal = gTextual.getNumeroTotalPalavras();
		int numDistintas  = gTextual.getNumeroObjetosPalavra();
		
		boolean isTrue  =  numTotal > numDistintas;
		assertEquals(true, isTrue);
	}
	
	

	protected void tearDown() {
		System.out.println("Teste Finalizado\n\n");
	}

}
