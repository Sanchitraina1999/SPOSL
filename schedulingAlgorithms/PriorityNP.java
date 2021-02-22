import java.util.Arrays;
import java.util.Scanner;

public class PriorityNP {
	private static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);

		System.out.println("Enter Number of Processes:");
		int numProcess = sc.nextInt();
		Process[] process = new Process[numProcess];

		// Accept Input
		for (int i = 0; i < numProcess; i++) {
			System.out.println("P(" + (i + 1) + "):Enter  Arrival time, Burst time & priority ");
			int at = sc.nextInt();
			int bt = sc.nextInt();
			int priority = sc.nextInt();
			System.out.println("P(" + (i + 1) + "):Enter Arrival time");
		}
		Arrays.sort(process, new SortByPriority());

		int sum = 0;
		double avgWT = 0, avgTAT = 0;
		System.out.println("\n\nPRNo\tBT\tAT\tCT\tTAT\tWT\tPR");
		System.out.println("============================================================================================");
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
		System.out.println("Average Waiting Time" + avgWT);
		System.out.println("Average TAT=" + avgTAT);

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

	}
}
/*
	imsotired@imsotired-Vostro-3578:~/Desktop/TE/SPOSL$  /usr/bin/env /usr/lib/jvm/java-11-openjdk-amd64/bin/java -Dfile.encoding=UTF-8 -cp /home/imsotired/.config/Code/User/workspaceStorage/557c4fc057c4787074b405cb8c60a66e/redhat.java/jdt_ws/SPOSL_ad48c254/bin FCFS 
Enter Number of Processes:
5
Enter Arrival time & Burst time for Process P(1):
2 6
Enter Arrival time & Burst time for Process P(2):
5 2
Enter Arrival time & Burst time for Process P(3):
1 8
Enter Arrival time & Burst time for Process P(4):
0 3
Enter Arrival time & Burst time for Process P(5):
4 4


PRNo    BT      AT      CT      TAT     WT
==================================================
P4      3       0       3       3       0       0
P3      8       1       11      10      2       0
P1      6       2       17      15      9       0
P5      4       4       21      17      13      0
P2      2       5       23      18      16      0
Average Waiting Time = 8.0
Average TAT = 12.6
 -------- ------------------------ ------------------ ------------ ------ 
|   P(1)   |        P(2)        |      P(3)      |    P(4)    |  P(5)  |
 -------- ------------------------ ------------------ ------------ ------ 
0        3                       10                 15           17     18
*/
