package Arbol.ABB;

/**
* @author Raul Laveran Ardaya
* @param <K>
* @param <V>
 **/

public class NodoBinario <K, V> {
    private NodoBinario <K, V> hijoIzquiedo;
    private NodoBinario <K, V> hijoDerecho;
    private K clave;
    private V valor;

    public NodoBinario() {
    }

    public NodoBinario(K clav, V val) {
        this.clave = clav;
        this.valor = val;
    }

    public NodoBinario<K, V> getHijoIzquierdo() {
        return  this.hijoIzquiedo;
    }

    public void setHijoIzquierdo (NodoBinario<K, V> HijoIzquierdo) {
        this.hijoIzquiedo = HijoIzquierdo;
    }

    public NodoBinario<K, V> getHijoDerecho() {
        return this.hijoDerecho;
    }

    public void setHijoDerecho(NodoBinario<K, V> HijoDerecho) {
        this.hijoDerecho = HijoDerecho;
    }

    public K getClave() {
        return this.clave;
    }

    public void setClave(K Clave) {
        this.clave = Clave;
    }

    public V getValor() {
        return this.valor;
    }

    public void setValor(V Valor) {
        this.valor = Valor;
    }

    public static boolean esNodoVacio(NodoBinario nodo) {
        return (nodo == null); // arreglar esta parte
    }

    public static NodoBinario nodoVacio() {
        return null;
    }

    public boolean esVacioHijoIzquierdo() {
        return esNodoVacio(this.hijoIzquiedo);
    }

    public boolean esVacioHijoDerecho() {
        return esNodoVacio(this.hijoDerecho);
    }

    public boolean esHoja() {
        return (this.esVacioHijoIzquierdo() && this.esVacioHijoDerecho());
    }
}
