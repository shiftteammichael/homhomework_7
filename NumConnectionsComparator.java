/**
 * The NumConnectionsComparator class compares the number of connections of each patient and
 * reorders the list
 * @author Michael Hom
 * @id 112536073
 * @Recitaiton 09
 */


import java.util.Comparator;

public class NumConnectionsComparator implements Comparator<Patient> {
    @Override
    public int compare(Patient o1, Patient o2) {
        if(o1.getIDS().size()==o2.getIDS().size()){
            return 0;
        }
        else if(o1.getIDS().size()>o2.getIDS().size()){
            return 1;
        }
        else{
            return -1;
        }
    }
}
