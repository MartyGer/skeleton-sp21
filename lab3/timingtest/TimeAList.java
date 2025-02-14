package timingtest;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        TimeAList t = new TimeAList();
        AList<Integer> list = new AList<>();
        AList<Integer> listMem = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        Stopwatch sw = new Stopwatch();
        int i = 0;
        while (i <= 128000) {
            if (i == 1000 || i == 2000 || i == 4000 || i == 8000 || i == 16000 || i == 32000 || i == 64000 || i == 128000) {
                //times.addLast();
                list.addLast(i);
                opCounts.addLast(i);
                double timeInSeconds = sw.elapsedTime();
                times.addLast(timeInSeconds);

            }
            listMem.addLast(i);



            i++;

        }
        t.printTimingTable(list, times, opCounts);


    }
}
