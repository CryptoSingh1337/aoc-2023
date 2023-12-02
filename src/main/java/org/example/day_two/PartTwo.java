package org.example.day_two;

import org.example.utils.Scanner;

import java.io.*;
import java.util.Map;

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

        private final Map<String, Integer> colorMapping = Map.of(
                "red", 0,
                "green", 1,
                "blue", 2
        );

        public long sumOfGameIdsWhichArePossible(String[] input) {
            long answer = 0;
            for (int i = 1; i <= input.length; i++) {
                String in = input[i - 1];
                long[] maxOfEachColorRequiredToMakeGamePossible = new long[3];
                String game = in.substring(in.indexOf(":") + 1).trim();
                for (String set : game.split(";")) {
                    String subset = set.trim();
                    int[] noOfColoredDices = new int[3];
                    for (String dice : subset.split(",")) {
                        String coloredDice = dice.trim();
                        String[] splitColorAndNoOfDice = coloredDice.split(" ");
                        int color = colorMapping.get(splitColorAndNoOfDice[1]);
                        noOfColoredDices[color] =
                                Integer.parseInt(splitColorAndNoOfDice[0]);
                        maxOfEachColorRequiredToMakeGamePossible[color] =
                                Math.max(maxOfEachColorRequiredToMakeGamePossible[color],
                                        noOfColoredDices[color]);
                    }
                }
                answer += maxOfEachColorRequiredToMakeGamePossible[0] *
                        maxOfEachColorRequiredToMakeGamePossible[1] *
                        maxOfEachColorRequiredToMakeGamePossible[2];
            }
            return answer;
        }

        public void solve(Scanner sc, PrintWriter pw) throws IOException {
            int n = sc.nextInt();
            String[] input = new String[n];
            for (int i = 0; i < n; i++) {
                input[i] = sc.nextLine();
            }
            pw.println(sumOfGameIdsWhichArePossible(input));
        }
    }
}
