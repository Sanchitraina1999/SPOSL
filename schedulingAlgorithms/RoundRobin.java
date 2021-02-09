import java.util.Arrays;
import java.util.Scanner;

public class RoundRobin {
    private Scanner sc;

    public void execute() {
        sc = new Scanner(System.in);

        System.out.println("Enter Number of Processes:");
        int numProcess = sc.nextInt();
        Process[] process = new Process[numProcess];

        for (int i = 0; i < numProcess; i++) {
            System.out.println("P(" + (i + 1) + "):Enter Arrival time & Burst time");
            int at = sc.nextInt();
            int bt = sc.nextInt();
            process[i] = new Process("P" + (i + 1), bt, at);
        }
        Arrays.sort(process, new SortByArrival());

        System.out.println("Enter Quantum Time: ");
        int quantum = sc.nextInt();

        double avgWT = 0, avgTAT = 0;
        int time = 0;
        System.out.println("\n\nPRNo\tBT\tAT\tCT\tTAT\tWT\tPR");
        System.out.println(
                "============================================================================================");
        while (true) {
            boolean done = true;
            for (int i = 0; i < numProcess; i++) {
                if (process[i].remBT > 0 && process[i].AT <= time) {
                    done = false;

                    if (process[i].remBT > quantum) {
                        time = time + quantum;
                        process[i].remBT = process[i].remBT - quantum;
                        System.out.println(i + " TIME " + time);

                    } else {

                        time += process[i].remBT;
                        System.out.println(i + " TIME " + time);
                        process[i].remBT = 0;
                        process[i].CT = time;
                        process[i].TAT = process[i].CT - process[i].AT;
                        process[i].WT = process[i].TAT - process[i].BT;
                        avgWT += process[i].WT;
                        avgTAT += process[i].TAT;
                        process[i].display();
                    }
                }

            }
            if (done == true) {
                break;
            }

        }

    }

}
