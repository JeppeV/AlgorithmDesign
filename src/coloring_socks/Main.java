package coloring_socks;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Integer[] problemInfo = stringArrayToIntArray(scanner.nextLine().split(" "));

        int n = problemInfo[0];
        int c = problemInfo[1];
        int d = problemInfo[2];



        Integer[] ss = stringArrayToIntArray(scanner.nextLine().split(" "));
        ArrayList<Integer> socks = new ArrayList<>(Arrays.asList(ss));

        Collections.sort(socks, Comparator.comparingInt(s -> s));

        int previousSock = -1, currentSock;
        int laundryMachineCount = 1;
        int currentLoad = 0;
        for(int i = 0; i < n; i++) {
            currentSock = socks.get(i);

            if(currentLoad == c) {
                currentLoad = 0;
                laundryMachineCount++;
                previousSock = -1;
            }

            if(previousSock < 0) {
                currentLoad++;
            } else {
                if(Math.abs(currentSock - previousSock) <= d) {
                    currentLoad++;
                } else {
                    currentLoad = 1;
                    laundryMachineCount++;
                }
            }

            previousSock = currentSock;

        }

        System.out.println(laundryMachineCount);


    }

    private static Integer[] stringArrayToIntArray(String[] in) {
        Integer[] out = new Integer[in.length];
        for(int i = 0; i < in.length; i++) {
            out[i] = Integer.parseInt(in[i]);
        }
        return out;
    }



}
