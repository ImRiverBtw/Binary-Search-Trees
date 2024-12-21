package main.java.avl;

public class Node<T> {
    T value;
    Node<T> left;
    Node<T> right;
    int height;

    public Node(T value) {
        this.value = value;
        this.height = 1;
    }

}