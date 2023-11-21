package Arbol.ABB;

public class MainAVL {
    public static void main(String[] args) {
        AVL<Integer, String> arbol = new AVL<>();

        arbol.insertarRec(300, "1");
        arbol.insertarRec(200, "11");
        arbol.insertarRec(100, "111");
        arbol.insertarRec(50, "1111");
        arbol.mostrarArbol();
    }
}