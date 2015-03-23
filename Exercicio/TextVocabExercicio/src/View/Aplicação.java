package View;

import Controller.Controlador;
import Model.GerenciadorTextual;

public class Aplicação {

    public static void main(String args[])
    {
    GerenciadorTextual facade = new GerenciadorTextual();
    Controlador controlador = new Controlador();
    new Visualizador(controlador);
 
    controlador.setModel(facade);
    
    }

}
