package org.example.utils;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author Saransh Kumar
 */

public class Scanner {

    private StringTokenizer st;
    private final BufferedReader br;

    public Scanner(InputStream s) {
        br = new BufferedReader(new InputStreamReader(s));
    }

    public Scanner(FileReader s) throws FileNotFoundException {
        br = new BufferedReader(s);
    }

    public String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    public long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    public String nextLine() throws IOException {
        return br.readLine();
    }

    public double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    public int[] readIntArray(int n) throws IOException {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = nextInt();
        return arr;
    }

    public long[] readLongArray(int n) throws IOException {
        long[] arr = new long[n];
        for (int i = 0; i < n; i++)
            arr[i] = nextLong();
        return arr;
    }

    public boolean ready() throws IOException {
        return br.ready();
    }

    public void trace(int[] arr) {
        System.err.println(Arrays.toString(arr));
    }

    public void trace(Integer[] arr) {
        System.err.println(Arrays.toString(arr));
    }

    public void trace(long[] arr) {
        System.err.println(Arrays.toString(arr));
    }

    public void trace(Long[] arr) {
        System.err.println(Arrays.toString(arr));
    }

    public void trace(char[] arr) {
        System.err.println(Arrays.toString(arr));
    }

    public void trace(Character[] arr) {
        System.err.println(Arrays.toString(arr));
    }

    public void trace(Collection<?> collection) {
        System.err.println(collection);
    }

    public void trace(Map<?, ?> map) {
        System.err.println(map);
    }

    public void trace(int a, int... b) {
        System.err.print(a + " ");
        for (int i = 0; i < b.length - 1; i++) {
            System.err.print(b[i] + " ");
        }
        System.err.println(b[b.length - 1]);
    }

    public void trace(double a, double... b) {
        System.err.print(a + " ");
        for (int i = 0; i < b.length - 1; i++) {
            System.err.print(b[i] + " ");
        }
        System.err.println(b[b.length - 1]);
    }

    public void trace(long a, long... b) {
        System.err.print(a + " ");
        for (int i = 0; i < b.length - 1; i++) {
            System.err.print(b[i] + " ");
        }
        System.err.println(b[b.length - 1]);
    }

    public void trace(char a, char... b) {
        System.err.print(a + " ");
        for (int i = 0; i < b.length - 1; i++) {
            System.err.print(b[i] + " ");
        }
        System.err.println(b[b.length - 1]);
    }
}
