package View;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Controller.Controlador;
public class TelaCarregarTexto extends JFrame {

	private static final long serialVersionUID = 1876L;
	public TelaCarregarTexto(Controlador controlador, Apresentacao telaPrincipal) {
		this.controlador = controlador;
		this.telaPrincipal = telaPrincipal;
		tela.setLayout(new FlowLayout());
		tela.add(carregarTexto);
		tela.add(campoCarregarTexto);
		tela.add(botaoConfirmarCarregar);
		eventoBotao();
		configuracoesTela();

	}

	public void eventoBotao() {
		botaoConfirmarCarregar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					controlador.setNomeTexto(campoCarregarTexto.getText());
				} catch (IOException e) {
					e.printStackTrace(); //Apresentar mensagem de erro
				}
				telaPrincipal = new Apresentacao(controlador); 	
				fecharTela();
				telaPrincipal.setVisible(true);
			}

		});

	}

	private void fecharTela() {
		this.setVisible(false);

	}

	public void configuracoesTela() {
		this.setSize(200, 250);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}


	JLabel carregarTexto = new JLabel("Carregar Texto: ");
	JTextField campoCarregarTexto = new JTextField(15);
	JButton botaoConfirmarCarregar = new JButton("Confirmar");
	Container tela = this.getContentPane();
	Apresentacao telaPrincipal;
	Controlador controlador;

}
