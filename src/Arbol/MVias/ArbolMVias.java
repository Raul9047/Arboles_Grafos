package Arbol.MVias;

import Arbol.ABB.InterfaceABB;
import Arbol.ABB.NodoBinario;
import Excepciones.ClaveNoExisteException;
import Excepciones.DatoYaExisteException;
import Excepciones.OrdenInvalidoException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ArbolMVias<K extends Comparable<K>, V> implements InterfaceABB<K, V> {

    protected NodoMVias<K, V> raiz;
    protected final int orden;
    private static final int ORDEN_MINIMO = 3;
    private static final int POSICION_INVALIDA = -1;

    public ArbolMVias() {
        this.orden = ArbolMVias.ORDEN_MINIMO;
    }

    public ArbolMVias(int orden) throws OrdenInvalidoException {
        if (orden < ArbolMVias.ORDEN_MINIMO)
            throw new OrdenInvalidoException();
        this.orden = orden;
    }

    @Override
    public boolean esVacio() {
        return NodoMVias.esNodoVacio(this.raiz);
    }

    @Override
    public int size() {
        int nroNodos = 0;
        if (!this.esArbolVacio()) {
            Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            while (!colaDeNodos.isEmpty()) {
                NodoMVias<K, V> nodoActual = colaDeNodos.poll();
                nroNodos++;
                for (int i = 0; i < this.orden; i++) {
                    if (nodoActual.esHijoVacio(i))
                        colaDeNodos.offer(nodoActual.getHijo(i));
                }
            }
        }
        return nroNodos;
    }

    @Override
    public int altura() {
        return altura(this.raiz);
    }

    public int altura(NodoMVias<K, V> nodoActual) {
        if (NodoMVias.esNodoVacio(nodoActual))
            return 0;
        int alturaMayor = 0;
        for (int i = 0; i < this.orden; i++) {
            int alturaDelHijoActual = altura(nodoActual.getHijo(i));
            if (alturaDelHijoActual > alturaMayor)
                alturaMayor = alturaDelHijoActual;
        }
        return alturaMayor + 1;
    }

    @Override
    public int nivel() {
        return nivel(this.raiz);
    }

    private int nivel(NodoMVias<K, V> nodoActual) {
        if (this.esArbolVacio())
            return 0;
        return this.altura() - 1;
    }


    @Override
    public void vaciar() {
        this.raiz = NodoMVias.nodoVacio();
    }

    @Override
    public boolean esArbolVacio() {
        return NodoMVias.esNodoVacio(this.raiz);
    }

    @Override
    public boolean contiene(K clave) {
        return (buscar(clave) != null);
    }

    @Override
    public List<K> recorridoPorNiveles() {
        List<K> recorrido = new ArrayList<>();
        if (!this.esArbolVacio()) {
            Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();
            colaDeNodos.add(this.raiz);
            while (!colaDeNodos.isEmpty()) {
                NodoMVias<K, V> nodoActual = colaDeNodos.poll();
                for (int i = 0; i < nodoActual.nroDeClaveNoVacias(); i++) {
                    recorrido.add(nodoActual.getClave(i));
                    if (!nodoActual.esHijoVacio(i))
                        colaDeNodos.add(nodoActual.getHijo(i));
                }
                if (!nodoActual.esHijoVacio(nodoActual.nroDeClaveNoVacias()))
                    colaDeNodos.add(nodoActual.getHijo(nodoActual.nroDeClaveNoVacias()));
            }
        }
        return recorrido;
    }

    @Override
    public List<K> recorridoPostOrden() {
        List<K> recorrido = new ArrayList<>();
        recorridoPostOrdenRec(this.raiz, recorrido);
        return recorrido;
    }

    private void recorridoPostOrdenRec(NodoMVias<K, V> nodoActual, List<K> recorrido) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
        } else {
            recorridoPostOrdenRec(nodoActual.getHijo(0), recorrido);
            for (int i = 0; i < nodoActual.nroDeClaveNoVacias(); i++) {
                recorridoPostOrdenRec(nodoActual.getHijo(i + 1), recorrido);
                recorrido.add(nodoActual.getClave(i));
            }
        }
    }

    @Override
    public List<K> recorridoPreOrden() {
        List<K> recorrido = new ArrayList<>();
        recorridoPreOrdenRec(this.raiz, recorrido);
        return recorrido;
    }

    private void recorridoPreOrdenRec(NodoMVias<K, V> nodoActual, List<K> recorrido) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
        } else {
            for (int i = 0; i < nodoActual.nroDeClaveNoVacias(); i++) {
                recorrido.add(nodoActual.getClave(i));
                recorridoPreOrdenRec(nodoActual.getHijo(i), recorrido);
            }
            recorridoPreOrdenRec(nodoActual.getHijo(nodoActual.nroDeClaveNoVacias()), recorrido);
        }
    }


    @Override
    public List<K> recorridoInOrden() {
        return null;
    }



    @Override
    public List<K> recorridoInOrdenRec() {
            List<K> recorrido = new ArrayList<>();
            recorridoInOrdenRec(this.raiz, recorrido);
            return recorrido;
        }

    private void recorridoInOrdenRec(NodoMVias<K, V> nodoActual, List<K> recorrido) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
        } else {
            for (int i = 0; i < nodoActual.nroDeClaveNoVacias(); i++) {
                recorridoInOrdenRec(nodoActual.getHijo(i), recorrido);
                recorrido.add(nodoActual.getClave(i));
            }
            recorridoInOrdenRec(nodoActual.getHijo(nodoActual.nroDeClaveNoVacias()), recorrido);
        }
    }

    @Override
    public V buscar(K claveBuscar) {
        if (claveBuscar == null)
            throw new IllegalArgumentException("Clave a buscar no puede ser NULA");
        if (!this.esArbolVacio()) {
            NodoMVias<K,V> nodoAux = this.raiz;
            do {
                boolean cambiarNodoAuxiliar = false;
                for (int i = 0; i < nodoAux.nroDeClaveNoVacias() && !cambiarNodoAuxiliar; i++) {
                    K claveAux = nodoAux.getClave(i);
                    if (claveBuscar.compareTo(claveAux) == 0)
                        return nodoAux.getValor(i);
                    else if (claveBuscar.compareTo(claveAux) < 0) {
                        nodoAux = nodoAux.getHijo(i);
                        cambiarNodoAuxiliar = true;
                    }
                }
                if (!cambiarNodoAuxiliar)
                    nodoAux = nodoAux.getHijo(nodoAux.nroDeClaveNoVacias());
            } while (!NodoMVias.esNodoVacio(nodoAux));
        }
        return null;
    }


    public void validar(K claveInsertar, V valorInsertar) {
        if (claveInsertar == null)
            throw new IllegalArgumentException("Clave a Insertar No Puede Ser Nula");
        if (valorInsertar == null)
            throw new IllegalArgumentException("Valor a Insertar No Puede Ser Nula");
    }

    @Override
    public void insertar(K claveInsertar, V valorInsertar) throws DatoYaExisteException {
        validar(claveInsertar, valorInsertar);
        if (this.esArbolVacio()) {
            this.raiz = new NodoMVias<>(this.orden, claveInsertar, valorInsertar);
            return;
        }
        NodoMVias<K, V> nodoActual = this.raiz;
        while (!NodoMVias.esNodoVacio(nodoActual)) {
            int posicionDeClaveAInsertar = this.buscarPosicionDeClave(claveInsertar, nodoActual);
            if (posicionDeClaveAInsertar != ArbolMVias.POSICION_INVALIDA) {
                nodoActual.setValor(posicionDeClaveAInsertar, valorInsertar);
                nodoActual = NodoMVias.nodoVacio();
            } else if (nodoActual.esHoja()) {
                if (nodoActual.estanClavesLlenas()) {
                    int posicionPorDondeBajar = this.buscarPosicionPorDondeBajar(nodoActual, claveInsertar);
                    NodoMVias<K, V> nuevoNodo = new NodoMVias<>(this.orden, claveInsertar, valorInsertar);
                    nodoActual.setHijo(posicionPorDondeBajar, nuevoNodo);
                }else
                    insertarClaveYValorOrdenado(nodoActual, claveInsertar, valorInsertar);
                nodoActual = NodoMVias.nodoVacio();
            } else {    //  NO ES HOJA
                int posicionPorDondeBajar = this.buscarPosicionPorDondeBajar(nodoActual, claveInsertar);
                if (nodoActual.esHijoVacio(posicionPorDondeBajar)) {
                    NodoMVias<K, V> nuevoNodo = new NodoMVias<>(this.orden, claveInsertar, valorInsertar);
                    nodoActual.setHijo(posicionPorDondeBajar, nuevoNodo);
                    nodoActual = NodoMVias.nodoVacio();
                } else
                    nodoActual = nodoActual.getHijo(posicionPorDondeBajar);
            }
        }
    }

    protected void insertarClaveYValorOrdenado(NodoMVias<K,V> nodoActual, K claveInsertar, V valorInsertar) {
        int posicion = 0;
        while (posicion < nodoActual.nroDeClaveNoVacias() && claveInsertar.compareTo(nodoActual.getClave(posicion)) > 0)
            posicion++;
        for (int i = nodoActual.nroDeClaveNoVacias() - 1; i >= posicion; i--) {
            nodoActual.setClave(i + 1, nodoActual.getClave(i));
            nodoActual.setValor(i + 1, nodoActual.getValor(i));
        }
        nodoActual.setClave(posicion, claveInsertar);
        nodoActual.setValor(posicion, valorInsertar);
    }


    protected int buscarPosicionPorDondeBajar(NodoMVias<K,V> nodoActual, K claveInsertar) {
        int i = 0;
        boolean fin = false;
        while (i < nodoActual.nroDeClaveNoVacias()) {
            K claveActual = nodoActual.getClave(i);
            if (claveActual.compareTo(claveInsertar) < 0)
                i++;
            else
                break;
            if (nodoActual.getClave(nodoActual.nroDeClaveNoVacias() - 1).compareTo(claveInsertar) < 0)
                return nodoActual.nroDeClaveNoVacias();
        }
        return i;
    }

    protected int buscarPosicionDeClave(K claveInsertar, NodoMVias<K,V> nodoActual) {
        for (int i = 0; i < nodoActual.nroDeClaveNoVacias(); i++) {
            K claveActual = nodoActual.getClave(i);
            if (claveActual.compareTo(claveInsertar) == 0)
                return i;
        }
        return - 1;
    }


    @Override
    public V eliminar(K claveEliminar) throws ClaveNoExisteException {
        if (claveEliminar == null)
            throw new ClaveNoExisteException("Clave No Puede Ser Nula");
        V valorAELiminar = this.buscar(claveEliminar);
        if (valorAELiminar == null)
            throw new ClaveNoExisteException();
        this.raiz = eliminar(this.raiz, claveEliminar);
        return valorAELiminar;
    }

    private NodoMVias<K, V> eliminar(NodoMVias<K, V> nodoActual, K claveEliminar) {
        if (NodoMVias.esNodoVacio(nodoActual))
            return NodoMVias.nodoVacio();
        for (int i = 0; i < nodoActual.nroDeClaveNoVacias(); i++) {
            K claveEnTurno = nodoActual.getClave(i);
            if (claveEliminar.compareTo(claveEnTurno) == 0) {
                if (nodoActual.esHoja()) {
                    this.eliminarClaveDeNodoEnPosicion(nodoActual, i);
                    if (nodoActual.nroDeClaveNoVacias() == 0)
                        return NodoMVias.nodoVacio();
                    return nodoActual;
                }
                K claveDeReemplazo;
                if (this.hayHijosMasAdelante(nodoActual, i))
                    claveDeReemplazo = this.obtenerSucesorInOrden(nodoActual, claveEliminar);
                else
                    claveDeReemplazo = this.obtenerPredecesorInOrden(nodoActual, claveEliminar);
                V valorDelReemplazo = this.buscar(claveDeReemplazo);
                eliminarClaveDeNodoEnPosicion(nodoActual, i);
                nodoActual.setClave(i, claveDeReemplazo);
                nodoActual.setValor(i, valorDelReemplazo);
                NodoMVias<K, V> nuevoHijo = eliminar(nodoActual.getHijo(i + 1), claveDeReemplazo);
                nodoActual.setHijo(i + 1, nuevoHijo);
                return nodoActual;
            }
            if (claveEliminar.compareTo(claveEnTurno) < 0) {
                NodoMVias<K, V> supuestoNuevoHijo = this.eliminar(nodoActual.getHijo(i), claveEliminar);
                nodoActual.setHijo(i, supuestoNuevoHijo);
                return nodoActual;
            }
        }
        NodoMVias<K, V> supuestoNuevoHijo = this.eliminar(nodoActual.getHijo(nodoActual.nroDeClaveNoVacias()), claveEliminar);
        nodoActual.setHijo(nodoActual.nroDeClaveNoVacias(), supuestoNuevoHijo);
        return nodoActual;
    }

    private K obtenerPredecesorInOrden(NodoMVias<K,V> nodoActual, K claveEliminar) {
        NodoMVias<K, V> nodoPredecedor = nodoActual.getHijo(0);
        for (int i = 0; i < nodoActual.nroDeClaveNoVacias(); i++) {
            if (claveEliminar.compareTo(nodoActual.getClave(i)) == 0)
                return nodoPredecedor.getClave(nodoPredecedor.nroDeClaveNoVacias() - 1);
            if (claveEliminar.compareTo(nodoActual.getClave(i)) < 0)
                return nodoPredecedor.getClave(nodoPredecedor.nroDeClaveNoVacias() - 1);
            nodoPredecedor = nodoActual.getHijo(i + 1);
        }
        return null;
    }


    private K obtenerSucesorInOrden(NodoMVias<K,V> nodoActual, K claveEliminar) {
        int posicion = this.buscarPosicionPorDondeBajar(nodoActual, claveEliminar) + 1;
        K claveDeRetorno = (K)NodoMVias.datoVacio();
        NodoMVias<K, V> nodoAuxiliar = nodoActual.getHijo(posicion);
        while (!NodoMVias.esNodoVacio(nodoAuxiliar)) {
            claveDeRetorno = nodoAuxiliar.getClave(nodoAuxiliar.nroDeClaveNoVacias() - 1);
            nodoAuxiliar = nodoAuxiliar.getHijo(0);
        }
        return claveDeRetorno;
    }

    protected void eliminarClaveDeNodoEnPosicion(NodoMVias<K,V> nodoActual, int posicion) {
        if (nodoActual.nroDeClaveNoVacias() == 1) {
            nodoActual.setClave(posicion, null);;
            nodoActual.setValor(posicion, null);
        } else {
            for (int i = posicion; i < nodoActual.nroDeClaveNoVacias() - 1; i++) {
                nodoActual.setClave(i, nodoActual.getClave(i + 1));
                nodoActual.setValor(i, nodoActual.getValor(i + 1));
                nodoActual.setHijo(i + 1, nodoActual.getHijo(i + 2));
            }
        }
    }

    private boolean hayHijosMasAdelante(NodoMVias<K,V> nodoActual, int posicion) {
        for (int i = posicion + 1; i <= nodoActual.nroDeClaveNoVacias(); i++) {
            if (!nodoActual.esHijoVacio(i))
                return true;
        }
        return false;
    }

    //  cantidad de nodos no Vacias
    public int cantidadNodosConHijosNoVaciosPares() {
        return cantidadNodosConHijosNoVaciosPares(this.raiz);
    }

    private int cantidadNodosConHijosNoVaciosPares(NodoMVias<K, V> nodoActual) {
        if (NodoMVias.esNodoVacio(nodoActual))
            return 0;
        int cantidadNodosConHijosPares = 0;
        int cantidad = 0;
        for (int i = 0; i <= nodoActual.nroDeClaveNoVacias(); i++) {
            if (!NodoMVias.esNodoVacio(nodoActual.getHijo(i))) {
                cantidadNodosConHijosPares += cantidadNodosConHijosNoVaciosPares(nodoActual.getHijo(i));
                cantidad++;
            }
        }
        if ((cantidad % 2 == 0) && (cantidad != 0))
            cantidadNodosConHijosPares++;
        return cantidadNodosConHijosPares;
    }

    public void mostrarRecorrido(List<K> lista) {
        for (int i = 0; i < lista.size(); i++) {
            System.out.print(lista.get(i) + " ");
        }
        System.out.println();
    }
}