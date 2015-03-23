package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.Controlador;
import Model.ClasseGramatical;
import Model.Destaque;

public class TelaEditar extends JFrame {


	
	TelaEditar(Controlador controlador) {
		this.controlador = controlador;
		tela.setLayout(new BorderLayout());
		gerenciadorPainelListaClasse();
		gerenciadorPainelListaDestaque();
		gerenciadorPainelLabel();
		gerenciadorPainelBotoes();
		preencherJComboBoxClasse();
		preencherJComboBoxDestaque();

		gerenciadorTela();

		this.setSize(200, 250);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	public void gerenciadorTela() {
		tela.add(painelLabel, BorderLayout.WEST);
		tela.add(painelButoes, BorderLayout.SOUTH);
	}

	public void gerenciadorPainelLabel() {
		BoxLayout layoutPainelButoes = new BoxLayout(painelLabel,
				getDefaultCloseOperation());
		painelLabel.setLayout(layoutPainelButoes);

		painelLabel.add(painelListaClasse);
		painelLabel.add(painelListaDestaque);
	}

	public void gerenciadorPainelBotoes() {
		FlowLayout layoutPainelButoes = new FlowLayout();
		painelButoes.setLayout(layoutPainelButoes);

		painelButoes.add(botaoSalvar);
		painelButoes.add(botaoCancelar);
		eventoBotaoCancelar();
		eventoBotaoSalvar();

	}

	public void gerenciadorPainelListaClasse() {
		FlowLayout layoutPainelButoes = new FlowLayout();
		painelListaClasse.setLayout(layoutPainelButoes);

		painelListaClasse.add(labelClasse);
		painelListaClasse.add(listaComboBoxClasses);
		eventoBoxClasse();
		
	}

	public void gerenciadorPainelListaDestaque() {
		FlowLayout layoutPainelButoes = new FlowLayout();
		painelListaClasse.setLayout(layoutPainelButoes);

		painelListaDestaque.add(labelDestaque);
		painelListaDestaque.add(listaComboBoxDestaque);
		eventoBoxDestaque();
	}

	public void preencherJComboBoxClasse() {

		for (String str : ClasseGramatical.getOpcoes()) {
			listaComboBoxClasses.addItem(str);
		}

	}

	public void preencherJComboBoxDestaque() {
		for (String str : Destaque.getOpcoes()) {
			listaComboBoxDestaque.addItem(str);
		}
	}

	public void eventoBotaoSalvar(){

		botaoSalvar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				controlador.setClasseGramatical(tipoClasseGramatical);
				controlador.setDestaque(tipoDestaque);
				fecharTela();
			}
		});

	}

	private void eventoBoxClasse() {
		listaComboBoxClasses.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				tipoClasseGramatical = (byte) cb.getSelectedIndex();
				if(tipoClasseGramatical == -1){
					tipoClasseGramatical = 0;
				}
			}
		});

	}
	private void eventoBoxDestaque() {
		listaComboBoxDestaque.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				tipoDestaque = (byte) cb.getSelectedIndex();
				if(tipoDestaque == -1){
					tipoDestaque = 0;
				}
			}
		});

	}


	private void eventoBotaoCancelar(){

		botaoCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fecharTela();
			}
		});

	}

	public void fecharTela() {
		this.setVisible(false);

	}

	private Container tela = this.getContentPane();
	private JPanel painelLabel = new JPanel();
	private JPanel painelButoes = new JPanel();
	private JPanel painelListaClasse = new JPanel();
	private JPanel painelListaDestaque = new JPanel();
	private JLabel labelClasse = new JLabel("Classe: ");
	private JLabel labelDestaque = new JLabel("   Destaque: ");
	private JButton botaoSalvar = new JButton("Salvar");
	private JButton botaoCancelar = new JButton("Cancelar");
	private JComboBox<String> listaComboBoxClasses = new JComboBox<String>();
	private JComboBox<String> listaComboBoxDestaque = new JComboBox<String>();
	private byte tipoDestaque;
	private byte tipoClasseGramatical;
	private Controlador controlador;
	private static final long serialVersionUID = 156L;

}
