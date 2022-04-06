package timingtest;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        SLList<Integer> list = new SLList<>();
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        int opCountsConstant = 10000;
        TimeSLList SLList = new TimeSLList();

        int i = 1;
        list.addLast(i);
        Stopwatch sw;


        while (i <= 128000) {

            list.addLast(i);
            i++;
        }
        i = 1;
        sw = new Stopwatch();
        while (i <= 128000) {

            list.getLast();

            if (i == 1000 || i == 2000 || i == 4000 || i == 8000 || i == 16000 || i == 32000 || i == 64000 || i == 128000) {
                Ns.addLast(i);
                Double dbTime = sw.elapsedTime();
                times.addLast(dbTime);
                opCounts.addLast(opCountsConstant);

            }
            i++;
        }
        SLList.printTimingTable(Ns, times, opCounts);
    }

}
