package com.shahid.javadsa;

import java.util.Arrays;
import java.util.Date;
import java.util.StringJoiner;

public class Array {
    public static int[] withoutDuplicates(int[] arr) {
        if (arr.length <= 1) return arr;
        Arrays.sort(arr);
        int i = 0, j = 1;
        while (j < arr.length) {
            if (arr[i] != arr[j]) {
                swap(arr, ++i, j);
            }
            j++;
        }
        return Arrays.copyOfRange(arr, 0, i + 1);
    }

    public static int[][] pascalTriangle(int size) {
        if (size <= 0) return new int[0][0];
        int[][] pascalTriangle = new int[size][];
        for (int i = 0; i < size; i++) {
            if (i == 0) pascalTriangle[0] = new int[]{1};
            else {
                int[] row = new int[i + 1];
                row[0] = 1;
                row[i] = 1;
                for (int j = 1; j < i; j++) row[j] = pascalTriangle[i - 1][j - 1] + pascalTriangle[i - 1][j];
                pascalTriangle[i] = row;
            }
        }
        return pascalTriangle;
    }

    public static String factor(int n) {
        StringJoiner sj = new StringJoiner("*");
        while (n > 1) {
            int primeFactor = n % 2 == 0 ? 2 : primeFactor(n);
            sj.add(String.valueOf(primeFactor));
            n /= primeFactor;
        }
        return sj.toString();
    }

    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) return false;
        }
        return true;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    private static int primeFactor(int number) {
        boolean[] prime = new boolean[number + 1];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;

        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) return i;
            if (prime[i]) {
                for (int j = i * i; j <= number; j += i) {
                    if (number % j == 0) return j;
                    prime[j] = false;
                }
            }
        }

        return number;
    }
}
