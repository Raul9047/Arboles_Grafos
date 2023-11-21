package Arbol.ABB;

import Arbol.MVias.NodoMVias;
import Excepciones.ClaveNoExisteException;
import Excepciones.DatoYaExisteException;

import java.util.*;

public class ABB <K extends Comparable<K>, V> implements InterfaceABB<K, V> {
    protected NodoBinario raiz;

    @Override
    public boolean esVacio() {
        return NodoBinario.esNodoVacio(raiz);
    }

    @Override
    public int size() {
        int nroNodos = 0;
        if (!this.esArbolVacio()) {
            Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            while (!colaDeNodos.isEmpty()) {
                NodoBinario<K, V> nodoActual = colaDeNodos.poll();
                nroNodos++;
                if (!nodoActual.esVacioHijoIzquierdo())
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                if (!nodoActual.esVacioHijoDerecho())
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
            }
        }
        return nroNodos;
    }

    @Override
    public int altura() {
        return altura(this.raiz);
    }

    public int altura(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual))
            return 0;
        int nroNodoIzq = altura(nodoActual.getHijoIzquierdo());
        int nroNodoDer = altura(nodoActual.getHijoDerecho());
        //return (nroNodoIzq > nroNodoDer) ? nroNodoIzq : nroNodoDer;
        return Math.max(nroNodoIzq, nroNodoDer);
    }


    @Override
    public int nivel() {
        return nivel(this.raiz);
    }

    private int nivel(NodoBinario<K, V> nodoActual) {
        if (NodoBinario.esNodoVacio(this.raiz))
            return 0;
        return this.altura() - 1;
    }


    @Override
    public void vaciar() {
        this.raiz = NodoBinario.nodoVacio();
    }

    @Override
    public boolean esArbolVacio() {
        return NodoBinario.esNodoVacio(raiz);
    }

    @Override
    public List<K> recorridoPorNiveles() {
        List<K> recorrido = new ArrayList<>();
        if (!this.esArbolVacio()) {
            Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
            colaDeNodos.add(this.raiz);
            while (!colaDeNodos.isEmpty()) {
                NodoBinario<K, V> nodoActual = colaDeNodos.poll();
                recorrido.add(nodoActual.getClave());
                if (!nodoActual.esVacioHijoIzquierdo())
                    colaDeNodos.add(nodoActual.getHijoIzquierdo());
                if (!nodoActual.esVacioHijoDerecho())
                    colaDeNodos.add(nodoActual.getHijoDerecho());
            }
        }
        return recorrido;
    }

    public List<K> recorridoNivelesRec() {
        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.add(this.raiz);
        List<K> nodos = new ArrayList<>();
        recorridoPorNivelesRec(colaDeNodos, nodos);
        return nodos;
    }

    private void recorridoPorNivelesRec(Queue<NodoBinario<K, V>> cola, List<K> nodos) {
        if (cola.isEmpty())
            return;
        NodoBinario<K, V> nodoActual = cola.poll();
        nodos.add(nodoActual.getClave());
        if (!nodoActual.esVacioHijoIzquierdo())
            cola.add(nodoActual.getHijoIzquierdo());
        if (!nodoActual.esVacioHijoDerecho())
            cola.add(nodoActual.getHijoDerecho());
        recorridoPorNivelesRec(cola, nodos);
    }




    @Override
    public List<K> recorridoPostOrden() {
        List<K> recorrido = new ArrayList<>();
        if (this.esArbolVacio())
            return recorrido;

        Stack<NodoBinario<K, V>> pila1 = new Stack<>();
        Stack<NodoBinario<K, V>> pila2 = new Stack<>();
        pila1.push(this.raiz);
        while (!pila1.isEmpty()) {
            NodoBinario<K, V> nodoActual = pila1.pop();
            pila2.push(nodoActual);
            if (!nodoActual.esVacioHijoIzquierdo())
                pila1.push(nodoActual.getHijoIzquierdo());
            if (!nodoActual.esVacioHijoDerecho())
                pila1.push(nodoActual.getHijoDerecho());
        }
        while (!pila2.isEmpty())
            recorrido.add(pila2.pop().getClave());
        return recorrido;
    }


    public List<K> recorridoPostOrdenRec() {
        List<K> recorrido = new ArrayList<>();
        recorridoPostOrdeN(this.raiz, recorrido);
        return recorrido;
    }

    private void recorridoPostOrdeN(NodoBinario<K, V> nodoActual, List<K> recorrido) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
        } else {
            recorridoPostOrdeN(nodoActual.getHijoIzquierdo(), recorrido);
            recorridoPostOrdeN(nodoActual.getHijoDerecho(), recorrido);

            recorrido.add(nodoActual.getClave());
        }
    }


    @Override
    public List<K> recorridoPreOrden(){
        List<K> recorrido = new ArrayList<>();
        if (this.esArbolVacio())
            return recorrido;
        Stack<NodoBinario<K, V>> pilaDeNodos = new Stack<>();
        pilaDeNodos.push(raiz);
        while (!pilaDeNodos.isEmpty()) {
            NodoBinario<K, V> nodoActual = pilaDeNodos.pop();
            recorrido.add(nodoActual.getClave());
            if (!nodoActual.esVacioHijoDerecho())
                pilaDeNodos.push(nodoActual.getHijoDerecho());
            if (!nodoActual.esVacioHijoIzquierdo())
                pilaDeNodos.push(nodoActual.getHijoIzquierdo());
        }
        return recorrido;
    }


    public List<K> recorridoPreOrdenRec() {
        List<K> recorrido = new ArrayList<>();
        recorridoPreOrdenRec(this.raiz, recorrido);
        return recorrido;
    }

    private void recorridoPreOrdenRec(NodoBinario<K, V> nodoActual, List<K> recorrido) {
        if (NodoBinario.esNodoVacio(nodoActual)) {

        } else {
            recorrido.add(nodoActual.getClave());
            recorridoPreOrdenRec(nodoActual.getHijoIzquierdo(), recorrido);
            recorridoPreOrdenRec(nodoActual.getHijoDerecho(), recorrido);
        }
    }


    @Override
    public List<K> recorridoInOrden() {
        List<K> recorrido = new ArrayList<>();
        if (!this.esArbolVacio()) {
            Stack<NodoBinario<K, V>> pilaDeNodos = new Stack();
            this.apilarParaInOrden(pilaDeNodos, this.raiz);
            while (!pilaDeNodos.isEmpty()) {
                NodoBinario<K, V> nodoActual = pilaDeNodos.pop();
                recorrido.add(nodoActual.getClave());
                this.apilarParaInOrden(pilaDeNodos, nodoActual.getHijoDerecho());
            }
        }
        return recorrido;
    }

    private void apilarParaInOrden(Stack<NodoBinario<K, V>> pilaDeNodos, NodoBinario<K, V> nodoActual) {
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            pilaDeNodos.push(nodoActual);
            nodoActual = nodoActual.getHijoIzquierdo();
        }
    }

    @Override
    public List<K> recorridoInOrdenRec() {
        List<K> recorrido = new ArrayList<>();
        recorridoPostOrdeN(this.raiz, recorrido);
        return recorrido;
    }

    private void recorridoInOrdeN(NodoBinario<K, V> nodoActual, List<K> recorrido) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
        } else {
            recorridoInOrdeN(nodoActual.getHijoIzquierdo(), recorrido);
            recorrido.add(nodoActual.getClave());
            recorridoInOrdeN(nodoActual.getHijoDerecho(), recorrido);
        }
    }



    @Override
    public V buscar(K claveBuscar) {
        if (claveBuscar == null)
            throw new IllegalArgumentException("Clave a buscar no puede ser NULA");
        if (!this.esArbolVacio()) {
            NodoBinario<K,V> nodoAux = this.raiz;
            do {
                K claveAux = nodoAux.getClave();
                if (claveBuscar.compareTo(claveAux) < 0)
                    nodoAux = nodoAux.getHijoIzquierdo();
                else if (claveBuscar.compareTo(claveAux) > 0)
                    nodoAux = nodoAux.getHijoDerecho();
                else
                    return nodoAux.getValor();
            } while (!NodoBinario.esNodoVacio(nodoAux));
        }
        return null;
    }

    public V buscarRec(K clave) {
        return (V) buscarRec(raiz, clave);
    }

    private V buscarRec(NodoBinario<K, V> nodo, K clave) {
        if (NodoBinario.esNodoVacio(nodo) || nodo.getClave().equals(clave))
            return (!NodoBinario.esNodoVacio(nodo)) ? nodo.getValor() : null;
        if (clave.compareTo(nodo.getClave()) < 0)
            return buscarRec(nodo.getHijoIzquierdo(), clave);
        else
            return buscarRec(nodo.getHijoDerecho(), clave);
    }

    @Override
    public boolean contiene(K clave) {
        return (buscar(clave) != null);
    }

    public void validar(K claveInsertar, V valorInsertar) {
        if (claveInsertar == null)
            throw new IllegalArgumentException("Clave a Insertar No Puede Ser Nula");
        if (valorInsertar == null)
            throw new IllegalArgumentException("Valor a Insertar No Puede Ser Nula");
    }

    @Override
    public void insertar(K claveInsertar, V valorInsertar) throws DatoYaExisteException {
        this.validar(claveInsertar, valorInsertar);
        if (this.esArbolVacio())
            this.raiz = new NodoBinario(claveInsertar, valorInsertar);
        else {
            NodoBinario<K, V> anterior = NodoBinario.nodoVacio();
            NodoBinario<K, V> siguiente = this.raiz;
            while (!NodoBinario.esNodoVacio(siguiente)) {
                K claveActual = siguiente.getClave();
                if (claveInsertar.compareTo(claveActual) < 0) {
                    anterior = siguiente;
                    siguiente = siguiente.getHijoIzquierdo();
                } else {
                    if (claveInsertar.compareTo(claveActual) > 0) {
                        anterior = siguiente;
                        siguiente = siguiente.getHijoDerecho();
                    } else {
                        siguiente.setValor(valorInsertar);
                        throw new DatoYaExisteException();
                    }
                }
            }
            NodoBinario<K, V> nuevoNodo = new NodoBinario<>(claveInsertar, valorInsertar);
            K claveAnterior = anterior.getClave();
            if (claveInsertar.compareTo(claveAnterior) < 0)
                anterior.setHijoIzquierdo(nuevoNodo);
            else
                anterior.setHijoDerecho(nuevoNodo);
        }
    }

    @Override
    public V eliminar(K clave) throws ClaveNoExisteException {
        NodoBinario<K, V> nodoActual = this.raiz;
        NodoBinario<K, V> padreNodoActual = NodoBinario.nodoVacio();
        boolean esHijoIzquierdo = false;
        //  Busca el nodo a eliminar y su padre
        while (!NodoBinario.esNodoVacio(nodoActual) && !nodoActual.getClave().equals(clave)) {
            padreNodoActual = nodoActual;
            if (clave.compareTo(nodoActual.getClave()) < 0) {
                nodoActual = nodoActual.getHijoIzquierdo();
                esHijoIzquierdo = true;
            }
            else {
                nodoActual = nodoActual.getHijoDerecho();
                esHijoIzquierdo = false;
            }
        }
        //  Si el nodo no existe, sale del metodo
        if (NodoBinario.esNodoVacio(nodoActual))
            return null;
        //  Caso 1: Si el elemento a borrar es HOJA
        if (nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()) {
            if (nodoActual == raiz)
                raiz = NodoBinario.nodoVacio();
            else if (esHijoIzquierdo)
                padreNodoActual.setHijoIzquierdo(NodoBinario.nodoVacio());
            else
                padreNodoActual.setHijoDerecho(NodoBinario.nodoVacio());
        }
        //  Caso 2: Nodo Con un solo HIJO
        else if (nodoActual.esVacioHijoIzquierdo())
            reemplazarNodo(padreNodoActual, nodoActual, nodoActual.getHijoDerecho(), esHijoIzquierdo);
        else if (nodoActual.esVacioHijoDerecho())
            reemplazarNodo(padreNodoActual, nodoActual, nodoActual.getHijoIzquierdo(), esHijoIzquierdo);
        //  Caso 3: Nodo con 2 HIJOS
        else {
            NodoBinario<K, V> sucesor = encontrarMinimo(nodoActual.getHijoDerecho());
            reemplazarNodo(padreNodoActual, nodoActual, sucesor, esHijoIzquierdo);
            sucesor.setHijoDerecho(nodoActual.getHijoDerecho());
            sucesor.setHijoIzquierdo(nodoActual.getHijoIzquierdo());
        }
        return nodoActual.getValor();
    }

    private void reemplazarNodo(NodoBinario<K, V> padre, NodoBinario<K, V> nodoAntiguo, NodoBinario<K, V> nodoNuevo, boolean esHijoIzquierdo) {
        if (NodoBinario.esNodoVacio(padre))
            raiz = nodoNuevo;
        else if (esHijoIzquierdo)
            padre.setHijoIzquierdo(nodoNuevo);
        else
            padre.setHijoDerecho(nodoNuevo);
    }

    private NodoBinario<K, V> encontrarMinimo(NodoBinario<K, V> nodo) {
        while (!nodo.esVacioHijoIzquierdo())
            nodo = nodo.getHijoIzquierdo();
        return nodo;
    }


    public void eliminarRec(K clave) throws ClaveNoExisteException {
        raiz = eliminarRec(this.raiz, clave);
    }

    private NodoBinario<K, V> eliminarRec(NodoBinario<K, V> nodo, K clave) {
        if (NodoBinario.esNodoVacio(nodo))
            return nodo;
        if (clave.compareTo(nodo.getClave()) < 0)
            nodo.setHijoIzquierdo(eliminarRec(nodo.getHijoIzquierdo(), clave));
        else if (clave.compareTo(nodo.getClave()) > 0)
            nodo.setHijoDerecho(eliminarRec(nodo.getHijoDerecho(), clave));
        else {
            //  caso 1
            if (nodo.esVacioHijoIzquierdo())
                return nodo.getHijoDerecho();
            else if (nodo.esVacioHijoDerecho())
                return nodo.getHijoDerecho();
            //  nodo con AMBOS hijos
            NodoBinario<K, V> sucesor = encontrarMinimo(nodo.getHijoDerecho());
            nodo.setClave(sucesor.getClave());
            nodo.setValor(sucesor.getValor());
            nodo.setHijoDerecho(eliminarRec(nodo.getHijoDerecho(), sucesor.getClave()));
        }
        return nodo;
    }





    public void insertarRec(K claveInsertar, V valorInsertar) {
        raiz = insertarRec(raiz, claveInsertar, valorInsertar);
    }

    private NodoBinario<K, V> insertarRec(NodoBinario<K, V> nodo, K clave, V valor) {
        if (NodoBinario.esNodoVacio(nodo))
            return new NodoBinario<>(clave, valor);
        if (clave.compareTo(nodo.getClave()) < 0)
            nodo.setHijoIzquierdo(insertarRec(nodo.getHijoIzquierdo(), clave, valor));
        else if (clave.compareTo(nodo.getClave()) > 0)
            nodo.setHijoDerecho(insertarRec(nodo.getHijoDerecho(), clave, valor));
        return nodo;
    }


    public int cantidadNodo() {
        if (this.esArbolVacio())
            return 0;
        int cantidad = 0;
        Queue<NodoBinario<K, V>> colaNodos = new LinkedList<>();
        colaNodos.offer(raiz);
        while (!colaNodos.isEmpty()) {
            NodoBinario<K, V> nodoActual = colaNodos.poll();
            cantidad++;
            if (!nodoActual.esVacioHijoIzquierdo())
                colaNodos.offer(nodoActual.getHijoIzquierdo());
            if (!nodoActual.esVacioHijoDerecho())
                colaNodos.offer(nodoActual.getHijoDerecho());
        }
        return cantidad;
    }

    public int cantNodos() {
        if (this.esArbolVacio())
            return 0;
        Stack<NodoBinario<K, V>> pilaNodo = new Stack<>();
        pilaNodo.push(this.raiz);
        int cantidad = 0;
        while(!pilaNodo.isEmpty()) {
            NodoBinario<K, V> nodoActual = pilaNodo.pop();
            cantidad++;
            if (!nodoActual.esVacioHijoIzquierdo())
                pilaNodo.push(nodoActual.getHijoIzquierdo());
            if (!nodoActual.esVacioHijoDerecho())
                pilaNodo.push(nodoActual.getHijoDerecho());
        }
        return cantidad;
    }

    public int cantNodoRec() {
        return cantNodoRec(this.raiz);
    }

    private int cantNodoRec(NodoBinario<K, V> nodo) {
        if (NodoBinario.esNodoVacio(nodo))
            return 0;
        return 1 + cantNodoRec(nodo.getHijoIzquierdo()) + cantNodoRec(nodo.getHijoDerecho());
    }


    public int contarHijoIzqNoVacio() {
        int contador = 0;
        if (!this.esArbolVacio()) {
            Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
            colaDeNodos.offer(raiz);
            while (!colaDeNodos.isEmpty()) {
                NodoBinario<K, V> nodoActual = colaDeNodos.poll();
                if (!nodoActual.esVacioHijoIzquierdo()) {
                    contador++;
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esVacioHijoDerecho())
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
            }
        }
        return contador;
    }


    public int contarHijoIzqNoVacioRec() {
        return contarHijoIzqNoVacioRec(this.raiz);
    }

    private int contarHijoIzqNoVacioRec(NodoBinario<K, V> nodo) {
        if (NodoBinario.esNodoVacio(nodo))
            return 0;
        int contador = 0;
        if (!nodo.esVacioHijoIzquierdo())
            contador++;
        contador += contarHijoIzqNoVacioRec(nodo.getHijoIzquierdo());
        contador += contarHijoIzqNoVacioRec(nodo.getHijoDerecho());
        return contador;
    }


    public int contarHijosIzquierdoEnNivel(int nivelObjetivo) {
        if (this.esArbolVacio() || nivelObjetivo < 0)
            return 0;
        int cantidad = 0;
        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(raiz);
        int nivelActual = 0;
        while (!colaDeNodos.isEmpty() && nivelActual <= nivelObjetivo) {
            int nodosEnNivel = colaDeNodos.size();
            while (nodosEnNivel > 0) {
                NodoBinario<K ,V> nodoActual = colaDeNodos.poll();
                if (nivelActual == nivelObjetivo && !nodoActual.esVacioHijoIzquierdo())
                    cantidad++;
                if (!nodoActual.esVacioHijoIzquierdo())
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                if (!nodoActual.esVacioHijoDerecho())
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                nodosEnNivel--;
            }
            nivelActual++;
        }
        return cantidad;
    }


    public int contarHijosIzquierdoEnNivelRec(int nivelObjetivo) {
        return contarHijosIzquierdoEnNivelRec(this.raiz, 0, nivelObjetivo);
    }

    private int contarHijosIzquierdoEnNivelRec(NodoBinario<K, V> nodoActual, int nivelActual, int nivelObjetivo) {
        if (NodoBinario.esNodoVacio(nodoActual) || nivelActual > nivelObjetivo)
            return 0;
        if (nivelActual == nivelObjetivo && !nodoActual.esVacioHijoIzquierdo())
            return 1;
        int contadorIzquierdo = contarHijosIzquierdoEnNivelRec(nodoActual.getHijoIzquierdo(), nivelActual + 1, nivelObjetivo);
        int contadorDerecho = contarHijosIzquierdoEnNivelRec(nodoActual.getHijoDerecho(), nivelActual + 1, nivelObjetivo);
        return contadorIzquierdo + contadorDerecho;
    }


    public void mostrarArbol() {
        mostrarArbol(this.raiz, 0);
    }

    private void mostrarArbol(NodoBinario<K, V> nodo, int nivel) {
        if (!NodoBinario.esNodoVacio(nodo)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < nivel; i++)
                sb.append(" ");
            sb.append("|_").append(nodo.getClave()).append(": ").append(nodo.getValor());
            System.out.println(sb.toString());

            mostrarArbol(nodo.getHijoIzquierdo(), nivel + 1);
            mostrarArbol(nodo.getHijoDerecho(), nivel + 1);
        }
    }



    public void mostrarRecorrido(List<K> lista) {
        for (int i = 0; i < lista.size(); i++) {
            System.out.print(lista.get(i) + " ");
        }
    }
}