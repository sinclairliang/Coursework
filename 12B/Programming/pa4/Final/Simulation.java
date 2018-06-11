//-----------------------------------------------------------------------------
//  Simulation.java
//  Sinclair Liang
//  wliang13@ucsc.edu
//  Simulate works by implenmanting Queue ADT
//-----------------------------------------------------------------------------
import java.io.*;
import java.util.Scanner;

public class Simulation {
    // parses ints from input_file and returns Job object
    public static Job getJob(Scanner in) {
        String[] s = in.nextLine().split(" ");
        int a = Integer.parseInt(s[0]);
        int d = Integer.parseInt(s[1]);
        return new Job(a, d);
    }
    static void usage() {
        System.out.println("Usage: Simulation input_file");
        System.exit(1);
    }
    static void printheading(PrintWriter printer, String input, String arg, int m, Queue q1) {
        if (input == "R") {
            printer.println("Report file: " + arg + ".rpt");
            printer.println(m + " Jobs:");
            printer.println(q1 + "\n");
            printer.println("***********************************************************");

        } else if (input == "T") {
            printer.println("Trace file: " + arg + ".trc");
            printer.println(m + " Jobs:");
            printer.println(q1 + "\n");

        } else if (input == "Th") {
            printer.println("*****************************");
            printer.print(m);
            printer.println(m == 1 ? " processor:" : " processors:");
            printer.println("*****************************");

        } else {
            System.out.println("ERROR.");
        }
    }
    static void printtrace(PrintWriter printer, Queue q1, Queue q2, Queue[] queues, int n, int time) {
        printer.println("time=" + time);
        printer.println("0: " + q1 + q2);

        for (int i = 0; i < n; i++) {
            printer.println((i + 1) + ": " + queues[i]);
        }

        printer.println();
    }
    static void printtrace(PrintWriter printer, Queue q1, Queue[] queues, int n, int time) {
        printer.println("time=" + time);
        printer.println("0: " + q1);

        for (int i = 0; i < n; i++) {
            printer.println((i + 1) + ": " + queues[i]);
        }

        printer.println();
    }
    static void printreport(PrintWriter printer, int n, double totalWait, double maxWait, double avg_wait) {
        printer.print(n);
        printer.print(n == 1 ? " processor: " : " processors: ");
        printer.printf("totalWait=% .0f, maxWait=% .0f, averageWait=% .2f\n", totalWait, maxWait, avg_wait);
    }
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            usage();
        }

        Scanner in = new Scanner(new File(args[0]));
        int m = in.nextInt();
        in.nextLine();
        PrintWriter report = new PrintWriter(new FileWriter(args[0] + ".rpt"));
        PrintWriter trace = new PrintWriter(new FileWriter(args[0] + ".trc"));
        Queue initial = new Queue();
        Queue storage = new Queue();


        for (int i = 0; i < m; i++) {
            Job J = getJob(in);
            initial.enqueue(J);
            storage.enqueue(J);
        }

        printheading(report, "R", args[0], m, storage);
        printheading(trace, "T", args[0], m, storage);

        for (int n = 1; n < m; n++) {
            printheading(trace, "Th", args[0], n, storage);
            int time = 0;
            int[] line = new int[m];
            int waiting = 0;
            boolean openline = true;
            boolean jobDone = false;
            Queue[] processors = new Queue[n];

            for (int i = 0; i < n; i++) {
                processors[i] = new Queue();
            }

            Queue move = new Queue();

            printtrace(trace, storage, processors, n, time);

            while (storage.isEmpty() == false || openline == false) {
                for ( int i = 0; i < n; i++) {
                    if (processors[i].isEmpty() == false && ((Job)(processors[i].peek())).getFinish() == -1) {
                        ((Job)(processors[i].peek())).computeFinishTime(time);
                    }
                }

                if (jobDone == true) {
                    printtrace(trace, storage, move, processors, n, time);
                    jobDone = false;
                }

                time++;

                for (int i = 0; i < n; i++) {
                    if (processors[i].isEmpty() == false) {
                        if (((Job)(processors[i].peek())).getFinish() == time) {

                            line[waiting++] = ((Job)(processors[i].peek())).getWaitTime();
                            move.enqueue(((Job)(processors[i].dequeue())));
                            jobDone = true;
                        }
                    }
                }

                while ((storage.isEmpty()) == false && ((Job)(storage.peek())).getArrival() == time) {
                    int min = 3000;
                    //random number of length;
                    int lowestIndex = 0;

                    for (int i = 0; i < n; i++) {
                        if (processors[i].length() < min) {
                            min = processors[i].length();
                            lowestIndex = i;
                        }
                    }

                    processors[lowestIndex].enqueue(storage.dequeue());
                    jobDone = true;
                }


                openline = true;

                for (int i = 0; i < n; i++) {
                    if (processors[i].isEmpty() == false) {
                        openline = false;
                        break;
                    }
                }
            }

            printtrace(trace, storage, move, processors, n, time);
            double max_wait = 0;
            double totalWait = 0;
            double avg_wait = 0;

            for (int index = 0; index < line.length; index++) {
                totalWait += line[index];
            }

            for (int i = 0; i < line.length; i++) {
                if (line[i] > max_wait) {
                    max_wait = line[i];
                }
            }

            avg_wait = totalWait / line.length;

            printreport(report, n, totalWait, max_wait, avg_wait);

            for (int i = 0; i < initial.length() ; i++) {
                initial.enqueue(((Job)(initial.peek())));
                ((Job)(initial.peek())).resetFinishTime();
                storage.enqueue(((Job)(initial.dequeue())));
            }

            move.dequeueAll();
        }

        in.close();
        report.close();
        trace.close();
    }
}
