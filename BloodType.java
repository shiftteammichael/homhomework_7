import java.io.Serializable;

/**
 * The BloodType class contains a string member variable bloodType to denote a specific blood type
 * @author Michael Hom
 * @Recitation 09
 * @id 112536073
 */

public class BloodType implements Serializable {
    String bloodType;

    /**
     * This is the default constructor of the BloodType class
     */
    public BloodType(){

    }

    /**
     * This is another constructor that takes the name and makes it a blood Type object
     * @param name
     */
    public BloodType(String name){
        bloodType=name;
    }

    /**
     * This method determines whether two blood types are compatible returning true if they are
     * and false otherwise
     * @param recipient
     * @param donor
     * @return
     */
    public static boolean isCompatible(BloodType recipient, BloodType donor){
        if(donor.getBloodType().equals("O")){
            return true;
        }
        if(donor.getBloodType().equals("A") && recipient.getBloodType().equals("A")){
            return true;
        }
        if(donor.getBloodType().equals("A") && recipient.getBloodType().equals("AB")){
            return true;
        }
        if(donor.getBloodType().equals("B") && recipient.getBloodType().equals("B")){
            return true;
        }
        if(donor.getBloodType().equals("B") && recipient.getBloodType().equals("AB")){
            return true;
        }
        if(donor.getBloodType().equals("AB") && recipient.getBloodType().equals("AB")){
           return true;
        }
       else{
           return false;
        }

    }

    /**
     * This is a getter method that gets the blood type
     * @return bloodType
     */
    public String getBloodType() {
        return bloodType;
    }

}
