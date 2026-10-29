package org.souhaib;

public class BinarySearch {


    public static int binarySearch_iterative(int[] arr, int target){
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            } if (arr[mid] < target) {
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static int binarySearch_recursion(int[] arr, int target) {
        return binarySearchHelper(arr, 0, arr.length - 1, target);
    }

    private static int binarySearchHelper(int[] arr, int left, int right, int target) {
        if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return mid;
        }

        if (arr[mid] > target) {
            return binarySearchHelper(arr, left, mid - 1, target);
        }

        return binarySearchHelper(arr, mid + 1, right, target);
    }

    public static int linearSearch_iterative(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }

        return -1;
    }

    public static int linearSearch_recursion(int[] arr, int target) {
        return linearSearchHelper(arr, target, 0);
    }

    private static int linearSearchHelper(
            int[] arr, int target, int index) {

        if (index >= arr.length) {
            return -1;
        }

        if (arr[index] == target) {
            return index;
        }

        return linearSearchHelper(arr, target, index + 1);
    }

}
