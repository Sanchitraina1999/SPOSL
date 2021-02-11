import java.util.*;

public class PriorityP {
    class Proces {
        int at, bt, pri, pno;
        Proces(int pno, int at, int bt, int pri) {
            this.pno = pno;
            this.pri = pri;
            this.at = at;
            this.bt = bt;
        }
    }

    class GChart {
        int pno, stime, ctime, wtime, ttime;
    }

    // user define comparative method (first arrival first serve,
    // if arrival time same then heigh priority first)
    class MyComparator implements Comparator {

        public int compare(Object o1, Object o2) {

            Proces p1 = (Proces) o1;
            Proces p2 = (Proces) o2;
            if (p1.at < p2.at)
                return (-1);

            else if (p1.at == p2.at && p1.pri > p2.pri)
                return (-1);

            else
                return (1);
        }
    }

    // class to find Gantt chart
class FindGantChart { 
    void findGc(LinkedList queue) 
    { 
  
        // initial time = 0 
        int time = 0; 
  
        // priority Queue sort data according 
        // to arrival time or priority (ready queue) 
        TreeSet prique = new TreeSet(new MyComparator()); 
  
        // link list for store processes data 
        LinkedList result = new LinkedList(); 
  
        // Proces in ready queue from new state queue 
        while (queue.size() > 0) 
            prique.add((Proces)queue.removeFirst()); 
  
        Iterator it = prique.iterator(); 
  
        // time set to according to first Proces 
        time = ((Proces)prique.first()).at; 
  
        // scheduling Proces 
        while (it.hasNext()) { 
  
            // dispatcher dispatch the 
            // Proces ready to running state 
            Proces obj = (Proces)it.next(); 
  
            GChart gc1 = new GChart(); 
            gc1.pno = obj.pno; 
            gc1.stime = time; 
            time += obj.bt; 
            gc1.ctime = time; 
            gc1.ttime = gc1.ctime - obj.at; 
            gc1.wtime = gc1.ttime - obj.bt; 
  
            /// store the exxtreted Proces 
            result.add(gc1); 
        } 
  
        // create object of output class and call method 
        new ResultOutput(result); 
    } 
}