import java.util.Arrays;
import java.util.Scanner;

public class PriorityNP {
	private Scanner sc;

	public void  execute()
	{
		sc = new Scanner(System.in);

		System.out.println("Enter Number of Processes:");
		int numProcess=sc.nextInt();
		Process []process=new Process[numProcess];

		//Accept Input
		for(int i=0;i<numProcess;i++)
		{
			System.out.println("P("+(i+1)+"):Enter  Burst time  & priority"); //
			int at=0;//sc.nextInt();
			//Note: Arrival time is 0 for all processes;
			int bt=sc.nextInt();
			int priority=sc.nextInt();
			//System.out.println("P("+(i+1)+"):Enter Arrival time");

			process[i]=new Process("P"+(i+1), bt, at,priority);
		}
		//Sorting processes according to Arrival Time //No need if you take AT=0 or in ascending order
				Arrays.sort(process,new SortByPriority());

				int sum=0;
				double avgWT=0,avgTAT=0;
				System.out.println("\n\nPRNo\tBT\tAT\tCT\tTAT\tWT\tPR");
				System.out.println("============================================================================================");
				for(int i=0;i<numProcess;i++)
				{
					sum=process[i].CT=sum+process[i].BT;
					process[i].TAT=process[i].CT-process[i].AT;
					process[i].WT=process[i].TAT-process[i].BT;

					avgWT=avgWT+process[i].WT;
					avgTAT=avgTAT+process[i].TAT;

					process[i].display();
				}
		
		
		avgTAT=(double)avgTAT/numProcess;
		avgWT=(double)avgWT/numProcess;
		System.out.println("Average Waiting Time"+avgWT);
		System.out.println("Average TAT="+avgTAT);
			
		int i, j;
		System.out.print(" ");
		for (i = 0; i < n; i++) {
			for (j = 0; j < obj[i].burstTime; j++)
				System.out.print("--");
			System.out.print(" ");
		}
		System.out.print("\n|");

		for (i = 0; i < n; i++) {
			for (j = 0; j < obj[i].burstTime - 1; j++)
				System.out.print(" ");
			int x = i + 1;
			System.out.print("P(" + x + ")");
			for (j = 0; j < obj[i].burstTime - 1; j++)
				System.out.print(" ");
			System.out.print("|");
		}
		System.out.print("\n ");
		for (i = 0; i < n; i++) {
			for (j = 0; j < obj[i].burstTime; j++)
				System.out.print("--");
			System.out.print(" ");
		}
		System.out.print("\n");

		System.out.print("0");
		for (i = 0; i < n; i++) {
			for (j = 0; j < obj[i].burstTime; j++)
				System.out.print("  ");
			if (obj[i].turnaroundTime > 9)
				System.out.print("\b");
			System.out.print(obj[i].turnaroundTime);

		}
		System.out.print("\n");

	}
}
