package View;

import Controller.Controlador;

public class Visualizador {
	public Visualizador(Controlador controlador){
		this.controlador = controlador;
		 new TelaCarregarTexto(this.controlador,this.apresentacao);
		
	}
	public Apresentacao getApresentacao(){
		return this.apresentacao;
	}

	private Controlador controlador;
	private Apresentacao apresentacao;
}
