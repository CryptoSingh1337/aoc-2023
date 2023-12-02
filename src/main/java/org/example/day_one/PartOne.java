package org.example.day_one;

import org.example.utils.Scanner;

import java.io.*;

/**
 * @author Saransh Kumar
 */

public class PartOne {

    public static class Task {

        public long calibrationValueLoopVersion(String[] str) {
            long ans = 0;
            for (String s : str) {
                int firstDigit = 0, lastDigit = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (Character.isDigit(s.charAt(i))) {
                        firstDigit = s.charAt(i) - '0';
                        System.out.println("First digit: " + firstDigit);
                        break;
                    }
                }
                for (int i = s.length() - 1; i >= 0; i--) {
                    if (Character.isDigit(s.charAt(i))) {
                        lastDigit = s.charAt(i) - '0';
                        System.out.println("Last digit: " + lastDigit);
                        break;
                    }
                }
                String number = String.format("%d%d", firstDigit, lastDigit);
                System.out.println(number);
                ans += Long.parseLong(number);
            }
            return ans;
        }

        public long calibrationValueSequentialStreamVersion(String[] str) {
            long ans = 0;
            for (String s : str) {
                int firstDigit = s.chars()
                        .filter(Character::isDigit)
                        .findFirst()
                        .orElse(0) - '0';
                int lastDigit = s.chars()
                        .filter(Character::isDigit)
                        .skip(s.chars()
                                .filter(Character::isDigit)
                                .count() - 1)
                        .findFirst()
                        .orElse(0) - '0';
                String number = String.format("%d%d", firstDigit, lastDigit);
                ans += Long.parseLong(number);
            }
            return ans;
        }

        public long calibrationValueParallelStreamVersion(String[] str) {
            long ans = 0;
            for (String s : str) {
                int firstDigit = s.chars().parallel()
                        .filter(Character::isDigit)
                        .findFirst()
                        .orElse(0) - '0';
                int lastDigit = s.chars().parallel()
                        .filter(Character::isDigit)
                        .skip(s.chars().parallel()
                                .filter(Character::isDigit)
                                .count() - 1)
                        .findFirst()
                        .orElse(0) - '0';
                String number = String.format("%d%d", firstDigit, lastDigit);
                ans += Long.parseLong(number);
            }
            return ans;
        }

        public void solve(Scanner sc, PrintWriter pw) throws IOException {
            int n = sc.nextInt();
            String[] str = new String[n];
            for (int i = 0; i < n; i++) {
                str[i] = sc.nextLine();
            }

            long startTime = System.nanoTime();
            long answer = calibrationValueLoopVersion(str);
            long endTime = System.nanoTime();
            System.out.println("Total Time taken by loop version: " + ((endTime - startTime) / 1000) + " ms");
            pw.println("Loop version answer: " + answer);

            startTime = System.nanoTime();
            answer = calibrationValueSequentialStreamVersion(str);
            endTime = System.nanoTime();
            System.out.println("Total Time taken by Sequential Stream version: " + ((endTime - startTime) / 1000) + " ms");
            pw.println("Sequential Stream version answer: " + answer);

            startTime = System.nanoTime();
            answer = calibrationValueParallelStreamVersion(str);
            endTime = System.nanoTime();
            System.out.println("Total Time taken by Parallel Stream version: " + ((endTime - startTime) / 1000) + " ms");
            pw.println("Parallel Stream version answer: " + answer);
        }
    }

    public static void main(String... args) throws IOException {
        Scanner sc = new Scanner(new FileReader(System.getenv("INPUT")));
        PrintWriter pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(System.getenv("OUTPUT"))));
        Task t = new Task();
        int T = sc.nextInt();
        while (T-- > 0)
            t.solve(sc, pw);
        pw.close();
    }
}
