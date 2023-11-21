package Arbol.ABB;

import Excepciones.ClaveNoExisteException;
import Excepciones.DatoYaExisteException;

import java.util.List;

public interface InterfaceABB <K, V> {
    boolean esVacio();
    int size();
    int altura();
    int nivel();
    void vaciar();
    boolean esArbolVacio();
    boolean contiene(K clave);
    List<K> recorridoPorNiveles();
    List<K> recorridoPostOrden();
    List<K> recorridoPreOrden();
    List<K> recorridoInOrden();
    List<K> recorridoInOrdenRec();
    V buscar(K claveBuscar);
    void insertar(K clave, V valor) throws DatoYaExisteException;
    V eliminar(K clave) throws ClaveNoExisteException;
}
