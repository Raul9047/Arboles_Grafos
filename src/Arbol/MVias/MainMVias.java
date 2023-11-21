package Arbol.MVias;

import Excepciones.ClaveNoExisteException;
import Excepciones.DatoYaExisteException;
import Excepciones.OrdenInvalidoException;

import java.util.ArrayList;
import java.util.List;

public class MainMVias {
    public static void main(String[] args) throws DatoYaExisteException, OrdenInvalidoException, ClaveNoExisteException {
        ArbolMVias<Integer, String> arbol = new ArbolMVias<>(3);
        arbol.insertar(50,"Azul");
        arbol.insertar(78, "Naranja");
        arbol.insertar(74, "Zapato");
        arbol.insertar(30, "Jeans");
        arbol.insertar(44, "Amarillo");
        arbol.insertar(20, "Negro");
        arbol.insertar(25, "Café");
        arbol.insertar(24, "Camisa");
        arbol.insertar(23, "Mesa");
        arbol.insertar(28, "TV");
        arbol.insertar(74, "Banana");
        arbol.insertar(120, "Arroz");
        arbol.insertar(35, "Blusa");
        arbol.insertar(111, "Zapato");
        arbol.insertar(90, "Portatil");
        arbol.insertar(81, "Llaves");
        arbol.insertar(71, "Mouse");
        arbol.insertar(100, "Cables");
        arbol.insertar(89, "Termico");
        arbol.insertar(51, "Gato");
        arbol.insertar(76, "Gorrión");

        List<Integer> lista = new ArrayList<>();
        lista = arbol.recorridoPorNiveles();
        arbol.mostrarRecorrido(lista);

        arbol.eliminar(44);
        lista = arbol.recorridoPorNiveles();
        arbol.mostrarRecorrido(lista);
    }
}
