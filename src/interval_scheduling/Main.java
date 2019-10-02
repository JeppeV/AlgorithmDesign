package interval_scheduling;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        String[] intervalInfo;
        Interval interval;
        TreeSet<Interval> intervals = new TreeSet<>(new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                int result = i1.finishTime - i2.finishTime;
                if(result == 0) result = i1.startTime - i2.startTime;
                return result;
            }
        });

        //System.out.println("n=" + n);
        for(int i = 0; i < n; i++) {
            //System.out.println("i=" + i);
            intervalInfo = scanner.nextLine().trim().split(" ");
            //System.out.println(String.join(",", intervalInfo));
            interval = new Interval(Integer.parseInt(intervalInfo[0]), Integer.parseInt(intervalInfo[1]));
            //System.out.println(interval);
            intervals.add(interval);
            //System.out.println(intervals);
        }



        //intervals.sort(Comparator.comparingInt(t -> t.finishTime));

        LinkedList<Interval> result = new LinkedList<>();
        //int result = 0;

        Interval first = intervals.pollFirst();
        result.add(first);
        int previousFinishTime = first.finishTime;

        for(Interval current : intervals) {
            if(previousFinishTime <= current.startTime) {
                result.add(current);
                previousFinishTime = current.finishTime;
            }
        }

        System.out.println(result.size());
    }

    private static class Interval {

        Interval(int startTime, int finishTime) {
            this.startTime = startTime;
            this.finishTime = finishTime;
        }

        int startTime;
        int finishTime;

        @Override
        public String toString() {
            return "[start:" + startTime + ", finish:" + finishTime + "]";
        }
    }

}
