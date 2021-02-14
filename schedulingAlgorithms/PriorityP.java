public 
    System.out.println("-------------------");
		System.out.println("Priority Scheduling");
		System.out.println("-------------------");

		pro=procopy.clone();
		arr=arrcopy.clone();
		oldburst=oldburstcopy.clone();

		ready=new LinkedList<>();
		running=new LinkedList<>();
		for(int i=1;i<=n;i++)
			m.replace(pro[i],1);
		ct1=new Vector();

		Arrays.fill(newburst,-1);

		for(int i=1;i<=n;i++)
		{
			for(int j=i+1;j<=n;j++)
			{
				int temp,temp2;
				String temp1;
				if((arr[i]>arr[j])||((arr[i]==arr[j])&&priority[i]>priority[j]))
				{
					// System.out.println(priority[i]+" "+priority[j]);
					temp=arr[i];
					arr[i]=arr[j];
					arr[j]=temp;
					temp=oldburst[i];
					oldburst[i]=oldburst[j];
					oldburst[j]=temp;
					temp1=pro[i];
					pro[i]=pro[j];
					pro[j]=temp1;
					temp2=priority[i];
					priority[i]=priority[j];
					priority[j]=temp2;
				}
			}
		}
	
		min=1000;min1=1000;
		for(int i=1;i<=n;i++)
		{
			if(min>arr[i])
				min=arr[i];
		}
		for(int i=1;i<=n;i++)
		{
			if(arr[i]==min)
			{
				m.replace(pro[i],-1);
				ready.add(pro[i]);
			}
		}
		prev=min;s=0;count=0;
		avgtat=0;avgwt=0;
		mi=1000;
		while(!ready.isEmpty())
		{
			if(ready.peek().equals("NULL"))
			{
				running.add(ready.peek());
				prev=mi;
			}
			else
			{
				running.add(ready.peek());
				int in=index(pro,ready.peek());
				prev=prev+oldburst[in];
			}
			ct1.add(prev);
			int againmin=1000;
			int sort1[]=new int[n+1];
			String sort2[]=new String[n+1];
			int sortincre=1,sortflag=0;
			for(int i=1;i<=n;i++)
			{
				if(m.get(pro[i])==1)
				{
					sortflag=1;
					break;
				}
			}
			if(sortflag==1)
			{
				for(int i=1;i<=n;i++)
				{
					if(arr[i]<=prev && m.get(pro[i])==1 && newburst[i]!=0)
					{
						sort1[sortincre]=priority[i];
						sort2[sortincre]=pro[i];
						sortincre++;
						
					}
				}
				
				int sortmin=1000,sortindex=0;
				for(int i=0;i<=n;i++)
				{
					if(sortmin>sort1[i]&&sort1[i]!=0)
					{
						sortmin=sort1[i];
						sortindex=i;
					}
				}
				// for(int i=1;i<=n;i++)
				// 	System.out.println(sort1[i]+"yes"+sort2[i]);
				//System.out.println(sortindex);
				int sortindex2=index(pro,sort2[sortindex]);
				//System.out.println(ready);
				m.replace(pro[sortindex2],-1);
				ready.add(sort2[sortindex]);
			}
			ready.remove();
			mi=1000;
			int flag1=0;
			if(ready.size()<=0)
			{
				for(int i=1;i<=n;i++)
				{
					if(m.get(pro[i])==1)
					{
						flag1=1;
						if(mi>arr[i])
							mi=arr[i];
					}
				}
				if(flag1==1)
				{
					ready.add("NULL");
				}
			}
		}
		// System.out.println(running);
		// System.out.println(ct1);
		for(int i=1;i<=n;i++)
		{	
			s=0;
			String cmp="P"+i;
			for(String j:running)
			{
				if(j.equals(cmp))
					count=s;
				s++;
			}
			int z=(int)ct1.get(count);
			ct[i]=z;
		}
		for(int i=1;i<=n;i++)
		{
			tat[i]=ct[i]-arrcopy[i];
			wt[i]=tat[i]-oldburstcopy[i];
			System.out.println(tat[i]+" "+wt[i]);
			avgtat+=tat[i];
			avgwt+=wt[i];
		}
		System.out.println("Average TAT: "+(avgtat/n));
		System.out.println("Average WT: "+(avgwt/n));
    }