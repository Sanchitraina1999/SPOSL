import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class pass1 {

	public static void main(String[] args) {
		
		try
		{
			int flag=0,m=0;
			//File f=new File("/home/TE/31256/A3/src/input.asm");
			File f=new File("/home/ritesh/3rd(2ndSem)/SPOS/31256_A3/src/input.asm");	
			Scanner sc=new Scanner(f);
			String mnt[][]=new String[5][5];
			Vector mdt=new Vector();
			Vector kptab=new Vector();
			Vector pntab=new Vector();
			while(sc.hasNextLine())
			{
				 String split[]=new String[10];
				 String line=sc.nextLine();
				 split=line.split("[\t,' ']");
				 if(split.length==1 && split[0].equals("MACRO"))
					 flag=1;
				 else if(flag==0)
				 {
					 String l1="";
					 int flag1=0;
					 for(int i=0;i<split.length;i++)
					 {
						 int index;
//						 System.out.println(pntab);
						if(split[i].contains("&") && pntab.contains(String.valueOf(split[i].charAt(1))))
						{
							index=pntab.indexOf(String.valueOf(split[i].charAt(1))); 
							if(i>=2)
								l1+=",(P,"+(index+1)+")";
							else
								l1+="(P,"+(index+1)+")";
						}
						else
							l1+=split[i]+" ";
						
						if(split[i].equals("MEND"))
							flag=2;
					 }
						mdt.add(l1);
				 }
				 else if(flag==1)
				 {
					 pntab=new Vector();
					 int posp=0,keyp=0;
					 mnt[m][0]=split[0];
					 for(int i=1;i<split.length;i++)
					 {
						 if(split[i].contains("="))
						 {
							 keyp++;
							 String split1[]=new String[2];
							 split1=split[i].split("=");
							 String def=String.valueOf(split1[0].charAt(1));
							 String d1=split[i].substring(3);
							 kptab.add((def+"\t\t"+d1));
							 pntab.add(def);
						 }
						 else if(split[i].contains("&"))
						 {
						 	posp++;
						 	String pos=String.valueOf(split[i].charAt(1));
						 	pntab.add(pos);
						 }
					}
					 mnt[m][1]=String.valueOf(posp);
					 mnt[m][2]=String.valueOf(keyp);
					 if(m==0)
						 mnt[m][3]=mnt[m][4]=String.valueOf(1);
					 else
					 {
						 mnt[m][3]=String.valueOf(1+mdt.size());
						 mnt[m][4]=String.valueOf(kptab.size()-keyp+1);
					 }	
					 m++;
					 flag=0;
					 FileWriter pn=new FileWriter("pntab.txt");
					 for(int i=0;i<pntab.size();i++)
						 pn.write(pntab.get(i)+"\n");
					 pn.close();
				 }
			}
			String mn="",md="",kpp="";
			System.out.println("MNT:");
			mn+="Name\t#PP\t#KP\tMDTP\tKPDP\n";
			for(int j=0;j<m;j++)
			{
				for(int i=0;i<5;i++)
					mn+=mnt[j][i]+"\t";
				mn+="\n";
			}
			System.out.println(mn);
			FileWriter mntfile=new FileWriter("mnt.txt");
			mntfile.write(mn);
			System.out.println();

			System.out.println("MDT:");
			for(int i=0;i<mdt.size();i++)
				md+=(i+1)+"\t"+mdt.get(i)+"\n";
			System.out.println(md);
			FileWriter mdfile=new FileWriter("mdt.txt");
			mdfile.write(md);

			System.out.println("Last Pntab:");
			for(int i=0;i<pntab.size();i++)
				System.out.println(pntab.get(i));
			System.out.println();
			
			System.out.println("Kptab:");
			System.out.println("\tKeyparameter\tDefault_val");
			for(int i=0;i<kptab.size();i++)
				kpp+=(i+1)+"\t"+kptab.get(i)+"\n";
			System.out.println(kpp);
			FileWriter kpfile=new FileWriter("kp.txt");
			kpfile.write(kpp);
			System.out.println();

			mntfile.close();
			mdfile.close();
			kpfile.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}