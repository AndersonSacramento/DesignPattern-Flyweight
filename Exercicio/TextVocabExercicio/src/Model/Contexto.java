package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Contexto {
	
	
	
	public void setClasseGramatical(ClasseGramatical classe){
		novaClasseGramatical(classe);
		String strIndex = this.index.toString();
		ClasseGramatical classeAnterior = getClasseGramatical();
		if(classeAnterior != null && !classeAnterior.equals(classe) ){
			mapClasseGramatical.get(classeAnterior).remove(strIndex);
		}else if(classeAnterior != null && classeAnterior.equals(classe)){ //classeAnterior == classe){
			return;
		}
		if(!mapClasseGramatical.containsKey(classe)){
			mapClasseGramatical.put(classe, new ArrayList<String>());
		}
		mapClasseGramatical.get(classe).add(strIndex);			
		
	}
	public void  setDestaque(Destaque destaque){
		
		novoDestaque(destaque);
		String strIndex = this.index.toString();
		Destaque destaqueAnterior = getDestaque();
		if(destaqueAnterior != null && destaqueAnterior != destaque){
			mapDestaque.get(destaqueAnterior).remove(strIndex);
		}else if(destaqueAnterior == destaque){
				return;
		}
		if(!mapDestaque.containsKey(destaque)){
			mapDestaque.put(destaque, new ArrayList<String>());
		}
		mapDestaque.get(destaque).add(strIndex);			
	}
	
	public Destaque getDestaque(){
		Destaque destaque = null;	
		Iterator<Destaque> iterator = mapDestaque.keySet().iterator();
		String strIndex = this.index.toString();
		
		while(iterator.hasNext()){
			destaque  = iterator.next();
			if( mapDestaque.get(destaque).contains(strIndex) ){
				break;
			}
			destaque = null;
		}
		return destaque;
	}
	public ClasseGramatical getClasseGramatical(){
		ClasseGramatical classe = null;
		Iterator<ClasseGramatical> iterator = mapClasseGramatical.keySet().iterator();
		String strIndex = this.index.toString();
		
		while(iterator.hasNext()){
			classe  = iterator.next();
			if( mapClasseGramatical.get(classe).contains(strIndex) ){
				break;
			}
			classe = null;
		}
		return classe;
	}
	public void proxFrase(){
		index.proxFrase();
		index.inicioPalavra();
		
	}
	public void proxPalavra(){
		index.proxPalavra();
		
	}
	public void antFrase(){
		index.antFrase();
		index.inicioPalavra();
		System.out.println("Index::"+index.toString());
	}
	public void antPalavra(){
		index.antPalavra();
	}
	public Integer getPosicaoFrase() {
		return index.getPosicaoFrase();
	}
	public Integer getPosicaoPalavra() {
		return index.getPosicaoPalavra();
	}
    public Index getContextoAtual(){
    	return index;
    }
    public void setContextoAtual(Index index){
    	this.index = index;
    }
    public void inicioFrase(){
    	index.inicioFrase();
    }
    public void inicioPalavra(){
    	index.inicioPalavra();
    }
    public void inicio(){
    	index.inicioFrase();
    	index.inicioPalavra();
    }
    public void primeiraPalavra(){
    	index.primeiraPalavra();
    }
    public void primeiraFrase(){
    	index.primeiraFrase();
    }
    public void novoDestaque(Destaque destaque){
    	if(!piscinaDestaques.containsKey(destaque.getId())){
    		piscinaDestaques.put(destaque.getId(), destaque);
		}
    }
    public void novaClasseGramatical(ClasseGramatical classeGram){
    	if( !piscinaClasseGramatical.containsKey(classeGram.getTipo()) ){
    		piscinaClasseGramatical.put(classeGram.getTipo(), classeGram);
 
    	}
    }
    public Localizacao getLocalizacaoFrase(Texto texto){
    	int inicio = 0, fim = 0;
    	List<Frase> frases = texto.getFrases();
    	for(int i = 0; i < index.getPosicaoFrase(); i++){
    		inicio += frases.get(i).getTamanho();
    	}
    	fim = inicio + frases.get(index.getPosicaoFrase()).getTamanho();
    	return new Localizacao(inicio,fim);
    }
    public Localizacao getLocalizacaoPalavra(Texto texto){
    	int inicio = 0, fim = 0;  	
    	List<Frase> frases = texto.getFrases();
    	Frase frase = frases.get(index.getPosicaoFrase());
    	List<Palavra> palavras = frase.getPalavras();
    	Palavra palavra = palavras.get(index.getPosicaoPalavra());
    
    	Localizacao localFrase = getLocalizacaoFrase(texto);
    	inicio = localFrase.getInicio() + frase.inicioPalavra(palavra.toString(),index.getPosicaoPalavra());
    	fim = inicio + palavras.get(index.getPosicaoPalavra()).getTamanho();
  
    	return new Localizacao(inicio, fim);
    }
    
	private Index index = new Index();
	private HashMap<Destaque,List<String>> mapDestaque = new HashMap<Destaque,List<String>>();
	private HashMap<ClasseGramatical,List<String>> mapClasseGramatical = new HashMap<ClasseGramatical,List<String>>();
	
	public HashMap<String,Destaque> piscinaDestaques = new HashMap<String,Destaque>(); 
	private HashMap<Byte, ClasseGramatical> piscinaClasseGramatical = new HashMap<Byte, ClasseGramatical>(); 
	
	
}
