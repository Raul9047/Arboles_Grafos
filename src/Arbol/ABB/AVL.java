package Arbol.ABB;

import Arbol.MVias.NodoMVias;
import Excepciones.ClaveNoExisteException;

public class AVL <K extends Comparable<K>, V> extends ABB<K, V>{
    private static final int LIMITE_MAXIMO = 1;

    @Override
    public void insertarRec(K claveInsertar, V valorInsertar) {
        super.validar(claveInsertar, valorInsertar);
        super.raiz = insertarRec(super.raiz, claveInsertar, valorInsertar);
    }

    private NodoBinario<K, V> insertarRec(NodoBinario<K, V> nodoActual, K claveInsertar, V valorInsertar) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            NodoBinario<K, V> nuevoNodo = new NodoBinario<>(claveInsertar, valorInsertar);
            return nuevoNodo;
        }
        K claveNodoActual = nodoActual.getClave();
        if (claveInsertar.compareTo(claveNodoActual) < 0) {
            NodoBinario<K, V> supuestoHijoIzquierdo = insertarRec(nodoActual.getHijoIzquierdo(), claveInsertar, valorInsertar);
            nodoActual.setHijoIzquierdo(supuestoHijoIzquierdo);
            return balancear(nodoActual);
        }
        else if (claveInsertar.compareTo(claveNodoActual) > 0) {
            NodoBinario<K, V> supuestoHijoDerecho = insertarRec(nodoActual.getHijoDerecho(), claveInsertar, valorInsertar);
            nodoActual.setHijoDerecho(supuestoHijoDerecho);
            return balancear(nodoActual);
        } else
            nodoActual.setValor(valorInsertar);
        return null;
    }


    public NodoBinario<K, V> balancear(NodoBinario<K, V> nodoABalancear) {
        int alturaIzq = super.altura(nodoABalancear.getHijoIzquierdo());
        int alturaDer = super.altura(nodoABalancear.getHijoDerecho());
        int difereciaDeAltura = alturaIzq - alturaDer;
        //  ROTACION A LA DERECHA
        if (difereciaDeAltura > AVL.LIMITE_MAXIMO) {
            NodoBinario<K, V> hijoIzquierdoNodoABalancear = nodoABalancear.getHijoIzquierdo();
            alturaIzq = super.altura(hijoIzquierdoNodoABalancear.getHijoIzquierdo());
            alturaDer = super.altura(hijoIzquierdoNodoABalancear.getHijoDerecho());
            if (alturaDer > alturaIzq)
                return rotacionDobleDerecha(nodoABalancear);
            return rotacionSimpleDerecha(nodoABalancear);
        } else if (difereciaDeAltura < - AVL.LIMITE_MAXIMO) {
            NodoBinario<K, V> hijoDerechoABalancear = nodoABalancear.getHijoDerecho();
            alturaIzq = super.altura(hijoDerechoABalancear.getHijoIzquierdo());
            alturaDer = super.altura(hijoDerechoABalancear.getHijoDerecho());
            if (alturaDer < alturaIzq)
                return rotacionDobleIzquierda(nodoABalancear);
            return rotacionSimpleIzquierda(nodoABalancear);
        }
        return nodoABalancear;
    }

    private NodoBinario<K, V> rotacionSimpleDerecha(NodoBinario<K, V> nodoActual) {
        NodoBinario<K, V> nodoQueRota = nodoActual.getHijoIzquierdo();
        nodoActual.setHijoIzquierdo(nodoQueRota.getHijoDerecho());
        nodoQueRota.setHijoDerecho(nodoActual);
        return nodoQueRota;
    }

    private NodoBinario<K, V> rotacionSimpleIzquierda(NodoBinario<K, V> nodo) {
        NodoBinario<K, V> nuevoPadre = nodo.getHijoIzquierdo();
        nodo.setHijoIzquierdo(nuevoPadre.getHijoDerecho());
        nuevoPadre.setHijoDerecho(nodo);
        return nuevoPadre;
    }

    private NodoBinario<K, V> rotacionDobleIzquierda(NodoBinario<K, V> nodo) {
        return null;
    }

    private NodoBinario<K, V> rotacionDobleDerecha(NodoBinario<K, V> nodo) {
        return null;
    }

    @Override
    public V eliminar(K claveEliminar) throws ClaveNoExisteException {
        if (claveEliminar == null)
            throw new ClaveNoExisteException("Clave No Puede Ser Nula");
        V valorAELiminar = this.buscar(claveEliminar);
        if (valorAELiminar == null)
            throw new ClaveNoExisteException();
        this.raiz = eliminar(super.raiz, claveEliminar);
        return valorAELiminar;
    }

    private NodoBinario<K, V> eliminar(NodoBinario<K, V> nodoActual, K claveEliminar) {
        K claveActual = nodoActual.getClave();
        if (claveEliminar. compareTo(claveActual) < 0) {

        }
        return null;
    }


    @Override
    public void mostrarArbol() {
        super.mostrarArbol();
    }
}