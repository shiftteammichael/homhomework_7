/**
 * The BloodTypeComparator class compares the blood types of the patients and reorders it
 * @author Michael Hom
 * @id 112536073
 * @Recitaiton 09
 */


import java.util.Comparator;

public class BloodTypeComparator implements Comparator<Patient> {
    @Override
    public int compare(Patient o1, Patient o2) {
        return o1.getBloodType().getBloodType().compareTo(o2.getBloodType().getBloodType());
    }
}
