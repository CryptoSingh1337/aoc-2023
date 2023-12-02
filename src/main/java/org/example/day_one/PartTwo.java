package org.example.day_one;

import org.example.utils.Scanner;

import java.io.*;
import java.util.Map;

/**
 * @author Saransh Kumar
 */

public class PartTwo {

    public static class Task {

        private final Map<String, Integer> map = Map.of(
                "one", 1,
                "two", 2,
                "three", 3,
                "four", 4,
                "five", 5,
                "six", 6,
                "seven", 7,
                "eight", 8,
                "nine", 9
        );

        public long calibrationValue(String[] str, int n) {
            long ans = 0;
            for (String s : str) {
                int firstDigit = 0, lastDigit = 0;
                String word = "";

                int wordDigitIndex = Integer.MAX_VALUE, digitIndex = Integer.MAX_VALUE;
                for (String digit : map.keySet()) {
                    int index = s.indexOf(digit);
                    if (index != -1) {
                        if (wordDigitIndex > index) {
                            wordDigitIndex = index;
                            word = digit;
                        }
                    }
                }
                for (int i = 0; i < s.length(); i++) {
                    if (Character.isDigit(s.charAt(i))) {
                        digitIndex = i;
                        firstDigit = s.charAt(i) - '0';
                        break;
                    }
                }

                if (wordDigitIndex < digitIndex) {
                    firstDigit = map.get(word);
                }

                StringBuilder sb = new StringBuilder(s);
                sb.reverse();

                wordDigitIndex = Integer.MAX_VALUE; digitIndex = Integer.MAX_VALUE;
                for (String digit : map.keySet()) {
                    StringBuilder _digit = new StringBuilder(digit);
                    _digit.reverse();
                    int index = sb.indexOf(_digit.toString());
                    if (index != -1) {
                        if (wordDigitIndex > index) {
                            wordDigitIndex = index;
                            word = _digit.reverse().toString();
                        }
                    }
                }
                for (int i = 0; i < sb.length(); i++) {
                    if (Character.isDigit(sb.charAt(i))) {
                        digitIndex = i;
                        lastDigit = sb.charAt(i) - '0';
                        break;
                    }
                }

                if (wordDigitIndex < digitIndex) {
                    lastDigit = map.get(word);
                }

                String number = String.format("%d%d", firstDigit, lastDigit);
                System.out.println(number);
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
            pw.println(calibrationValue(str, n));
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
