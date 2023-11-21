package Grafo.NoPesado;

import Excepciones.NroVerticesInvalidoException;
import Grafo.Excepciones.AristaNoExisteException;
import Grafo.Excepciones.AristaYaExisteException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grafo {
    protected List< List<Integer> > listaDeAdyacente;

    public Grafo() {
        listaDeAdyacente = new ArrayList<>();
    }

    public Grafo(int nroVertices) throws NroVerticesInvalidoException {
        if (nroVertices <= 0)
            throw new NroVerticesInvalidoException();
        listaDeAdyacente = new ArrayList<>();
        for (int i = 0; i < nroVertices; i++)
            this.insertarVertice();
    }

    public void insertarVertice() {
        List<Integer> adyacenteDelNuevoVertice = new ArrayList<>();
        this.listaDeAdyacente.add(adyacenteDelNuevoVertice);
    }

    public void validarVertice(int posicion) {
        if (posicion < 0 || posicion >= this.listaDeAdyacente.size())
            throw new IllegalArgumentException("Vertices No Valido");
    }

    public boolean existeAdyacencia(int posOrigen, int posDestino) {
        validarVertice(posOrigen);
        validarVertice(posDestino);
        List<Integer> adyacenteDelOrigen = this.listaDeAdyacente.get(posOrigen);
        return adyacenteDelOrigen.contains(posDestino);
    }

    public void insertarArista(int verticeInicial, int verticeFinal) throws AristaYaExisteException {
        this.validarVertice(verticeInicial);
        this.validarVertice(verticeFinal);
        if (this.existeAdyacencia(verticeInicial, verticeFinal))
            throw new AristaYaExisteException();
        List<Integer> listaAdyacente = this.listaDeAdyacente.get(verticeInicial);
        listaAdyacente.add(verticeFinal);
        Collections.sort(listaAdyacente);
        if (verticeFinal != verticeInicial) {
            listaAdyacente = this.listaDeAdyacente.get(verticeFinal);
            listaAdyacente.add(verticeInicial);
            Collections.sort(listaAdyacente);
        }
    }

    public int cantidadDeVertices() {
        return this.listaDeAdyacente.size();
    }

    public int cantidadDeAristas() {
        int cantidadDeArista = 0;
        int cantidadDeLazos = 0;
        for (int i = 0; i < this.listaDeAdyacente.size(); i++) {
            List<Integer> listaAdyacencia = this.listaDeAdyacente.get(i);
            for (Integer ad : listaAdyacencia) {
                if (ad == i)
                    cantidadDeLazos ++;
                else
                    cantidadDeArista ++;
            }
        }
        cantidadDeArista = (cantidadDeArista / 2) + cantidadDeLazos;
        return cantidadDeArista;
    }

    public Iterable<Integer> adyacenteDeVertice(int posVertice) {
        validarVertice(posVertice);
        List<Integer> adyacenteVertice = this.listaDeAdyacente.get(posVertice);
        Iterable<Integer> iterableAdyacenteDeVertice =(Iterable)adyacenteVertice;
        return iterableAdyacenteDeVertice;
    }

    public int gradoDeVertice(int posVertice) {
        validarVertice(posVertice);
        List<Integer> listaAdyacencia = this.listaDeAdyacente.get(posVertice);
        return listaAdyacencia.size();
    }

    public void eliminarArista(int posOrigen, int posDestino) throws AristaNoExisteException {

    }

    public void eliminarVertice(int posVertice) {

    }
}
