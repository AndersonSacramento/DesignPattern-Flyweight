package Model;

import java.util.HashMap;

public class FlyweightFactory {
	
	public Palavra criarPalavra(String grafia){
		if(!palavras.containsKey(grafia)){
			palavras.put(grafia, new Palavra(grafia));
			
		}
		return palavras.get(grafia);
	}
	public int getNumeroObjetos(){
		return palavras.size();
	}
	private HashMap<String,Palavra> palavras = new HashMap<String,Palavra>();

}
