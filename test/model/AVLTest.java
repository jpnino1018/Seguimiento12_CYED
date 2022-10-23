package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class AVLTest {

    AVL tree;

    public void setup1(){
        tree = new AVL();
    }

    public void setup2(){
        tree = new AVL();
        tree.insert(3);
        tree.insert(2);
        tree.insert(7);
        tree.insert(1);
        tree.insert(12);
    }
    @Test
    public void insertNodesInEmptyAVLTree() {
        setup1();
        tree.insert(1);

        assertTrue(tree.search(1));
    }

    @Test
    public void insertNodesInAVLTree() {
        setup2();
        tree.insert(4);

        assertTrue(tree.search(4));
    }

    @Test
    public void insertNodesInAVLTree2() {
        setup2();
        tree.insert(1);

        assertTrue(tree.search(1));
    }

    @Test
    public void deleteNodeInEmptyAVLTree() {
        setup1();
        tree.delete(4);

        assertNull(tree.getRoot());
    }

    @Test
    public void deletePresentNodeInAVLTree() {
        setup2();
        tree.delete(7);

        assertFalse(tree.search(7));
    }

    @Test
    public void deletePresentNodeInAVLTree2() {
        setup2();
        tree.delete(1);

        assertFalse(tree.search(1));
    }
}