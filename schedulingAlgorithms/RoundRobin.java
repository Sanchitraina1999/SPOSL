import java.util.Arrays;
import java.util.Scanner;

public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

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

        // GANTT CHART
        int i, j;
        System.out.print(" ");
        for (i = 0; i < numProcess; i++) {
            for (j = 0; j < process[i].BT; j++)
                System.out.print("--");
            System.out.print(" ");
        }
        System.out.print("\n|");

        for (i = 0; i < numProcess; i++) {
            for (j = 0; j < process[i].BT - 1; j++)
                System.out.print(" ");
            int x = i + 1;
            System.out.print("P(" + x + ")");
            for (j = 0; j < process[i].BT - 1; j++)
                System.out.print(" ");
            System.out.print("|");
        }
        System.out.print("\n ");
        for (i = 0; i < numProcess; i++) {
            for (j = 0; j < process[i].BT; j++)
                System.out.print("--");
            System.out.print(" ");
        }
        System.out.print("\n");

        System.out.print("0");
        for (i = 0; i < numProcess; i++) {
            for (j = 0; j < process[i].BT; j++)
                System.out.print("  ");
            if (process[i].TAT > 9)
                System.out.print("\b");
            System.out.print(process[i].TAT);

        }
        System.out.print("\n");
        sc.close();

    }

}

/*
    imsotired@imsotired-Vostro-3578:~/Desktop/TE/SPOSL$  /usr/bin/env /usr/lib/jvm/java-11-openjdk-amd64/bin/java -Dfile.encoding=UTF-8 -cp /home/imsotired/.config/Code/User/workspaceStorage/557c4fc057c4787074b405cb8c60a66e/redhat.java/jdt_ws/SPOSL_ad48c254/bin RoundRobin 
Enter Number of Processes:
5
P(1):Enter Arrival time & Burst time
2 6
P(2):Enter Arrival time & Burst time
5 2
P(3):Enter Arrival time & Burst time
1 8
P(4):Enter Arrival time & Burst time
0 3
P(5):Enter Arrival time & Burst time
4 4
Enter Quantum Time: 
2


PRNo    BT      AT      CT      TAT     WT      PR
============================================================================================
0 TIME 2
1 TIME 4
2 TIME 6
3 TIME 8
4 TIME 10
P2      2       5       10      5       3       0
0 TIME 11
P4      3       0       11      11      8       0
1 TIME 13
2 TIME 15
3 TIME 17
P5      4       4       17      13      9       0
1 TIME 19
2 TIME 21
P1      6       2       21      19      13      0
1 TIME 23
P3      8       1       23      22      14      0
*/