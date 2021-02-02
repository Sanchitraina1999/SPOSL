import java.util.*;

class pr {
	int arrivalTime, burstTime, id, waitingTime, turnaroundTime;
	String name;
}

public class SJF {
	static int ch, n;
	static pr obj[] = new pr[100];
	static Scanner sc = new Scanner(System.in);

	public static void processInput() {
		System.out.print("Enter no of processes: \n");
		n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			System.out.print("Enter processes arrival time, burst time: \n");
			obj[i] = new pr();
			obj[i].arrivalTime = sc.nextInt();
			obj[i].burstTime = sc.nextInt();
			int x = i + 1;
			obj[i].name = "P" + x;
			obj[i].id = i;
		}
	}

	public static void prsort() {
		// Arrays.sort(obj);
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < n; j++) {
				if (obj[j - 1].arrivalTime > obj[j].arrivalTime) {
					// swap
					pr temp = new pr();
					temp = obj[j - 1];
					obj[j - 1] = obj[j];
					obj[j] = temp;
				}
			}
		}
	}

	public static void display() {
		double avgwt = 0.0, avgTAT = 0.0;
		for (int i = 0; i < n; i++) {
			System.out.println("Process name: " + obj[i].name + "\t" + "Waiting Time: " + obj[i].waitingTime + "\t"
					+ "Turnaround Time: " + obj[i].turnaroundTime);
			avgwt += obj[i].waitingTime;
			avgTAT += obj[i].turnaroundTime;
		}
		avgwt /= n;
		avgTAT /= n;
		System.out.println("Avgwt = " + avgwt + "\n");
		System.out.println("AvgTAT = " + avgTAT + "\n");
		System.out.println("\n");
	}

	public static void sjf() {
		System.out.println("\n\nSJF\n\n");
		prsort();
		pr obj1[] = new pr[100];
		for (int i = 0; i < n; i++) {
			obj1[i] = new pr();
			obj1[i].arrivalTime = obj[i].arrivalTime;
			obj1[i].burstTime = obj[i].burstTime;
		}
		int totalBurstTime = 0, selected, minval = 1;
		for (int i = 0; i < n; i++)
			totalBurstTime += obj[i].burstTime;
		for (int i = 0; i <= obj[n - 1].arrivalTime + totalBurstTime; i++) {
			selected = -1;
			minval = 100000000;
			for (int j = 0; j < n; j++) {
				if (obj1[j].burstTime <= minval && i >= obj1[j].arrivalTime && obj1[j].burstTime >= 1) {
					minval = obj1[j].burstTime;
					selected = j;
				}
			}
			if (selected != -1) {
				obj1[selected].burstTime--;
				if (obj1[selected].burstTime == 0) {
					obj[selected].waitingTime = Math.max(i - (obj[selected].burstTime + obj[selected].arrivalTime) + 1,
							0);
					obj[selected].turnaroundTime = obj[selected].waitingTime + obj[selected].burstTime;
				}
			}
		}
	}

	public static void main(String[] Args) {
		processInput();
		sjf();
		display();
		sc.close();
	}
}