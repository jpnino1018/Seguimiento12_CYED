package main;

import model.AVL;

public class Main {

    public static void main(String[] args) {

        AVL tree = new AVL();

        tree.insert(1);
        tree.insert(7);
        tree.insert(15);
        tree.insert(3);
        tree.insert(4);
        tree.insert(10);

        tree.delete(1);
        tree.delete(15);

    }
}
