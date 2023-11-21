package Grafo.Utileria;

import java.util.ArrayList;
import java.util.List;

public class UtilRecorrido {
    private List<Boolean> listaMarcados;

    public UtilRecorrido(int nroVertices) {
        this.listaMarcados = new ArrayList<>();
        for (int i = 0; i < nroVertices; i++)
            this.listaMarcados.add(Boolean.FALSE);
    }

    public void descarmarTodos() {
        for (int i = 0; i < listaMarcados.size(); i++)
            this.desmarcarVertice(i);
    }

    public void desmarcarVertice(int vertice) {
        this.listaMarcados.set(vertice, Boolean.FALSE);
    }

    public void marcarVertice(int vertice) {
        this.listaMarcados.set(vertice, Boolean.TRUE);
    }

    public boolean estaVerticeMarcado(int vertice) {
        return this.listaMarcados.get(vertice);
    }

    public boolean estanTodosMarcados() {
        for (int i = 0; i < this.listaMarcados.size(); i++) {
            if (!this.estaVerticeMarcado(i))
                return  false;
        }
        return true;
    }
}