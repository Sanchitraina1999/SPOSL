import java.util.*;
import java.io.*;

public class a1 {

	public static void main(String[] args) {

		LinkedHashMap<String, Integer> ad = new LinkedHashMap<String, Integer>();
		LinkedHashMap<String, Integer> is = new LinkedHashMap<String, Integer>();
		LinkedHashMap<String, Integer> dl = new LinkedHashMap<String, Integer>();
		LinkedHashMap<String, Integer> reg = new LinkedHashMap<String, Integer>();
		LinkedHashMap<String, Integer> lt = new LinkedHashMap<String, Integer>();
		LinkedHashMap<String, Integer> symbol = new LinkedHashMap<String, Integer>();
		Vector literal = new Vector();
		Vector literalvalue = new Vector();
		LinkedHashMap<Integer, Integer> pool = new LinkedHashMap<Integer, Integer>();
		String ic[] = new String[30];

		ad.put("START", 1);
		ad.put("END", 2);
		ad.put("ORIGIN", 3);
		ad.put("EQU", 4);
		ad.put("LTORG", 5);

		is.put("STOP", 0);
		is.put("ADD", 1);
		is.put("SUB", 2);
		is.put("MULT", 3);
		is.put("MOVER", 4);
		is.put("MOVEM", 5);
		is.put("COMP", 6);
		is.put("BC", 7);
		is.put("DIV", 8);
		is.put("READ", 9);
		is.put("PRINT", 10);

		dl.put("DC", 1);
		dl.put("DS", 2);

		lt.put("LT", 1);
		lt.put("LE", 2);
		lt.put("EQ", 3);
		lt.put("GT", 4);
		lt.put("GE", 5);
		lt.put("ANY", 6);

		reg.put("AREG", 1);
		reg.put("BREG", 2);
		reg.put("CREG", 3);
		reg.put("DREG", 4);
		int k = 0;
		Vector lc = new Vector();
		int addr[] = new int[30];
		int f1 = 1, z = 0, icc = 0;
		try {
			File f = new File("/home/imsotired/Desktop/TE/SPOSL/Assembler/demo.asm");
			Scanner sc = new Scanner(f);
			int ltorgcounter = 0;
			while (sc.hasNextLine()) {
				int l = 0;
				String line = sc.nextLine();
				if (line.length() == 0)
					break;
				String split[] = new String[20];
				split = line.split("[\t,]");
				// System.out.println(split[1]);
				for (int i = 0; i < split.length; i++) {
					if (split[i].length() != 0)
						l++;
				}
				if (split[1].equals("START")) {
					lc.add(0);
					if (split[2] != null)
						z = Integer.parseInt(split[2]);
					else
						z = 0;
					addr[k] = z;
					ic[icc] = "(AD,1) (C," + z + ")";
				} else if (split[1].equals("END")) {
					lc.add(0);
					ic[icc] = "(AD,2)";
					for (int i = 0; i < literal.size(); i++) {
						if ((Integer) literalvalue.get(i) == -1) {
							literalvalue.remove(i);
							literalvalue.insertElementAt(addr[k - 1], i);
							lc.add(addr[k - 1]);
							addr[k] = addr[k - 1] + 1;
							icc++;
							ic[icc] = "(L," + (i + 1) + ") ";
						}
					}
					pool.put(f1, ltorgcounter + 1);
				} else if (split[1].equals("STOP")) {
					lc.add(addr[k - 1]);
					addr[k] = addr[k - 1] + 1;
					ic[icc] = "(IS,0)";
				} else if (split[1].equals("LTORG")) {
					pool.put(f1, ltorgcounter + 1);
					lc.add(0);
					ic[icc] = "(AD,5)";
					int ltorgcounter1 = ltorgcounter;
					for (int i = ltorgcounter1; i < literal.size(); i++) {
						literalvalue.remove(i);
						literalvalue.insertElementAt(addr[k - 1], i);
						lc.add(addr[k - 1]);
						addr[k] = addr[k - 1] + 1;
						icc++;
						ic[icc] = "(L," + (i + 1) + ") ";
						ltorgcounter++;
						k++;
					}
					k--;
					f1++;
				} else {
					if (l == 4) {
						if (!(symbol.containsKey(split[0])))
							symbol.put(split[0], addr[k - 1]);
						else
							symbol.replace(split[0], addr[k - 1]);
						if (ad.containsKey(split[1])) {
							lc.add(0);
							k--;
						} else if (is.containsKey(split[1])) {
							lc.add(addr[k - 1]);
							addr[k] = addr[k - 1] + 1;
							ic[icc] = "(IS" + "," + is.get(split[1]) + ") ";
						} else if (dl.containsKey(split[1])) {
							if (split[1].equals("DC")) {
								lc.add(addr[k - 1]);
								addr[k] = addr[k - 1] + 1;
							} else {
								lc.add(addr[k - 1]);
								addr[k] = addr[k - 1] + Integer.parseInt(split[2]);
							}
							ic[icc] = "(DL" + "," + dl.get(split[1]) + ") (C," + split[2] + ")";
						}
						if (reg.containsKey(split[2]))
							ic[icc] += "(" + reg.get(split[2]) + ") ";
						else if (symbol.containsKey(split[2])) {
							split[2] = split[2].replaceAll("\\s+", "");
							if (!(symbol.containsKey(split[2])))
								symbol.put(split[2], -1);
							int xy1 = 1;
							for (String s : symbol.keySet()) {
								if (s.equals(split[2]))
									break;
								xy1++;
							}
							ic[icc] += "(S," + xy1 + ") ";
						}
						if (split[3].contains("=")) {
							int poolindex;
							if (f1 == 1)
								poolindex = 0;
							else
								poolindex = pool.get(f1 - 1);
							int poolflag = 0;
							for (int i = poolindex; i < literal.size(); i++) {
								if (literal.get(i).equals(split[3])) {
									poolflag = 1;
									break;
								}
							}
							if (poolflag == 0) {
								literal.add(split[3]);
								literalvalue.add(-1);
							}
							int xy1;
							for (xy1 = ltorgcounter; xy1 < literal.size(); xy1++) {
								if (literal.get(xy1).equals(split[0]))
									break;
							}
							ic[icc] += "(L," + (xy1 + 1) + ") ";
						} else {
							split[3] = split[3].replaceAll("\\s+", "");
							if (!(symbol.containsKey(split[3])))
								symbol.put(split[3], -1);
							int xy1 = 1, yx1;
							for (String s : symbol.keySet()) {
								if (s.equals(split[3]))
									break;
								xy1++;
							}
							ic[icc] += "(S," + xy1 + ") ";
						}
					} else if (l == 3) {
						if (split[0].length() != 0) {
							if (symbol.containsKey(split[0]))
								symbol.replace(split[0], addr[k - 1]);
							else
								symbol.put(split[0], addr[k - 1]);
						}
						if (ad.containsKey(split[1])) {
							lc.add(0);
							int flag = 0;
							String a[] = new String[2];
							if (split[2].contains("+")) {
								a = split[2].split("\\+");
								flag = 1;
							} else {
								a = split[2].split("-");
								flag = -1;
							}
							// System.out.println(symbol);
							int aa = symbol.get(a[0]);
							// System.out.println(aa);
							int addition = Integer.parseInt(a[1]);
							if (flag == 1) {
								symbol.put(split[0], aa + addition);
								addr[k] = addr[k - 1] + 1;
							} else if (flag == -1) {
								symbol.put(split[0], aa - addition);
								addr[k] = addr[k - 1] + 1;
							}
							k--;
							int xy = 1;
							for (String s : symbol.keySet()) {
								if (s.equals(a[0]))
									break;
								xy++;
							}
							if (flag == 1)
								ic[icc] = "(AD,4) (S," + xy + ")+" + addition;
							else
								ic[icc] = "(AD,4) (S," + xy + ")-" + addition;
						} else if (is.containsKey(split[1])) {
							lc.add(addr[k - 1]);
							addr[k] = addr[k - 1] + 1;
							if (ic[icc] == null)
								ic[icc] = "(IS" + "," + is.get(split[1]) + ") ";
							else
								ic[icc] += "(IS" + "," + is.get(split[1]) + ") ";
						} else if (dl.containsKey(split[1])) {
							if (split[1].equals("DC")) {
								lc.add(addr[k - 1]);
								addr[k] = addr[k - 1] + 1;
							} else {
								lc.add(addr[k - 1]);
								addr[k] = addr[k - 1] + Integer.parseInt(split[2]);
							}
							if (ic[icc] == null)
								ic[icc] = "(DL" + "," + dl.get(split[1]) + ") (C," + split[2] + ")";
							else
								ic[icc] += "(DL" + "," + dl.get(split[1]) + ") (C," + split[2] + ")";
						}
						if (reg.containsKey(split[2]))
							ic[icc] += "(" + reg.get(split[2]) + ") ";
						else if (symbol.containsKey(split[2])) {
							split[2] = split[2].replaceAll("\\s+", "");
							if (!(symbol.containsKey(split[2])))
								symbol.put(split[2], -1);
							int xy = 1;
							for (String s : symbol.keySet()) {
								if (s.equals(split[2]))
									break;
								xy++;
							}
							ic[icc] += "(S," + xy + ") ";
						}
						if (split[0].length() == 0) {
							if (split[3].contains("=")) {
								int poolindex;
								if (f1 == 1)
									poolindex = 0;
								else
									poolindex = pool.get(f1 - 1);
								int poolflag = 0;
								for (int i = poolindex; i < literal.size(); i++) {
									if (literal.get(i).equals(split[3])) {
										poolflag = 1;
										break;
									}
								}
								if (poolflag == 0) {
									literal.add(split[3]);
									literalvalue.add(-1);
								}
								int xy;
								for (xy = ltorgcounter; xy < literal.size(); xy++) {
									if (literal.get(xy).equals(split[3]))
										break;
								}
								ic[icc] += "(L," + (xy + 1) + ") ";
							} else {
								split[3] = split[3].replaceAll("\\s+", "");
								if (!(symbol.containsKey(split[3])))
									symbol.put(split[3], -1);
								int xy = 1;
								for (String s : symbol.keySet()) {
									if (s.equals(split[3]))
										break;
									xy++;
								}
								ic[icc] += "(S," + xy + ") ";
							}
						}
					} else if (l == 2) {
						if (ad.containsKey(split[1])) {
							lc.add(0);
							int flag = 0;
							String a[] = new String[2];
							if (split[2].contains("+")) {
								a = split[2].split("\\+");
								flag = 1;
							} else {
								a = split[2].split("-");
								flag = -1;
							} // String a=String.valueOf(split[2].charAt(0));
							int aa = symbol.get(a[0]);
							int addition = Integer.parseInt(a[1]);
							if (flag == 1)
								addr[k] = aa + addition;
							else if (flag == -1)
								addr[k] = aa - addition;
							int xy = 1;
							for (String s : symbol.keySet()) {
								if (s.equals(a[0]))
									break;
								xy++;
							}
							if (flag == 1)
								ic[icc] = "(AD,3) (S," + xy + ")+" + addition;
							else
								ic[icc] = "(AD,3) (S," + xy + ")-" + addition;
						} else if (is.containsKey(split[1])) {
							lc.add(addr[k - 1]);
							addr[k] = addr[k - 1] + 1;
							ic[icc] = "(IS" + "," + is.get(split[1]) + ") ";
						} else if (dl.containsKey(split[1])) {
							if (split[1].equals("DC")) {
								lc.add(addr[k - 1]);
								addr[k] = addr[k - 1] + 1;
							} else {
								lc.add(addr[k - 1]);
								int cont = 0;
								addr[k] = addr[k - 1] + Integer.parseInt(split[2]);
							}
							ic[icc] = "(DL" + "," + dl.get(split[1]) + ") (C," + split[2] + ")";
						}
						if (reg.containsKey(split[2]))
							ic[icc] += "(" + reg.get(split[2]) + ") ";
						else if (symbol.containsKey(split[2])) {
							split[2] = split[2].replaceAll("\\s+", "");
							if (!(symbol.containsKey(split[2])))
								symbol.put(split[2], -1);
							int xy = 1;
							for (String s : symbol.keySet()) {
								if (s.equals(split[2]))
									break;
								xy++;
							}
							ic[icc] += "(S," + xy + ") ";
						}

					}
				}
				// System.out.println(ic[icc]);
				k++;
				icc++;
			}
			String IC = null;
			for (int i = 0; i < ic.length; i++) {
				if (ic[i] != null) {
					if (IC == null)
						IC = lc.get(i) + "\t" + ic[i] + "\n";
					else
						IC += lc.get(i) + "\t" + ic[i] + "\n";
				}
			}
			System.out.println("LC\tIC:");
			System.out.println(IC);
			System.out.println("********************************************************************************");
			System.out.println("Symbol Table:");
			System.out.println(symbol.toString());
			System.out.println("********************************************************************************");
			System.out.println("Literal Table");
			System.out.println(literal);
			System.out.println(literalvalue);
			System.out.println("********************************************************************************");
			System.out.println("Pool Table");
			System.out.println(pool.toString());

			FileWriter fs = new FileWriter("Assembler/Symbol.txt");
			FileWriter fl = new FileWriter("Assembler/Literal.txt");
			FileWriter fp = new FileWriter("Assembler/Pool.txt");
			FileWriter fic = new FileWriter("Assembler/IC.txt");
			fic.write(IC);
			for (String s : symbol.keySet())
				fs.write(s + "\t" + symbol.get(s) + "\n");
			for (int i = 0; i < literal.size(); i++)
				fl.write(literal.get(i) + "\t" + literalvalue.get(i) + "\n");
			for (int s : pool.keySet())
				fp.write(s + "\t" + pool.get(s) + "\n");
			fs.close();
			fl.close();
			fp.close();
			fic.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
