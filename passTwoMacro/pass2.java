import java.util.*;
import java.io.*;

public class pass2 {

	public static String[] checkifmacro(String a){
		String result[]=new String[20];
		//File f1=new File("/home/TE/31256/A4/src/mnt.txt");
		File f1=new File("/home/ritesh/3rd(2ndSem)/SPOS/31256_A4/src/mnt.txt");
		try
		{
			Scanner sc1=new Scanner(f1);
			result[0]="false";
			while(sc1.hasNextLine())
			{
				String split1[]=new String[10];
				String line=sc1.nextLine();
				split1=line.split("\t");
				if(a.equals(split1[0]))
				{
					result[0]="true";
					for(int i=0;i<split1.length;i++)
						result[i+1]=split1[i];
					break;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vector aptab=new Vector();
		Vector checkvector=new Vector();
		try
		{
			 // File f=new File("/home/TE/31256/A4/src/input.asm");
			 // File f2=new File("/home/TE/31256/A4/src/kp.txt");
			 // File f3=new File("/home/TE/31256/A4/src/mdt.txt");
			File f=new File("/home/ritesh/3rd(2ndSem)/SPOS/31256_A4/src/input.asm");
			File f2=new File("/home/ritesh/3rd(2ndSem)/SPOS/31256_A4/src/kp.txt");
			File f3=new File("/home/ritesh/3rd(2ndSem)/SPOS/31256_A4/src/mdt.txt");
			Scanner sc=new Scanner(f);
			FileWriter fw=new FileWriter("Result.txt");
			while(sc.hasNextLine())
			{
				String split[]=new String[10];
				String check[]=new String[20];
				String line=sc.nextLine();
				split=line.split("[\t,]");
				if(split[0].equals("START"))
					System.out.println(split[0]+"\t"+split[1]+"\n");
				if(split[0].equals("END"))
					System.out.println(split[0]);
				check=checkifmacro(split[0]);
				if(check[0].equals("false"))
				{
					for(int i=0;i<split.length;i++)
						fw.write(split[i]+"\t");
					fw.write("\n");
				}
				if(check[0].equals("true"))
				{
					FileWriter fw1=new FileWriter("aptab.txt");
					int total,p,k=0;
					for(int i=0;i<split.length;i=i+2)
					{
						if(split[i+1].contains("="))
						{
							String another[]=new String[2];
							another=split[i+1].split("=");
							aptab.add(another[1]);
							char a=another[0].charAt(1);
							checkvector.add(String.valueOf(a));
						}
						else
							aptab.add(split[i+1]);	
					}
					total=Integer.parseInt(check[2])+Integer.parseInt(check[3]);
					if(total>aptab.size())
					{
						int d=aptab.size()-Integer.parseInt(check[2]);
						Scanner sc2=new Scanner(f2);
						while(sc2.hasNextLine())
						{
							String norline=sc2.nextLine();
							String keyline[]=new String[10];
							keyline=norline.split("\t");
							if(Integer.parseInt(keyline[0])>=Integer.parseInt(check[5]) && Integer.parseInt(keyline[0])<=Integer.parseInt(check[5])+Integer.parseInt(check[3])-1)	
							{
								if(!(checkvector.contains(keyline[1])))
									aptab.add(keyline[2]);
							}
						}
					}
					for(int i=0;i<aptab.size();i++)
						fw1.write(aptab.get(i)+"\n");
					Scanner sc3=new Scanner(f3);
					int flag=0;
					while(sc3.hasNextLine())
					{
						String mdtline=sc3.nextLine();
						String output[]=new String[10];
						output=mdtline.split("[\t,()]");
						if(output[0].equals(check[4])|| flag==1)
						{
							String lineoutput="";
							if(output[1].equals("MEND "))
								break;
							lineoutput+="+"+output[1]+"";
							for(int i=2;i<output.length;i++)
							{
								if(output[i].equals("P"))
								{
									lineoutput+=aptab.get(Integer.parseInt(output[i+1])-1)+"";	
									i++;
								}
								else if(output[i]!=" "||output[i]!="\t")
								{
									lineoutput+=" "+output[i]+"\t";
								}
							}
							lineoutput+="\n";
							fw.write(lineoutput);
							System.out.println(lineoutput);
							flag=1;
						}
					}
					aptab.clear();
					fw1.close();
				}
			}
			fw.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
