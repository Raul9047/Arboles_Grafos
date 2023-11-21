package Grafo;

import Grafo.Excepciones.AristaYaExisteException;
import Grafo.Excepciones.NroVerticesInvalidoException;
import Grafo.NoPesado.Grafo;

public class Main {
    public static void main(String[] args) throws Excepciones.NroVerticesInvalidoException, AristaYaExisteException {
        Grafo grafo = new Grafo(6);
        grafo.insertarArista(0, 1);
        grafo.insertarArista(0, 3);
        grafo.insertarArista(1, 4);
        grafo.insertarArista(2, 4);
        grafo.insertarArista(3, 5);
        System.out.println("Cantidad de Aristas " + grafo.cantidadDeAristas());
    }
}
