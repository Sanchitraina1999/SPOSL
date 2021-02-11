import java.util.Arrays;
import java.util.Scanner;

public class PriorityNP {
	private Scanner sc;

	public void execute() {
		sc = new Scanner(System.in);

		System.out.println("Enter Number of Processes:");
		int numProcess = sc.nextInt();
		Process[] process = new Process[numProcess];

		// Accept Input
		for (int i = 0; i < numProcess; i++) {
			System.out.println("P(" + (i + 1) + "):Enter  Arrival time, Burst time & priority "); //
			int at = sc.nextInt();
			int bt = sc.nextInt();
			int priority = sc.nextInt();
		}
		Arrays.sort(process, new SortByPriority());

		int sum = 0;
		double avgWT = 0, avgTAT = 0;
		System.out.println("\n\nPRNo\tBT\tAT\tCT\tTAT\tWT\tPR");
		System.out.println(
				"============================================================================================");
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
