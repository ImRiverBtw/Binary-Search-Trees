package main.java.binarySearchTree;

public class BinarySearchTree<T extends Comparable<T>> extends Node<T> {
    private Node<T> root;

    public BinarySearchTree(T value) {
        super(value);
        this.root = this;
    }

    public void insert(T value) {
        root = insertRecursive(root, value);
    }

    public boolean contains(T value) {
        return containsRecursive(root, value);
    }

    public T findMinValue() {
        return  findMinValueRecursive(root);
    }

    public T findMaxValue() {
        return findMaxValueRecursive(root);
    }

    public int getSize(){
        return getSizeRecursive(root);
    }

    public int getDepth(){
        return getDepthRecursive(root);
    }

    public void inOrderPrint(){
        inOrderRecursive(root);
    }

    public void inOrderRecursive(Node<T> x){
        if (x == null) return;
        inOrderRecursive(x.left);
        System.out.print(x.value + ", ");
        inOrderRecursive(x.right);
    }

    public void inOrderReversePrint(){
        inOrderReverseRecursive(root);
    }

    public void inOrderReverseRecursive(Node<T> x){
        if (x == null) return;
        inOrderReverseRecursive(x.right);
        System.out.print(x.value + ", ");
        inOrderReverseRecursive(x.left);
    }

    private Node<T> insertRecursive(Node<T> node, T value) {
        if (node == null) {
            return new Node<>(value);
        }
        if (value.compareTo(node.value) < 0) {
            node.left = insertRecursive(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            node.right = insertRecursive(node.right, value);
        }
        return node;
    }

    private boolean containsRecursive(Node<T> node, T value) {
        if (node == null) {
            return false;
        }

        // Check if the current node's value equals the target value
        if (node.value.equals(value)) {
            return true;
        }

        // Recursively search both left and right subtrees
        return containsRecursive(node.left, value) || containsRecursive(node.right, value);
    }

    private void buildBalancedRecursive(T[] array, int start, int end) {
        if (start > end) {
            return;
        }

        // Find the midpoint
        int mid = start + (end - start) / 2;

        // Insert the midpoint value into the BST
        insert(array[mid]);

        // Recursively build the left and right subtrees
        buildBalancedRecursive(array, start, mid - 1);
        buildBalancedRecursive(array, mid + 1, end);
    }



    private T findMinValueRecursive(Node<T> node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node.value;
        }
        return findMinValueRecursive(node.left);
    }

    private T findMaxValueRecursive(Node<T> node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node.value;
        }
        return findMaxValueRecursive(node.right);
    }

    private int getSizeRecursive(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + getSizeRecursive(node.left) + getSizeRecursive(node.right);
    }

    private int getDepthRecursive(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getDepthRecursive(node.left), getDepthRecursive(node.right));
    }
}
