package org.example.day_three;

import org.example.utils.Scanner;

import java.io.*;
import java.util.stream.Stream;

/**
 * @author Saransh Kumar
 */

public class PartTwo {

    public static void main(String... args) throws IOException {
        Scanner sc = new Scanner(new FileReader(System.getenv("INPUT")));
        PrintWriter pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(System.getenv("OUTPUT"))));
        Task t = new Task();
        int T = sc.nextInt();
        while (T-- > 0)
            t.solve(sc, pw);
        pw.close();
    }

    public static class Task {

        public long formNumber(int[] input, int j) {
            int firstIndex = j, lastIndex = j;
            while (firstIndex >= 0 && Character.isDigit(input[firstIndex])) {
                firstIndex--;
            }
            firstIndex += 1;
            while (lastIndex < input.length && Character.isDigit(input[lastIndex])) {
                lastIndex++;
            }
            lastIndex -= 1;
            StringBuilder sb = new StringBuilder();
            for (int k = firstIndex; k <= lastIndex; k++) {
                sb.append((char) input[k]);
            }
            return Long.parseLong(sb.toString());
        }

        public long sumOfPartNumbers(String[] input) {
            long answer = 0;
            int[][] parsedInput = new int[input.length][input[0].length()];
            for (int i = 0; i < input.length; i++) {
                for (int j = 0; j < input[i].length(); j++) {
                    parsedInput[i][j] = input[i].charAt(j);
                }
            }
            int n = parsedInput.length, m = parsedInput[0].length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (parsedInput[i][j] == '*') {
                        System.out.printf("* found at (%d, %d)\n", i, j);
                        long top = i > 0 && isSymbol(parsedInput[i - 1][j]) ?
                                formNumber(parsedInput[i - 1], j) : 1;
                        long bottom = i < n - 1 && isSymbol(parsedInput[i + 1][j]) ?
                                formNumber(parsedInput[i + 1], j) : 1;
                        long left = j > 0 && isSymbol(parsedInput[i][j - 1]) ?
                                formNumber(parsedInput[i], j - 1) : 1;
                        long right = j < m - 1 && isSymbol(parsedInput[i][j + 1]) ?
                                formNumber(parsedInput[i], j + 1) : 1;
                        long topLeft = i > 0 && j > 0 && isSymbol(parsedInput[i - 1][j - 1]) ?
                                formNumber(parsedInput[i - 1], j - 1) : 1;
                        long topRight = i > 0 && j < m - 1 && isSymbol(parsedInput[i - 1][j + 1]) ?
                                formNumber(parsedInput[i - 1], j + 1) : 1;
                        long bottomLeft = i < n - 1 && j > 0 && isSymbol(parsedInput[i + 1][j - 1]) ?
                                formNumber(parsedInput[i + 1], j - 1) : 1;
                        long bottomRight = i < n - 1 && j < m - 1 && isSymbol(parsedInput[i + 1][j + 1]) ?
                                formNumber(parsedInput[i + 1], j + 1) : 1;

                        if (top == topLeft || top == topRight) {
                            top = 1;
                        }

                        if (topLeft == topRight) {
                            topLeft = 1;
                        }

                        if (bottom == bottomLeft || bottom == bottomRight) {
                            bottom = 1;
                        }

                        if (bottomLeft == bottomRight) {
                            bottomLeft = 1;
                        }

                        long count = Stream.of(top, bottom, left, right, topLeft,
                                topRight, bottomLeft, bottomRight)
                                .filter(in -> in != 1)
                                .count();
                        boolean shouldAdd = count > 1;

                        System.out.printf("""
                                Numbers are top: %d, bottom: %d, left: %d, right: %d, topLeft: %d, topRight: %d,
                                bottomLeft: %d, bottomRight: %d
                                """, top, bottom, left, right, topLeft, topRight, bottomLeft, bottomRight);
                        if (shouldAdd) {
                            answer += top * bottom * left * right * topLeft * topRight * bottomLeft * bottomRight;
                        }
                    }
                }
            }
            System.out.println("Answer: " + answer);
            return answer;
        }

        public boolean isSymbol(int code) {
            return Character.isDigit(code);
        }

        public void solve(Scanner sc, PrintWriter pw) throws IOException {
            int n = sc.nextInt();
            String[] input = new String[n];
            for (int i = 0; i < n; i++) {
                input[i] = sc.nextLine();
            }
            pw.println(sumOfPartNumbers(input));
        }
    }
}
