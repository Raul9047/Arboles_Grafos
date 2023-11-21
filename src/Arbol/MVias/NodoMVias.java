package Arbol.MVias;

import java.util.ArrayList;
import java.util.List;

public class NodoMVias <K, V>{
    private List<K> listaDeClaves;
    private List<V> listaDeValores;
    private List<NodoMVias<K, V>> listaDeHijos;

    public static NodoMVias nodoVacio() {
        return null;
    }

    public static Object datoVacio() {
        return null;
    }

    public static boolean esNodoVacio(NodoMVias unNodo) {
        return unNodo == NodoMVias.nodoVacio();
    }

    public NodoMVias(int orden) {
        this.listaDeClaves = new ArrayList<>();
        this.listaDeValores = new ArrayList<>();
        this.listaDeHijos = new ArrayList<>();
        for (int i = 0; i < orden; i++) {
            this.listaDeClaves.add((K) NodoMVias.datoVacio());
            this.listaDeValores.add((V) NodoMVias.datoVacio());
            this.listaDeHijos.add(NodoMVias.nodoVacio());
        }
        this.listaDeHijos.add(NodoMVias.nodoVacio());
    }

    public NodoMVias(int orden, K clave, V valor) {
        this(orden);
        this.listaDeClaves.set(0, clave);
        this.listaDeValores.set(0, valor);
    }

    public K getClave(int posicion) {
        return this.listaDeClaves.get(posicion);
    }

    public void setClave(int posicion, K clave) {
        this.listaDeClaves.set(posicion, clave);
    }

    public V getValor(int posicion) {
        return this.listaDeValores.get(posicion);
    }

    public void setValor(int posicion, V valor) {
        this.listaDeValores.set(posicion, valor);
    }

    public NodoMVias<K, V> getHijo(int posicion) {
        return this.listaDeHijos.get(posicion);
    }

    public void setHijo(int posicion, NodoMVias<K, V> unHijo) {
        this.listaDeHijos.set(posicion, unHijo);
    }

    public boolean esHijoVacio(int posicion) {
        return NodoMVias.esNodoVacio(this.listaDeHijos.get(posicion));
    }

    public boolean esDatoVacio(int posicion) {
        return NodoMVias.datoVacio() == listaDeClaves.get(posicion);
    }

    public boolean esHoja() {
        for (int i = 0; i < this.listaDeHijos.size(); i++) {
            if (!this.esHijoVacio(i))
                return false;
        }
        return true;
    }

    public int nroDeClaveNoVacias() {
        int cantidadDeClaveNoVacias = 0;
        for (int i = 0; i < this.listaDeClaves.size(); i++) {
            if (!this.esDatoVacio(i))
                cantidadDeClaveNoVacias++;
        }
        return cantidadDeClaveNoVacias;
    }

    public boolean hayClavesNoVacias() {
        return this.nroDeClaveNoVacias() != 0;
    }

    public boolean estanClavesLlenas() {
        return  this.nroDeClaveNoVacias() == this.listaDeClaves.size();
    }

    public int nroHijosNoVacios() {
        return 0;
    }
}
