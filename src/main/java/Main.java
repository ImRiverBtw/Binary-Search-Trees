package main.java;

import main.java.avl.AVLTree;
import main.java.binarySearchTree.BinarySearchTree;
import main.java.models.VoteEntry;


import java.util.List;

public class Main {
    static List<VoteEntry> voteEntries;
    static String directoryPath = "src/xml";
    static XMLParser parser = new XMLParser();
    static BinarySearchTree<VoteEntry> balancedTree;


    public static void main(String[] args) {
         // parse the xml
        voteEntries = parser.parseVoteEntries(directoryPath);

        // Print out the results
        AVLTest();
//        bstTest();
//        bstTestBalanced();
//        bstTestUnbalanced();
    }

    public static String deltaTime(long startTime, long endTime) {
        return  (endTime - startTime) + "nanoseconds";
    }



    public static void bstTest() {
        System.out.println("//RANDOM BST//");
        long startTime = System.nanoTime();
        BinarySearchTree<VoteEntry> bst = new BinarySearchTree<>(voteEntries.getFirst());
        for (int i = 1; i < voteEntries.size(); i++) {
            bst.insert(voteEntries.get(i));
        }
        long endTime = System.nanoTime();
        System.out.println(deltaTime(startTime, endTime) + " inserting");
        System.out.println("finished");
        System.out.println("Tree size: " + bst.getSize());
        System.out.println("Tree depth: " + bst.getDepth());
        System.out.println("Minimum value: " + bst.findMinValue());
        System.out.println("Maximum value: " + bst.findMaxValue());
        System.out.println(bst.contains(new VoteEntry("Drievliet", -1)));

        startTime = System.nanoTime();
        bst.inOrderReversePrint();
        endTime = System.nanoTime();
        System.out.println("\n"+deltaTime(startTime, endTime) + "inordertraversal");
    }
    public static void bstTestUnbalanced() {
        System.out.println("//UNBALANCED BST//");
        VoteEntry[] entries = voteEntries.toArray(new VoteEntry[0]);

        //Sort VoteEntries
        QuickSort quickSort = new QuickSort();
        quickSort.sort(entries, 0, entries.length - 1);

        long startTime = System.nanoTime();
        BinarySearchTree<VoteEntry> bst = new BinarySearchTree<>(entries[0]);
        for (int i = 1; i < voteEntries.size(); i++) {
            bst.insert(entries[i]);
        }
        long endTime = System.nanoTime();
        System.out.println(deltaTime(startTime, endTime) + " inserting");

        System.out.println("unbalanced bst");
        System.out.println("Tree size: " + bst.getSize());
        System.out.println("Tree depth: " + bst.getDepth());
        System.out.println("Minimum value: " + bst.findMinValue());
        System.out.println("Maximum value: " + bst.findMaxValue());
        System.out.println(bst.contains(new VoteEntry("Drievliet", -1)));
        startTime = System.nanoTime();
        bst.inOrderReversePrint();
        endTime = System.nanoTime();
        System.out.println("\n"+deltaTime(startTime, endTime) + "inordertraversal");
    }

    public static void AVLTest(){
        System.out.println("//AVL BST//");
        long startTime = System.nanoTime();
        AVLTree<VoteEntry> avlTree = new AVLTree<>();

        for (VoteEntry voteEntry : voteEntries) {
            avlTree.insert(voteEntry);
        }
        long endTime = System.nanoTime();
        System.out.println(deltaTime(startTime, endTime) + " inserting");
        System.out.println(avlTree.getSize());
        System.out.println(avlTree.getDepth());

    }

    public static void arrayTest(){
        VoteEntry[] entries = voteEntries.toArray(new VoteEntry[0]);
        long startTime = System.nanoTime();
        QuickSort quickSort = new QuickSort();
        quickSort.sort(entries, 0, entries.length - 1);
        long endTime = System.nanoTime();
        System.out.println("\n"+deltaTime(startTime, endTime) + "sorting");

        startTime = System.nanoTime();
        for (VoteEntry entry : entries) {
            System.out.print(entry);
        }
        endTime = System.nanoTime();

        System.out.println("\n"+deltaTime(startTime, endTime) + "print");
    }

    public static void bstTestBalanced() {
        System.out.println("//BALANCED BST//");
        VoteEntry[] entries = voteEntries.toArray(new VoteEntry[0]);


        //Sort VoteEntries
        QuickSort quickSort = new QuickSort();
        quickSort.sort(entries, 0, entries.length - 1);

        long startTime = System.nanoTime();
        int mid = entries.length / 2;
        balancedTree = new BinarySearchTree<VoteEntry>(entries[mid]);
        insertBalanced(entries, 0, mid -1);
        insertBalanced(entries, mid + 1, entries.length - 1);

        long endTime = System.nanoTime();
        System.out.println(deltaTime(startTime, endTime));

        System.out.println("finished");
        System.out.println("Tree size: " + balancedTree.getSize());
        System.out.println("Tree depth: " + balancedTree.getDepth());
        System.out.println("Minimum value: " + balancedTree.findMinValue());
        System.out.println("Maximum value: " + balancedTree.findMaxValue());

        startTime = System.nanoTime();
        balancedTree.inOrderReversePrint();
        endTime = System.nanoTime();
        System.out.println("\n"+deltaTime(startTime, endTime) + "inordertraversal");
    }


    public static void insertBalanced(VoteEntry[] array, int start, int end) {
        if (start > end) {
            return;
        }
        int mid =  start + (end - start) / 2;
        balancedTree.insert(array[mid]);
        insertBalanced(array, start, mid - 1);
        insertBalanced(array, mid + 1, end);
    }

    public void AVLTree() {

    }

    public void BTree() {

    }
}

