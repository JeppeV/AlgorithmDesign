package detailed_differences;

import java.util.Scanner;

public class DetailedDifferences {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        String sequence1, sequence2;
        StringBuilder resultBuilder;

        for(int i = 0; i < n; i++) {

            sequence1 = scanner.nextLine();
            sequence2 = scanner.nextLine();
            resultBuilder = new StringBuilder();

            for(int j = 0; j < sequence1.length(); j++) {

                if(sequence1.charAt(j) == sequence2.charAt(j)) {
                    resultBuilder.append(".");
                }else {
                    resultBuilder.append("*");
                }
            }

            System.out.println(sequence1);
            System.out.println(sequence2);
            System.out.println(resultBuilder.toString());
            System.out.println();


        }

    }

}
