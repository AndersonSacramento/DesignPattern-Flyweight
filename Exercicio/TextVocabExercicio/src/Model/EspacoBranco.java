package Model;

public class EspacoBranco {
	
	private EspacoBranco(){
		this.conteudo = " ";
	}
	public static EspacoBranco getInstancia(){
		if(instancia == null){
			instancia = new EspacoBranco();
		}
		return instancia;
	}
	public String toString(){
		return conteudo;
	}
	private String conteudo;
	private static EspacoBranco instancia;
}
