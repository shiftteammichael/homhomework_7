/**
 * The OrganComparator class compares the organs between the patients
 * @author Michael Hom
 * @id 112536073
 * @Recitaiton 09
 */



import java.util.Comparator;

public class OrganComparator implements Comparator<Patient> {
    @Override
    public int compare(Patient o1, Patient o2) {
        return (o1.getOrgan().compareTo(o2.getOrgan()));
    }
}
