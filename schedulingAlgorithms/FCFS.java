import java.util.Arrays;
import java.util.Scanner;

public class FCFS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // --------FCFS
        System.out.println("Enter Number of Processes:");
        int numProcess = sc.nextInt();
        Process[] process = new Process[numProcess];

        // Accept Input
        for (int i = 1; i < numProcess+1; i++) {
            System.out.println("Enter Arrival time & Burst time for Process P(" + i + "):");
            int at = sc.nextInt();
            int bt = sc.nextInt();
            process[i-1] = new Process("P" + i, bt, at);
        }

        // Sorting processes according to Arrival Time //No need if you take AT=0 or in
        // ascending order
        Arrays.sort(process, new SortByArrival());

        int sum = 0;
        double avgWT = 0, avgTAT = 0;
        for (int i = 0; i < numProcess; i++) {
            sum = process[i].CT = sum + process[i].BT;
            process[i].TAT = process[i].CT - process[i].AT;
            process[i].WT = process[i].TAT - process[i].BT;

            avgWT = avgWT + process[i].WT;
            avgTAT = avgTAT + process[i].TAT;

            process[i].display();
        }
        avgTAT = (double) avgTAT / numProcess;
        avgWT = (double) avgWT / numProcess;
        System.out.println("Average Waiting Time = " + avgWT);
        System.out.println("Average TAT = " + avgTAT);
    }
}