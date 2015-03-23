package Controller;

import java.awt.event.KeyEvent;
import java.io.IOException;

import Model.GerenciadorTextual;
import View.Apresentacao;

public class Controlador {

	public void setNomeTexto(String nome) throws IOException{
		this.model.carregarTexto(nome);
		this.model.inicioTexto();
	}
	public String getTexto() throws IOException{
		return this.model.textoOriginal();
	}

	public boolean capturarAcima(Apresentacao view ,KeyEvent ke){
		if(ke.getKeyCode() == KeyEvent.VK_UP){
			this.model.antFrase();
			highlightFrase(view);
			view.atualizarInfoFrase(model);
			view.setVisibilidadeBotaoEditar(false);
			return true;
		}
		return false;
	}
	public boolean capturarAbaixo(Apresentacao view,KeyEvent ke){
		if(ke.getKeyCode() == KeyEvent.VK_DOWN){
			this.model.proxFrase();
			highlightFrase(view);
			view.atualizarInfoFrase(model);
			view.setVisibilidadeBotaoEditar(false);
			return true;
		}
		return false;
	}
	public boolean capturarEsquerda(Apresentacao view, KeyEvent ke){
		if(ke.getKeyCode() == KeyEvent.VK_LEFT){
			this.model.antPalavra();
			highlightPalavra(view);
			view.atualizarInfoPalavra(model);
			view.setVisibilidadeBotaoEditar(true);
			return true;
		}
		return false;
	}
	public boolean capturarDireita(Apresentacao view, KeyEvent ke){
		if(ke.getKeyCode() == KeyEvent.VK_RIGHT){
			this.model.proxPalavra();
			highlightPalavra(view);
			view.atualizarInfoPalavra(model);
			view.setVisibilidadeBotaoEditar(true);
			return true;
		}
		return false;
	}

    public boolean setClasseGramatical(byte tipo){
    	if(model.editarClasseGramatical(tipo)){
    		return true;
    	}
    	return false;
    }
    public boolean setDestaque(byte tipo){
    	if(model.editarDestaque(tipo)){
    		return true;
    	}
    	return false;
    }
    
	private void highlightFrase(Apresentacao view) {
		view.highLightFrase(this.model.getLocalizacaoFrase());
	}

	private void highlightPalavra(Apresentacao view) {
		view.highLightPalavra(this.model.getLocalizacaoPalavra());
	}

	public void setModel(GerenciadorTextual model){
		this.model = model;
	}
	private GerenciadorTextual model;
	

}
