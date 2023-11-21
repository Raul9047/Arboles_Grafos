package Arbol.MVias;

import Excepciones.DatoYaExisteException;
import Excepciones.OrdenInvalidoException;

public class MainArbolB {
    public static void main(String[] args) throws OrdenInvalidoException, DatoYaExisteException {
        ArbolB<Integer, String> arbol = new ArbolB<>(4);
        arbol.insertar(14, "Hola mundo");
        arbol.insertar(21, "volvi");
        arbol.insertar(1, "raul");
        arbol.insertar(15, "mari");
    }
}
