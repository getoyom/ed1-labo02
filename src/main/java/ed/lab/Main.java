package ed.lab;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        E03AVLTree<Integer> avlTree = new E03AVLTree<>(Integer::compare);// Crea un árbol AVL de números enteros.
        System.out.println( avlTree.search(5));// retorna null porque el árbol está vacío
        avlTree.insert(5); // almacena 5 en el árbol AVL
        avlTree.insert(3); // almacena 3 en el árbol AVL
        avlTree.insert(1); // almacena 1 en el árbol AVL y lo rebalanced
        System.out.println(avlTree.search(5)); // retorna 5
        System.out.println(avlTree.search(1)); // retorna 1
        System.out.println(avlTree.size()); // retorna 3
        System.out.println(avlTree.height()); // retorna 2
        avlTree.traverse();
        avlTree.delete(3); // elimina 3
        System.out.println(avlTree.search(3)); // retorna null
        avlTree.insert(4); // almacena 4 y rebalanced el árbol AVL
        avlTree.traverse();
    }
}