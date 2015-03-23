package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

import Controller.Controlador;
import Model.GerenciadorTextual;
import Model.Localizacao;

public class Apresentacao extends JFrame {



	Apresentacao(Controlador controlador) {	
		this.controlador = controlador;
		tela.setLayout(new BorderLayout());
		gerenciadorCaixaDeTexto();
		gerenciadorPainelInfo();
		gerenciadorPainelTexto();
		gerenciadorPainelBotoes();
		gerenciadorTela();

	
		eventoTeclado();
		eventoBotao();

		configuracoesTela();
	}

	public void gerenciadorTela() {
		tela.add(painelInfo, BorderLayout.EAST);
		tela.add(painelTexto, BorderLayout.WEST);
		tela.add(painelButoes, BorderLayout.SOUTH);
	}

	public void gerenciadorCaixaDeTexto(){
		caixaTextoInfo.setLineWrap(true);
		caixaTextoInfo.setFont(new Font("Arial", Font.BOLD, 14));
		caixaTextoInfo.setEditable(false);
		caixaTextoFrases();

	}
	private void caixaTextoFrases(){
		
		
		try {
			this.caixaTexto = new JTextArea(controlador.getTexto());
		} catch (IOException e) {
			
			e.printStackTrace();//Mensagem de erro
		}
		caixaTexto.setPreferredSize(new Dimension(472, 350));
		caixaTexto.setFont(new Font("Arial", Font.BOLD, 14));
		caixaTexto.setLineWrap(true);
		caixaTexto.setWrapStyleWord(true);
		caixaTexto.setEditable(false);

	}

	public void gerenciadorPainelInfo() {
		painelInfo.setPreferredSize(new Dimension(200, 350));
		painelInfo.setLayout(new BorderLayout());
		painelInfo.add(labelInfo, BorderLayout.NORTH);
		painelInfo.add(caixaTextoInfo, BorderLayout.CENTER);
	}

	public void gerenciadorPainelTexto() {
		painelTexto.setLayout(new BorderLayout());
		painelTexto.add(labelTexto, BorderLayout.NORTH);
		JScrollPane scrollPane = new JScrollPane(caixaTexto);

		painelTexto.add(scrollPane, BorderLayout.CENTER);
	}

	public void eventoTeclado() {
		caixaTexto.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if(controlador.capturarAcima(Apresentacao.this, ke)){
					;
				}else if(controlador.capturarAbaixo(Apresentacao.this, ke)){
					;
				}else if(controlador.capturarEsquerda(Apresentacao.this,ke)){
					;
				}else if(controlador.capturarDireita(Apresentacao.this,ke)){
					;
				}
			}		
		});
	}

	public void gerenciadorPainelBotoes() {
		FlowLayout layoutPainelButoes = new FlowLayout();
		painelButoes.setLayout(layoutPainelButoes);

		painelButoes.add(botaoEditar);
		botaoEditar.setVisible(false);
	}

	public void eventoBotao() {
		botaoEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				novaTela = new TelaEditar(controlador);
				novaTela.setVisible(true);
			}
		});
	}
	public void setVisibilidadeBotaoEditar(boolean visibilidade){
		botaoEditar.setVisible(visibilidade);
	}

	public void configuracoesTela() {
		this.setSize(700, 350);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void highLightFrase(	Localizacao localFrase){
		removeHighlights(caixaTexto);

		try {
			Highlighter hilite = caixaTexto.getHighlighter();
			hilite.addHighlight(localFrase.getInicio(), localFrase.getFim(), myHighlighter);
		} catch (BadLocationException e) {
		}
	}

	public void removeHighlights(JTextComponent textComp) {

		Highlighter hilite = textComp.getHighlighter();
		Highlighter.Highlight[] hilites = hilite.getHighlights();

		for (int i = 0; i < hilites.length; i++) {
			if (hilites[i].getPainter() instanceof MyHighlightPainter) {
				hilite.removeHighlight(hilites[i]);
			}
		}
	}

	Highlighter.HighlightPainter myHighlighter = new MyHighlightPainter(
			Color.LIGHT_GRAY);

	private class MyHighlightPainter extends
			DefaultHighlighter.DefaultHighlightPainter {

		public MyHighlightPainter(Color color) {

			super(color);

		}
	}

	public void highLightPalavra(Localizacao localPalavra){
		removeHighlights(caixaTexto);

		try {
			Highlighter hilite = caixaTexto.getHighlighter();
			hilite.addHighlight(localPalavra.getInicio(), localPalavra.getFim(), myHighlighter);

		} catch (BadLocationException e) {
		}

	}

	public void atualizarInfoPalavra(GerenciadorTextual model) {
		
		caixaTextoInfo.setText("\n"+model.visualizarPalavraDestaque()+"\n\n"
				+"Destaque:"+model.getDestaque().descricao()+"\n\n"
				+"Classe Gramatical:"+model.getClasseGramatical().toString()+"\n\n");
		
		

	}

	public void atualizarInfoFrase(GerenciadorTextual model) {
		
		caixaTextoInfo.setText("\n"+model.visualizarFraseDestaque()+"\n\n"
				+"Destaque:"+model.getDestaque().descricao()+"\n\n"
				+"Número de palavras:"+model.getNumeroPalavrasFrase());
		

	}

	public void atualizarInfoTexto() {
		// Atualizar informações sobre texto

	}

	private JLabel labelInfo = new JLabel("Informações");
	private JLabel labelTexto = new JLabel("Texto");
	private JTextArea caixaTextoInfo = new JTextArea();
	private JTextArea caixaTexto; 
	private JButton botaoEditar = new JButton("Editar");
	private JPanel painelInfo = new JPanel();
	private JPanel painelTexto = new JPanel();
	private JPanel painelButoes = new JPanel();
	private Container tela = this.getContentPane();
	private TelaEditar novaTela;
	private Controlador controlador;
	private static final long serialVersionUID = 126L;

}
