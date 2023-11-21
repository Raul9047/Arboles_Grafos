package Grafo.NoPesado;

import Grafo.Utileria.UtilRecorrido;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    private final Grafo elGrafo;
    private final UtilRecorrido controlMarcado;
    private final int verticePartida;
    private List<Integer> recorrido;

    public BFS(Grafo grafoBase, int posPartida) {
        grafoBase.validarVertice(posPartida);
        verticePartida = posPartida;
        recorrido = new ArrayList<>();
        elGrafo = grafoBase;
        controlMarcado = new UtilRecorrido(elGrafo.cantidadDeVertices());
        ejecutarBFS(posPartida);
    }

    public void ejecutarBFS(int posVertice) {
        Queue<Integer> colaVertice = new LinkedList<>();
        colaVertice.offer(posVertice);
        controlMarcado.marcarVertice(posVertice);
        do {
            int posVerticeEnTurno = colaVertice.poll();
            this.recorrido.add(posVerticeEnTurno);
            Iterable<Integer> adyacenteDelVerticeEnTurno = this.elGrafo.adyacenteDeVertice(posVerticeEnTurno);
            for (Integer AdyacenteEnTurno : adyacenteDelVerticeEnTurno) {
                if (!this.controlMarcado.estaVerticeMarcado(AdyacenteEnTurno)) {
                    colaVertice.offer(AdyacenteEnTurno);
                    this.controlMarcado.marcarVertice(AdyacenteEnTurno);
                }
            }
        } while (!colaVertice.isEmpty());
    }

    public Iterable<Integer> obtenerVerticesVisitados() {
        return this.recorrido;
    }

    public boolean hayCaminoVertice(int posVerticeDestino) {
        elGrafo.validarVertice(posVerticeDestino);
        return this.controlMarcado.estaVerticeMarcado(posVerticeDestino);
    }

    public boolean hayCaminoATodos() {
        return controlMarcado.estanTodosMarcados();
    }
}