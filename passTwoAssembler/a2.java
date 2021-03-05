import java.util.*;
import java.io.*; 
public class a2 {
	public static void main(String[] args) {
		try{
			File fic=new File("/home/imsotired/Desktop/TE/SPOSL/passTwoAssembler/IC.txt");
			Scanner sc=new Scanner(fic);
			while(sc.hasNextLine())
			{
				String mcode[]=new String[6];
				for(int i=0;i<mcode.length;i++)
					mcode[i]=String.valueOf(0);
				String text=sc.nextLine();
				if(text.length()==0)
					break;
				String split[]=new String[20];
				split=text.split("[\t,\\(\\)]");
				int flag=0;
				//System.out.println(split[1]+" ");
				if(split[2].equals("AD"))
					flag=1;
				else if(split[2].equals("DL"))
					flag=2;
				else if(split[2].equals("IS"))
					flag=3;
				else if(split[2].equals("L"))
					flag=4;
				if (flag==4)
				{
					String fname="Literal.txt";
					int y=0;
					File fsm=new File(fname);
					Scanner scsym=new Scanner(fsm);
					while(scsym.hasNextLine())
					{
						y++;
						String s1=scsym.nextLine();
						if(s1.length()==0)
							break;
						if(y==Integer.parseInt(split[3]))
						{	
							String sym[]=new String[2];
							sym=s1.split("\t");
							int number=Integer.parseInt(sym[1]);
							int k=5;
							while(number>0)
							{
								mcode[k]=String.valueOf(number%10);
								number/=10;
								k--;
							}
						}
					}	
					System.out.print(split[0]+"\t");
						for(int i=0;i<mcode.length;i++)
							System.out.print(mcode[i]+" ");
						System.out.println();
				}
				if(flag==1)
					continue;
				if(flag==2)
				{
					if(split[3].equals("1"))
					{
						mcode[5]=split[6];
						System.out.print(split[0]+"\t");
						for(int i=0;i<mcode.length;i++)
							System.out.print(mcode[i]+" ");
						System.out.println();
					}	
					else
					{
						for(int i=0;i<mcode.length;i++)
							mcode[i]="-";
						int loop=Integer.parseInt(split[6]);
						for(int i=0;i<loop;i++)
						{	
							int lc=Integer.parseInt(split[0])+i;
							System.out.print(lc+"\t");
							for(int j=0;j<mcode.length;j++)
								System.out.print(mcode[j]+" ");
							System.out.println();
						}
					}
					
				}
				else if(flag==3)
				{
					if(split[3].equals("0"))
					{}	
					else if(split[3].equals("10"))
					{
						int k1=1,number1=10;
						while(number1>0)
						{
							mcode[k1]=String.valueOf(number1%10);
							number1/=10;
							k1--;
						}
						String fname="";
						if(split[5].equals("S"))
							fname="Symbol.txt";
						else if(split[5].equals("L"))
							fname="Literal.txt";
						int y=0;
						File fsm=new File(fname);
						Scanner scsym=new Scanner(fsm);
						while(scsym.hasNextLine())
						{
							y++;
							String s1=scsym.nextLine();
							if(s1.length()==0)
								break;
							if(y==Integer.parseInt(split[6]))
							{	
								String sym[]=new String[2];
								sym=s1.split("\t");
								int number=Integer.parseInt(sym[1]);
								int k=5;
								while(number>0)
								{
									mcode[k]=String.valueOf(number%10);
									number/=10;
									k--;
								}
							}
						}	
					}
					else
					{
						mcode[1]=split[3];
						if(split[5].equals("1")||split[5].equals("2")||split[5].equals("3")||split[5].equals("4"))
							mcode[2]=split[5];
						else
							mcode[2]=String.valueOf(0);
						String fname="";
						if(split[7].equals("S"))
							fname="Symbol.txt";
						else if(split[7].equals("L"))
							fname="Literal.txt";
						int y=0;
						File fsm=new File(fname);
						Scanner scsym=new Scanner(fsm);
						while(scsym.hasNextLine())
						{
							y++;
							String s1=scsym.nextLine();
							if(s1.length()==0)
								break;
							if(y==Integer.parseInt(split[8]))
							{	
								String sym[]=new String[2];
								sym=s1.split("\t");
								int number=Integer.parseInt(sym[1]);
								int k=5;
								while(number>0)
								{
									mcode[k]=String.valueOf(number%10);
									number/=10;
									k--;
								}
							}
						}	
					}
					System.out.print(split[0]+"\t");
					for(int i=0;i<mcode.length;i++)
						System.out.print(mcode[i]+" ");
					System.out.println();
				}	
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			sc.close();
		}

	}
}