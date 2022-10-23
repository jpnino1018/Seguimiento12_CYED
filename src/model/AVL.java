package model;

public class AVL{

    private Node<Integer> root;

    public void insert(int key) {
        root=insert(root, key);
    }

    private Node<Integer> insert (Node<Integer> current, int key){
        if (current == null){
            return (new Node<>(key));
        }

        if (key < current.getKey()){
            current.setLeft(insert(current.getLeft(), key));
        }
        else if (key > current.getKey()){
            current.setRight(insert(current.getRight(), key));
        }else {
            return current;
        }

        current.setHeight(1+max(checkHeight(current.getLeft()), checkHeight(current.getRight())));

        int fb = getFb(current);

        if (fb > 1 && key < current.getLeft().getKey()){
            return rightRotate(current);
        }
        if (fb < -1 && key > current.getRight().getKey()){
            return leftRotate(current);
        }
        if (fb > 1 && key > current.getLeft().getKey()){
            current.setLeft(leftRotate(current.getLeft()));
            return rightRotate(current);
        }
        if (fb < -1 && key < current.getRight().getKey()){
            current.setRight(rightRotate(current.getRight()));
            return leftRotate(current);
        }

        return current;
    }

    public void delete(int key){
        root = delete(root, key);
    }

    private Node<Integer> delete(Node<Integer> current, int key){
        if (current == null){
            return current;
        }
        if (key < current.getKey()){
            current.setLeft(delete(current.getLeft(), key));
        }else if(key > current.getKey()){
            current.setRight(delete(current.getRight(), key));
        }else {
            if ((current.getLeft() == null) || (current.getRight() == null)) {
                Node<Integer> temporary = null;
                if (temporary == current.getLeft()) {
                    temporary = current.getRight();
                } else {
                    temporary = current.getLeft();
                }
                if (temporary == null) {
                    current = null;

                } else {
                    current = temporary;
                }
            } else {
                Node<Integer> temporary = maxValueNode(current.getLeft());
                current.setKey(temporary.getKey());
                current.setLeft(delete(current.getLeft(), temporary.getKey()));
            }
        }
        if (current == null) {
            return current;
        }
        current.setHeight(max(checkHeight(current.getLeft()), checkHeight(current.getRight())) + 1);

        int fb = getFb(current);

        if (fb > 1 && getFb(current.getLeft()) >= 0) {
            return rightRotate(current);
        }
        if (fb < -1 && getFb(current.getRight()) <= 0) {
            return leftRotate(current);
        }
        if (fb > 1 && getFb(current.getLeft()) < 0) {
            current.setLeft(leftRotate(current.getLeft()));
            return rightRotate(current);
        }
        if (fb < -1 && getFb(current.getRight()) > 0) {
            current.setRight(rightRotate(current.getRight()));
            return leftRotate(current);
        }
        return current;
    }

    private Node<Integer> maxValueNode(Node<Integer> node){
        Node<Integer> current = node;

        while (current.getRight() != null){
            current = current.getRight();
        }
        return current;
    }
    public int checkHeight(Node<Integer> node){
        if(node == null){
            return 0;
        }else {
            return node.getHeight();
        }
    }

    public int getFb(Node<Integer> node){
        if (node == null){
            return 0;
        }else {
            return checkHeight(node.getLeft())-checkHeight(node.getRight());
        }
    }
    public int max(int a, int b){
        if(a>b){
            return a;
        }else if(a<b){
            return b;
        }else {
            return 0;
        }
    }

    public Node<Integer> rightRotate(Node<Integer> current){
        Node<Integer> newRoot = current.getLeft();
        Node<Integer> temporary = newRoot.getRight();

        newRoot.setRight(current);
        current.setLeft(temporary);

        current.setHeight(max(checkHeight(current.getLeft()), checkHeight(current.getRight()))+1);
        newRoot.setHeight(max(checkHeight(newRoot.getLeft()), checkHeight(newRoot.getRight()))+1);

        return newRoot;
    }

    public Node<Integer> leftRotate(Node<Integer> current){
        Node<Integer> newRoot = current.getRight();
        Node<Integer> temporary = newRoot.getLeft();

        newRoot.setLeft(current);
        current.setRight(temporary);

        current.setHeight(max(checkHeight(current.getLeft()), checkHeight(current.getRight()))+1);
        newRoot.setHeight(max(checkHeight(newRoot.getLeft()), checkHeight(newRoot.getRight()))+1);

        return newRoot;
    }

    public boolean search(int key){
        return search(root, key);
    }

    private boolean search(Node<Integer> current, int key){
        boolean result = false;
        if (current == null){
            //Nada
        }
        else if (key == current.getKey()){
            result = true;
        }
        else if (key < current.getKey()){
            result = search(current.getLeft(), key);
        }
        else if (key > current.getKey()){
            result = search(current.getRight(), key);
        }
        return result;
    }

    public Node<Integer> getRoot(){
        return root;
    }
}