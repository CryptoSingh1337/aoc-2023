package org.example.day_three;

import org.example.utils.Scanner;

import java.io.*;

/**
 * @author Saransh Kumar
 */

public class PartOne {

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
                    if (Character.isDigit(parsedInput[i][j])) {
                        int top = i > 0 ? parsedInput[i - 1][j] : '.';
                        int bottom = i < n - 1 ? parsedInput[i + 1][j] : '.';
                        int left = j > 0 ? parsedInput[i][j - 1] : '.';
                        int right = j < m - 1 ? parsedInput[i][j + 1] : '.';
                        int topLeft = i > 0 && j > 0 ? parsedInput[i - 1][j - 1] : '.';
                        int topRight = i > 0 && j < m - 1 ? parsedInput[i - 1][j + 1] : '.';
                        int bottomLeft = i < n - 1 && j > 0 ? parsedInput[i + 1][j - 1] : '.';
                        int bottomRight = i < n - 1 && j < m - 1 ? parsedInput[i + 1][j + 1] : '.';
                        if (isSymbol(top) || isSymbol(bottom) || isSymbol(left) ||
                                isSymbol(right) || isSymbol(topLeft) || isSymbol(topRight) ||
                                isSymbol(bottomLeft) || isSymbol(bottomRight)) {
                            int firstIndex = j, lastIndex = j;
                            while (firstIndex >= 0 && Character.isDigit(parsedInput[i][firstIndex])) {
                                firstIndex--;
                            }
                            firstIndex += 1;
                            while (lastIndex < m && Character.isDigit(parsedInput[i][lastIndex])) {
                                lastIndex++;
                            }
                            lastIndex -= 1;
                            StringBuilder sb = new StringBuilder();
                            for (int k = firstIndex; k <= lastIndex; k++) {
                                sb.append((char) parsedInput[i][k]);
                            }
                            j = lastIndex;
                            System.out.printf("Number found: %s at (%d, %d)\n", sb, i, j);
                            answer += Long.parseLong(sb.toString());
                        }
                    }
                }
            }
            System.out.println("Answer: " + answer);
            return answer;
        }

        public boolean isSymbol(int code) {
            return code != '.' && !Character.isDigit(code);
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
