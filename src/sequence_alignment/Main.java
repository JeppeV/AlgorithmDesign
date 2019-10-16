package sequence_alignment;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class Main {


    private enum DIRECTION { DONE, DIAGONAL, UP, LEFT }

    public static void main(String[] args) {

        try{

            HashMap<Character, HashMap<Character, Integer>> mismatchCosts = parseMismatchCosts("C:\\Users\\Jeppe\\GitHub\\AlgorithmDesign\\src\\sequence_alignment\\data\\BLOSUM62.txt");
            String gorilla = parseAnimalString("Human", "C:\\Users\\Jeppe\\GitHub\\AlgorithmDesign\\src\\sequence_alignment\\data\\HbB_FASTAs-in.txt");
            String deer = parseAnimalString("Horse", "C:\\Users\\Jeppe\\GitHub\\AlgorithmDesign\\src\\sequence_alignment\\data\\HbB_FASTAs-in.txt");

            runSequenceAlignment(gorilla, deer, 4, mismatchCosts);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }







    private static void runSequenceAlignment(String x, String y, int gapPenalty, HashMap<Character, HashMap<Character, Integer>> mismatchCosts) {

        x = "*" + x;
        y = "*" + y;
        int m = x.length();
        int n = y.length();

        int[][] M = new int[m][n];
        DIRECTION[][] traceback = new DIRECTION[m][n];

        for(int i = 0; i < m; i++) {
            M[i][0] = i*gapPenalty;
            traceback[i][0] = DIRECTION.LEFT;
        }

        for(int j = 0; j < n; j++) {
            M[0][j] = j*gapPenalty;
            traceback[0][j] = DIRECTION.UP;
        }

        traceback[0][0] = DIRECTION.DONE;

        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                char xChar = x.charAt(i);
                char yChar = y.charAt(j);
                int mismatchCost = mismatchCosts.get(xChar).get(yChar);
                int diagonalValue = mismatchCost + M[i-1][j-1];
                int leftValue = gapPenalty + M[i-1][j];
                int upValue = gapPenalty + M[i][j-1];

                if(diagonalValue <= upValue && diagonalValue <= leftValue) {
                    traceback[i][j] = DIRECTION.DIAGONAL;
                    M[i][j] = diagonalValue;
                } else if (leftValue <= diagonalValue && leftValue <= upValue) {
                    traceback[i][j] = DIRECTION.LEFT;
                    M[i][j] = leftValue;
                } else {
                    traceback[i][j] = DIRECTION.UP;
                    M[i][j] = upValue;
                }

                //M[i][j] = Math.min(mismatchCost + M[i-1][j-1], Math.min(gapPenalty + M[i-1][j], gapPenalty + M[i][j-1]));
            }
        }


        StringBuilder x_aligned = new StringBuilder();
        StringBuilder y_aligned = new StringBuilder();

        int i = m-1;
        int j = n-1;

        DIRECTION direction;

        while((direction = traceback[i][j]) != DIRECTION.DONE) {
            char xChar = x.charAt(i);
            char yChar = y.charAt(j);

            if(direction == DIRECTION.DIAGONAL) {
                i -= 1;
                j -= 1;
                x_aligned.append(xChar);
                y_aligned.append(yChar);
            } else if (direction == DIRECTION.LEFT) {
                i -= 1;
                x_aligned.append(xChar);
                y_aligned.append("_");
            } else {
                j -= 1;
                x_aligned.append("_");
                y_aligned.append(yChar);
            }
        }

        System.out.println(-M[m-1][n-1]);
        System.out.println(x_aligned.reverse().toString());
        System.out.println(y_aligned.reverse().toString());

    }

    private static HashMap<Character, HashMap<Character, Integer>> parseMismatchCosts(String filename) throws IOException {

        HashMap<Character, HashMap<Character, Integer>> mismatchCosts = new HashMap<>();

        File file = new File(filename);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String input;
        while((input = reader.readLine()).startsWith("#")) {}

        String[] firstRow = input.trim().split("\\s+");
        for(int i = 0; i < firstRow.length; i++) {
            char c = firstRow[i].charAt(0);
            mismatchCosts.put(c, new HashMap<>());
        }

        String[] row;
        while((input = reader.readLine()) != null) {
            row = input.trim().split("\\s+");
            char outerC = row[0].charAt(0);
            HashMap<Character,Integer> map = mismatchCosts.get(outerC);
            for(int i = 1; i < row.length; i++) {
                char innerC = firstRow[i-1].charAt(0);
                map.put(innerC, -Integer.parseInt(row[i]));
            }
        }

        return mismatchCosts;
    }

    private static String parseAnimalString(String animalName, String filename) throws IOException {

        animalName = animalName.toLowerCase().trim();
        HashMap<Character, HashMap<Character, Integer>> mismatchCosts = new HashMap<>();

        File file = new File(filename);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String input;
        while(!reader.readLine().toLowerCase().startsWith(">" + animalName)) {}

        StringBuilder stringBuilder = new StringBuilder();

        input = reader.readLine();
        while(!(input == null) && !input.startsWith(">")) {
            stringBuilder.append(input.trim());
            input = reader.readLine();
        }

        return stringBuilder.toString();

    }

}
