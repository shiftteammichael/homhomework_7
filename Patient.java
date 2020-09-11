/**
 * The Patient Class represents an active organ donor in the database
 * @author Michael Hom
 * @Recitation 09
 * @id 112536073
 * 
 */


import java.io.Serializable;
import java.util.ArrayList;

public class Patient implements Comparable, Serializable {
    String name;
    String organ;
    int age;
    BloodType bloodType;
    int ID;
    boolean isDonor;
    ArrayList<Integer>IDS=new ArrayList<>();

    /**
     * This is a getter method that I implemented to get either the Donor IDs or recipients IDs of the patient
     * @return IDS
     */
    public ArrayList<Integer> getIDS() {
        return IDS;
    }

    /**
     * This is the default constructor of the Patient Class
     */
    public Patient(){

    }

    /**
     * This method I implemented that prints out the Donor IDS or recipients IDS of the patient
     * @return
     */
    public String printIDS(){
        String yer="";
        for(int i=0; i<IDS.size();i++){
            if(i==IDS.size()-1){
                yer+=IDS.get(i);
            }
            else{
                yer+=IDS.get(i)+", ";
            }
        }
        return yer;
    }

    /**
     * This is another constructor that takes in these parameters and sets the patient
     * object to these parameters
     * @param ID
     * @param name
     * @param age
     * @param organ
     * @param bloodType
     */
    public Patient(int ID, String name, int age, String organ,BloodType bloodType){
        this.ID=ID;
        this.name=name;
        this.age=age;
        this.organ=organ;
        this.bloodType=bloodType;
    }

    /**
     * This is a getter method that check if the patient is a donor
     * @return
     */
    public boolean isDonor() {
        return isDonor;
    }

    /**
     * This is a setter method that sets the patient to a donor
     * @param donor
     */
    public void setDonor(boolean donor) {
        isDonor = donor;
    }

    /**
     * This is a getter method that gets the id of the patient
     * @return
     */
    public int getID() {
        return ID;
    }

    /**
     * This is a setter method that sets the id of the patient
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * This is a getter method that gets the bloodtype of the patient
     * @return bloodType
     */
    public BloodType getBloodType() {
        return bloodType;
    }

    /**
     * This is a setter method that sets the bloodtype of the patient
     * @param bloodType
     */
    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    /**
     * This is a getter method that gets the age of the patient
     * @return
     */
    public int getAge() {
        return age;
    }

    /***
     * This is a setter method that sts the age of the patient
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * This is a getter method that gets the organ of the patient
     * @return
     */
    public String getOrgan() {
        return organ;
    }

    /**
     * This is a getter method that gets the name of the patient
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * This is a method implemented using comparator and compares the
     * id of the patient
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
       Patient p= (Patient) o;
       if(this.ID==p.ID){
           return 0;
       }
       else if(this.ID>p.ID){
           return 1;
       }
       else{
           return -1;
       }
    }

    /**
     * This method returns a string representation of the patient
     * @return
     */
    public String toString(){
        return ID +", "+name+", "+age+ ", "+organ+", "+bloodType;
    }

}
