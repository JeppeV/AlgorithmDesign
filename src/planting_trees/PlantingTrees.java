package planting_trees;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class PlantingTrees {


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        in.nextLine();
        String input = in.nextLine();
        System.out.println(getLastDay(doParse(input)));

       /* String[] inputs = {"2 3 4 3", "39 38 9 35 39 20", "1 1 1", "1", "3 3 3"};
        for(String input : inputs) {

            System.out.println(getLastDay(doParse(input)));

        }
*/






    }

    private static List<Integer> doParse(String input) {
        String[] tokens = input.split(" ");
        List<Integer> trees = new ArrayList<>(tokens.length);

        for(int i = 0; i < tokens.length; i++) {
            trees.add(Integer.parseInt(tokens[i]));
        }

        return trees;
    }

    private static int getLastDay(List<Integer> trees) {

        trees.sort((i1, i2) -> i2 - i1);

        int lastDay = 1, currentDay;

        for(int i = 0; i < trees.size(); i++) {
            currentDay = i + 1;
            lastDay = Math.max(currentDay + trees.get(i) + 1, lastDay);
        }

        return lastDay;
    }

}
