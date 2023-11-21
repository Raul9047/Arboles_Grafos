package Arbol.MVias;

import Excepciones.ClaveNoExisteException;
import Excepciones.DatoYaExisteException;
import Excepciones.OrdenInvalidoException;

import java.util.Stack;

public class ArbolB <K extends Comparable<K>, V> extends ArbolMVias<K, V>{
    private final int nroMinimoDeHijos;
    private final int nroMinimoDeDatos;
    private final int nroMaximoDeDatos;

    public ArbolB() {
        this.nroMaximoDeDatos = super.orden - 1;
        this.nroMinimoDeDatos = this.nroMaximoDeDatos / 2;
        this.nroMinimoDeHijos = this.nroMinimoDeDatos + 1;
    }

    public ArbolB(int orden) throws OrdenInvalidoException {
        super(orden);
        this.nroMaximoDeDatos = super.orden - 1;
        this.nroMinimoDeDatos = this.nroMaximoDeDatos / 2;
        this.nroMinimoDeHijos = this.nroMinimoDeDatos + 1;
    }

    @Override
    public void insertar(K claveInsertar, V valorInsertar) throws DatoYaExisteException {
        super.validar(claveInsertar, valorInsertar);
        if (super.esArbolVacio()) {
            super.raiz = new NodoMVias<>(super.orden, claveInsertar, valorInsertar);
            return;
        }
        NodoMVias<K, V> nodoActual = this.raiz;
        NodoMVias<K, V> nodoPadre = null;
        while (!NodoMVias.esNodoVacio(nodoActual)) {
            if (nodoActual.esHoja()) {
                if (nodoActual.nroDeClaveNoVacias() <= this.nroMaximoDeDatos) {
                    this.insertarClaveYValorOrdenado(nodoActual, claveInsertar, valorInsertar);
                    nodoActual = NodoMVias.nodoVacio();
                    
                }
            } else {
                int posicion = this.buscarPosicionPorDondeBajar(nodoActual, claveInsertar);
                nodoPadre = nodoActual;
                nodoActual = nodoActual.getHijo(posicion);
            }
        }
    }

    @Override
    public V eliminar(K claveAEliminar) throws ClaveNoExisteException, NullPointerException {
        if (claveAEliminar == null)
            throw new NullPointerException("Clave a Eliminar no puede ser Nula");
        Stack<NodoMVias<K, V>> pilaDeAncestros = new Stack<>();
        NodoMVias<K, V> nodoActual = this.buscarNodoDeLaClave(claveAEliminar, pilaDeAncestros);
        if (NodoMVias.esNodoVacio(nodoActual))
            throw new ClaveNoExisteException();
        int posicionDeClaveAEliminar = super.buscarPosicionPorDondeBajar(nodoActual, claveAEliminar);
        V valorARetornar = nodoActual.getValor(posicionDeClaveAEliminar);
        if (nodoActual.esHoja()) {
            super.eliminarClaveDeNodoEnPosicion(nodoActual, posicionDeClaveAEliminar);
            if (nodoActual.nroDeClaveNoVacias() < this.nroMinimoDeDatos) {
                if (pilaDeAncestros.isEmpty()) {
                    if (!nodoActual.hayClavesNoVacias())
                        super.vaciar();
                } else {
                    this.prestarseOFusionarse(nodoActual,pilaDeAncestros);
                }
            }
        } else {
            pilaDeAncestros.push(nodoActual);
            NodoMVias<K, V> nodoDelPredecesor = this.obtenerNodoDelPredecedor(pilaDeAncestros, nodoActual.getHijo(posicionDeClaveAEliminar));
            int posicionDeClavePredecesora = nodoDelPredecesor.nroDeClaveNoVacias() - 1;
            K clavePredecesora = nodoDelPredecesor.getClave(posicionDeClavePredecesora);
            V valorAsociadoDelPredecesor = nodoDelPredecesor.getValor(posicionDeClaveAEliminar);
            super.eliminarClaveDeNodoEnPosicion(nodoDelPredecesor, posicionDeClavePredecesora);
            nodoActual.setClave(posicionDeClaveAEliminar, clavePredecesora);
            nodoActual.setValor(posicionDeClaveAEliminar, valorAsociadoDelPredecesor);
            if (nodoDelPredecesor.nroDeClaveNoVacias() < this.nroMinimoDeDatos)
                this.prestarseOFusionarse(nodoDelPredecesor, pilaDeAncestros);
        }
        return valorARetornar;
    }

    private NodoMVias<K,V> obtenerNodoDelPredecedor(Stack<NodoMVias<K,V>> pilaDeAncestros, NodoMVias<K,V> hijo) {
        return null;
    }

    private NodoMVias<K,V> buscarNodoDeLaClave(K claveAEliminar, Stack<NodoMVias<K,V>> pilaDeAncestros) {
        return null;
    }

    private void prestarseOFusionarse(NodoMVias<K, V> nodoDelPredecesor, Stack<NodoMVias<K, V>> pilaDeAncestros) {
        while (!pilaDeAncestros.isEmpty() && nodoDelPredecesor.nroDeClaveNoVacias() < this.nroMinimoDeDatos) {
            NodoMVias<K, V> nodoPadre = pilaDeAncestros.pop();
            int posicionEnPadre = buscarPosicionEnPadre(nodoPadre, nodoDelPredecesor);
            if (posicionEnPadre < nodoPadre.nroHijosNoVacios() - 1) {

            }
        }

    }

    private int buscarPosicionEnPadre(NodoMVias<K,V> nodoPadre, NodoMVias<K,V> nodoDelPredecesor) {
        return 0;
    }

}