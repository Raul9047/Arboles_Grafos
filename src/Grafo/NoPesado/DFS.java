package Grafo.NoPesado;

import Grafo.Utileria.UtilRecorrido;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFS {
    private final Grafo elGrafo;
    private final UtilRecorrido controlMarcados;
    private final int verticePartida;
    private List<Integer> recorrido;

    public DFS(Grafo grafoBase, int posPartida) {
        grafoBase.validarVertice(posPartida);
        verticePartida = posPartida;
        recorrido = new ArrayList<>();
        elGrafo = grafoBase;
        controlMarcados = new UtilRecorrido(elGrafo.cantidadDeVertices());
        ejercutarDFS(posPartida);
    }

    public void ejercutarDFS(int posVertice) {
        Stack<Integer> pilaVertice = new Stack<>();
        pilaVertice.push(posVertice);
        while (!pilaVertice.isEmpty()) {
            int verticeActual = pilaVertice.pop();
            if (!controlMarcados.estaVerticeMarcado(verticeActual)) {
                controlMarcados.marcarVertice(verticeActual);
                recorrido.add(verticeActual);
                Iterable<Integer> adyacentes = elGrafo.adyacenteDeVertice(verticeActual);
                for (Integer adyacente : adyacentes) {
                    if (!controlMarcados.estaVerticeMarcado(adyacente))
                        pilaVertice.push(adyacente);
                }
            }
        }
    }


}