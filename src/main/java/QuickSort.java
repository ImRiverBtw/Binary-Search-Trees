package main.java;

import main.java.models.VoteEntry;

public class QuickSort {

    public void sort(VoteEntry[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            sort(arr, left, partitionIndex - 1);
            sort(arr, partitionIndex + 1, right);
        }
    }

    private int partition(VoteEntry[] arr, int left, int right) {
        VoteEntry pivot = arr[right];
        int i = (left - 1);
        for (int j = left; j < right; j++) {
            if (arr[j].compareTo(pivot) <= 0) {
                i++;
                VoteEntry temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        VoteEntry temp = arr[i+1];
        arr[i+1] = arr[right];
        arr[right] = temp;
        return i+1;
    }
}
