package Grafo.NoPesado;

import Grafo.Excepciones.AristaNoExisteException;
import Grafo.Excepciones.AristaYaExisteException;
import Grafo.Excepciones.NroVerticesInvalidoException;

import java.util.Collections;
import java.util.List;

public class DiGrafo extends Grafo{
    public DiGrafo() {
        super();
    }

    public DiGrafo(int nroVertice) throws Excepciones.NroVerticesInvalidoException {
        super(nroVertice);
    }

    @Override
    public void insertarArista(int posOrigen, int posDestino) throws AristaYaExisteException {
        if (this.existeAdyacencia(posOrigen, posDestino))
            throw new AristaYaExisteException();
        List<Integer> listaAdyacente = this.listaDeAdyacente.get(posOrigen);
        listaAdyacente.add(posDestino);
        Collections.sort(listaAdyacente);
    }

    @Override
    public void eliminarArista(int posOrigen, int posDestino) throws AristaNoExisteException {

    }

    @Override
    public void eliminarVertice(int posVertice) {

    }
}