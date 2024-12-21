package main.java.avl;

public class AVLTree<T extends Comparable<T>> {

    public Node<T> root;


    private void updateHeight(Node<T> n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    private int height(Node<T> n) {
        return n == null ? 0 : n.height;
    }

    public int getBalance(Node<T> n) {
        return (n == null) ? 0 : height(n.right) - height(n.left);
    }

    private Node<T> rotateRight(Node<T> y) {

        Node<T> x = y.left;
        Node<T> z = x.right;

        // Perform rotation
        x.right = y;
        y.left = z;

        // Update heights
        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private Node<T> rotateLeft(Node<T> y) {
        Node<T> x = y.right;
        Node<T> z = x.left;

        // Perform rotation
        x.left = y;
        y.right = z;

        // Update heights
        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private Node<T> rebalance(Node<T> z) {
        updateHeight(z);
        int balance = getBalance(z);

        if (balance > 1) {
            if (height(z.right.right) > height(z.right.left)) {
                z = rotateLeft(z);
            } else {
                z.right = rotateRight(z.right);
                z = rotateLeft(z);
            }
        } else if (balance < -1) {
            if (height(z.left.left) > height(z.left.right))
                z = rotateRight(z);
            else {
                z.left = rotateLeft(z.left);
                z = rotateRight(z);
            }
        }
        return z;
    }

    public void insert(T value) {
        root = insert(root, value);
    }
    private Node<T> insert(Node<T> root, T value) {
        if (root == null) {
            return new Node<>(value);
        }
        int compare = root.value.compareTo(value);
     if (compare < 0) {
            root.left = insert(root.left, value);
        } else if (compare > 0) {
            root.right = insert(root.right, value);
        } else {
         return root;
        }
        return rebalance(root);
    }
    public void inOrderReversePrint(){
        inOrderReverseRecursive(root);
    }

    private void inOrderReverseRecursive(Node<T> x){
        if (x == null) return;
        inOrderReverseRecursive(x.right);
        System.out.print(x.value + ", ");
        inOrderReverseRecursive(x.left);
    }

    public int getSize(){
        return getSizeRecursive(root);
    }

    public int getDepth(){
        return getDepthRecursive(root);
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