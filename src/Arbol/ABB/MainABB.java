package Arbol.ABB;
import Excepciones.DatoYaExisteException;

import java.util.ArrayList;
import java.util.List;

public class MainABB {

    public static void main(String[] args) throws DatoYaExisteException {
        ABB<Integer, String> arbol = new ABB<>();
        arbol.insertarRec(75, "32");
        arbol.insertarRec(60, "32");
        arbol.insertarRec(43, "32");
        arbol.insertarRec(68, "32");
        arbol.insertarRec(90, "32");
        arbol.insertarRec(70, "32");
        arbol.insertarRec(85, "32");
        arbol.insertarRec(22, "32");
        arbol.insertar(56, "32");
        int i = arbol.cantNodos();

        arbol.mostrarArbol();

        List<Integer> lista = new ArrayList<>();
        
        lista = arbol.recorridoPorNiveles();
        System.out.print("Recorrido Por niveles ");
        arbol.mostrarRecorrido(lista);

        lista = arbol.recorridoPreOrden();
        System.out.print("\nRecorrido Pre Orden ");
        arbol.mostrarRecorrido(lista);

        lista = arbol.recorridoInOrden();
        System.out.print("\nRecorrido In Orden ");
        arbol.mostrarRecorrido(lista);

        lista = arbol.recorridoPostOrden();
        System.out.print("\nRecorrido Post Orden ");
        arbol.mostrarRecorrido(lista);

        System.out.println("\nLa cantidad de Nodos es : " + arbol.cantNodoRec());
        int j = arbol.contarHijosIzquierdoEnNivel(2);
    }
}