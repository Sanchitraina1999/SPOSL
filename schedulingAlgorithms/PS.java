import java.util.*;
public class PS {
	public static class Process implements Comparable<Process>
	{
		private  int burstTime;		//bt
		private  int arrivalTime;	//at
		private  int priority;		//pri
		public   int waitingTime = 0;
		public static int turnaroundTime = 0;	//waitingTime + burstTime
		private String name;
		static int totalTT = 0;	//total turnaround time
		
		public static ArrayList <Process> priRepeat = new ArrayList<Process>(0);	//an array-list for objects where priority is repeated
		public static int priCounter = 1;											//priority counter
		public void repeatedPriority()
		{
			
		}
		
		Process(int bt, int at, int pri, String name){
			this.setBurstTime(bt);
			this.setArrivalTime(at);
			this.setPriority(pri);
			this.setName(name);

		}
		
		public int getBurstTime() {
			return burstTime;
		}

		public void setBurstTime(int burstTime) {
			this.burstTime = burstTime;
		}
		
		public int getArrivalTime() {
			return arrivalTime;
		}

		public void setArrivalTime(int arrivalTime) {
			this.arrivalTime = arrivalTime;
		}

		
		public int getPriority() {
			return priority;
		}

		public void setPriority(int priority) {
			this.priority = priority;
		}
		
		public int getWaitingTime() {
			return waitingTime;
		}

		public void setWaitingTime(int waitingTime) {
			this.waitingTime = waitingTime;
		}
		
		public int compareTo(Process p)
		{
			return this.getPriority() - p.getPriority();
		}
		

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		/*public static void repeatPriHandeling(Process Arr[])
		{
			for(int i = 0; i < 4; i++){
				for(int j = i + 1; j < 4; j++){
					if(Arr[i].getPriority() == Arr[j].getPriority()){
						Process.priCounter++;
						if(Process.priCounter == 2){
							Process.priRepeat.add(Arr[i]);
							Process.priRepeat.add(Arr[j]);
							break;
						}
						else if(Process.priCounter > 2){
							Process.priRepeat.add(Arr[j]);
						}
					}
				}
			}
			 	
			
		}*/
		public static void generateWT(Process arr1[], Process arr2[])	//arr1 is ProcessArrAT -> i
		{																//arr2 is ProcessArr   -> j
			for(int i = 0; i < 4; i++){		//waiting time
				for(int j = 0; j < 4; j++){
					if(arr1[i].name != arr2[j].name){					//repeat till find the process that is being processed
						arr1[i].waitingTime += arr2[j].getBurstTime(); 
					}else{break;}
				}
				
			}
			
		}
		public static void generateTAT(Process arr1[])
		{
			for(int i = 0; i < 4; i++){		//TAT: turnaround time
				arr1[i].turnaroundTime = arr1[i].waitingTime + arr1[i].getBurstTime();
				System.out.println("Turnarround Time for " + arr1[i].name + ": " + arr1[i].turnaroundTime);
				Process.totalTT += arr1[i].turnaroundTime; 
			}
		}
	}
	public static void main(String[] args)
	{
		Process ProcessArr[] = new Process[4];		//array of objects, of size 4, will be sorted according to the priority
		Process ProcessArrAT[] = new Process[4];	//will be sorted according to the arrival time
		ProcessArrAT[0] = new Process(2, 0, 4, "p1");
		ProcessArrAT[1] = new Process(2, 2, 1,"p2");
		ProcessArrAT[2] = new Process(2, 4, 3,"p3");
		ProcessArrAT[3] = new Process(2, 6, 2,"p4");
		
		ProcessArr[0] = new Process(2, 0, 4, "p1");	//Process(int bt, int at, int pri, name)
		ProcessArr[1] = new Process(2, 2, 1,"p2");
		ProcessArr[2] = new Process(2, 4, 3,"p3");
		ProcessArr[3] = new Process(2, 6, 2,"p4");
		Arrays.sort(ProcessArr);//priority sorting
		Arrays.sort(ProcessArrAT, new Comparator<Process>(){	//arrival time sorting
			public int compare(Process one, Process two)
			{
				return one.arrivalTime - two.arrivalTime;
			}
		});
		Process.generateWT(ProcessArrAT, ProcessArr);
		
		int totalWT = 0;
		System.out.println("Sequence of excution: ");
		for(int i = 0; i < 4; i++)
		{
			System.out.println(ProcessArr[i].name);
		}
		
		System.out.println();

		for(int i = 0; i < 4; i++)
		{
			System.out.println(ProcessArrAT[i].name + " ");
			System.out.println("burst time: " + ProcessArrAT[i].burstTime + " ");
			System.out.println("arrival time: " + ProcessArrAT[i].arrivalTime + " ");
			System.out.println("priority: " + ProcessArrAT[i].priority + " ");	
			System.out.println("Waiting Time for process " + ProcessArrAT[i].name +": "+ ProcessArrAT[i].waitingTime);
			System.out.println();

			totalWT += ProcessArrAT[i].waitingTime;
		}
		
		Process.generateTAT(ProcessArrAT);
		System.out.println();
		
		int avrgWT = totalWT/4;
		int avrgTT = Process.totalTT/4;
		
		System.out.println("Average Waiting Time: " + avrgWT);
		System.out.println("Average Turnaround Time: " + avrgTT);
	}
}

/*/