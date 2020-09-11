/**
 * The TransplantDriver class contains an adjacency matrix for organ donors and recipients
 * @author Michael Hom
 * @id 112536073
 * @Recitaiton 09
 */



import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class TransplantGraph implements Serializable {
    ArrayList<Patient> donors;
    ArrayList<Patient> recipients;
    public static final int MAX_PATIENTS=100;
    boolean connections [][];

    /**
     * This is a constructor that takes in the array lists created from the static file
     * @param donors
     * @param recipients
     */
    public TransplantGraph(ArrayList<Patient>donors,ArrayList<Patient>recipients){
        this.donors=donors;
        this.recipients=recipients;
        connections=new boolean[donors.size()][recipients.size()];
    }

    /**
     * This is a constructor that sets everything up
     */
    public TransplantGraph(){
        donors=new ArrayList<>();
        recipients=new ArrayList<>();
        connections=new boolean[MAX_PATIENTS][MAX_PATIENTS];
    }

    /**
     * This method creates and returns a new Transplant object from the two files
     * @param donorFile
     * @param recipientFile
     * @return
     * @throws IOException
     */
    public static TransplantGraph buildFromFiles(String donorFile, String recipientFile) throws IOException {
        FileInputStream fis = new FileInputStream(donorFile);
        InputStreamReader instream = new InputStreamReader(fis);
        BufferedReader reader = new BufferedReader(instream);
        String data1;
        ArrayList<Patient>donor1=new ArrayList<>();
        ArrayList<Patient>recipient1=new ArrayList<>();
        while ((data1 = reader.readLine()) != null) {
            String[] tokens = data1.split(",");
            int id= Integer.parseInt(tokens[0]);
            String name= tokens[1].trim();
            int age1=Integer.parseInt(tokens[2].replaceAll("\\s+",""));
            String organ=tokens[3].replaceAll("\\s+","");
            String cap=organ.substring(0,1).toUpperCase()+organ.substring(1);
            String bloodType=tokens[4].replaceAll("\\s+","");
            BloodType yer= new BloodType(bloodType);
            Patient donor= new Patient(id,name,age1,cap,yer);
            donor.setBloodType(yer);
            donor.setDonor(true);
            donor1.add(donor);
        }
        FileInputStream Yis = new FileInputStream(recipientFile);
        InputStreamReader Yinstream = new InputStreamReader(Yis);
        BufferedReader Yreader = new BufferedReader(Yinstream);
        String data2;
        while ((data2 = Yreader.readLine()) != null) {
            String[] in = data2.split(",");
            int id= Integer.parseInt(in[0]);
            String name= in[1].trim();
            int age1=Integer.parseInt(in[2].replaceAll("\\s+",""));
            String organ=in[3].replaceAll("\\s+","");
            String cap=organ.substring(0,1).toUpperCase()+organ.substring(1);
            String bloodType=in[4].replaceAll("\\s+","");
            BloodType yer= new BloodType(bloodType);
            Patient recipient= new Patient(id,name,age1,cap,yer);
            recipient.setBloodType(yer);
            recipient.setDonor(false);
            recipient1.add(recipient);
        }
        return new TransplantGraph(donor1,recipient1);
    }

    /**
     * This method prints all organ donors' information in a neatly formatted table
     */
    public void printAllDonors(){
        String header= String.format("%-26s %19s %18s %16s %20s %20s" , "Index \t|" , "Recipient Name \t\t\t|" , "Age \t\t|" , "Organ Needed\t\t|", "Blood Type\t\t|" , "Donor IDS" );
        String yuh= "\n --------------------------------------------------------------------------------------------------------------------------------------------------";
        String table="";
        for(int i=0; i<donors.size();i++){
            String k=i+ "\t\t| ";
            String age=donors.get(i).getID()+ "\t\t| ";
            BloodType bloodType=donors.get(i).getBloodType();
            String mike= bloodType.getBloodType() + "\t\t| ";
            String lol=donors.get(i).printIDS();
            String name=donors.get(i).getName()+ "\t \t| ";
            String organ=donors.get(i).getOrgan() + "\t \t| ";
            table+=String.format("\n %-26s %19s %19s %16s %20s %20s",k,name,age, organ,mike,lol);
        }
       System.out.println(header+ yuh+ table);
    }

    /**
     * This is another method i implemented that prints the donor information in a neatly
     * formatted table when comparing
     */
    public void printAllDonorsHelper(){
        String header= String.format("%-26s %19s %18s %16s %20s %20s" , "Index \t|" , "Recipient Name \t\t\t|" , "Age \t\t|" , "Organ Needed\t\t|", "Blood Type\t\t|" , "Donor IDS" );
        String yuh= "\n --------------------------------------------------------------------------------------------------------------------------------------------------";
        String table="";
        for(int i=0; i<donors.size();i++){
            String k=donors.get(i).getID()+ "\t\t| ";
            String age=donors.get(i).getID()+ "\t\t| ";
            BloodType bloodType=donors.get(i).getBloodType();
            String yerr= bloodType.getBloodType() + "\t\t| ";
            String lol=donors.get(i).printIDS();
            String name=donors.get(i).getName()+ "\t \t| ";
            String organ=donors.get(i).getOrgan() + "\t \t| ";
            table+=String.format("\n %-26s %19s %19s %16s %20s %20s",k,name,age, organ,yerr,lol);
        }
        System.out.println(header+ yuh+ table);

    }

    /**
     * This is also another helper method that I implemented that prints the recipients information
     * in a neatly formatted table when comparing
     */
    public void printAllRecipientsHelper(){
        String header= String.format("%-26s %19s %18s %16s %20s %20s" , "Index \t|" , "Recipient Name \t\t\t|" , "Age \t\t|" , "Organ Needed\t\t|", "Blood Type\t\t|" , "Donor IDS" );
        String yuh= "\n --------------------------------------------------------------------------------------------------------------------------------------------------";
        String table="";
        for(int i=0; i<recipients.size();i++){
            String k=recipients.get(i).getID()+ "\t\t| ";
            String age=recipients.get(i).getID()+ "\t\t| ";
            BloodType bloodType=recipients.get(i).getBloodType();
            String yerr= bloodType.getBloodType() + "\t\t| ";
            String lol=recipients.get(i).printIDS();
            String name=recipients.get(i).getName()+ "\t \t| ";
            String organ=recipients.get(i).getOrgan() + "\t \t| ";
            table+=String.format("\n %-26s %19s %19s %16s %20s %20s",k,name,age, organ,yerr,lol);
        }
        System.out.println(header+ yuh+ table);
    }

    /**
     * This method prints all the organ recipients' information in a neatly formatted table
     */
    public void printAllRecipients(){
        String header= String.format("%-26s %19s %18s %16s %20s %20s" , "Index \t|" , "Recipient Name \t\t\t|" , "Age \t\t|" , "Organ Needed\t\t|", "Blood Type\t\t|" , "Donor IDS" );
        String yuh= "\n --------------------------------------------------------------------------------------------------------------------------------------------------";
        String table="";
        for(int i=0; i<recipients.size();i++){
            String k=i+ "\t\t| ";
            String age=recipients.get(i).getAge()+ "\t\t| ";
            BloodType bloodType=recipients.get(i).getBloodType();
            String yerr= bloodType.getBloodType() + "\t\t| ";
            String lol=recipients.get(i).printIDS();
            String name=recipients.get(i).getName()+ "\t \t| ";
            String organ=recipients.get(i).getOrgan() + "\t \t| ";
            table+=String.format("\n %-26s %19s %19s %16s %20s %20s",k,name, age , organ ,yerr,lol);
        }
        System.out.println(header+ yuh+ table);

    }

    /**
     * This method adds the specified patient to recipient list
     * @param patient
     * @throws IllegalArgumentException
     */
    public void addRecipient(Patient patient) throws IllegalArgumentException
    {
        if(recipients.size()==MAX_PATIENTS){
            throw new IllegalArgumentException("You cannot add anymore Recipient Patients as it reached the max!");
        }
        recipients.add(patient);
    }

    /**
     * This method adds specified patient to the donor list
     * @param patient
     * @throws IllegalArgumentException
     */
    public void addDonor(Patient patient) throws IllegalArgumentException{
        if(donors.size()==MAX_PATIENTS){
            throw new IllegalArgumentException("You cannot add anymore Donor Patients as it reached the max!");
        }
        donors.add(patient);
    }

    /**
     * This method removes the specified patient from the recipients list
     * @param name
     */
    public void removeRecipient(String name) {
        int n = 0;
        for (int i = 0; i < recipients.size(); i++) {
            if (recipients.get(i).getName().equalsIgnoreCase(name)) {
                n = 1;
            }
        }
        if (n == 1) {
            for (int i = 0; i < recipients.size(); i++) {
                if (recipients.get(i).getName().equalsIgnoreCase(name)) {
                    recipients.remove(i);
                }
            }

        }
        else{
            throw new IllegalArgumentException("There is no Recipient with that name!");
        }
    }

    /**
     * This method removes the specified patient from the donors list
     * @param name
     */
    public void removeDonor(String name){
        int n = 0;
        for (int i = 0; i < donors.size(); i++) {
            if (donors.get(i).getName().equalsIgnoreCase(name)) {
               n=1;
            }
        }
        if (n == 1) {
            for (int i = 0; i < donors.size(); i++) {
                if (donors.get(i).getName().equalsIgnoreCase(name)) {
                    donors.remove(i);
                }
            }
        }
        else{
            throw new IllegalArgumentException("There is no Donor with that Name!");
        }
    }

    /**
     * This is a helper method that implemented that decrements the ids of the donor patients when removing
     * @param position
     */
    public void removeDonors(int position){
        boolean yer=false;
        for(int i=0; i<donors.size();i++){
            if(i==position){
                yer=true;
                donors.get(i).setID(donors.get(i).getID()-1);
                continue;

            }
            if(yer){
                donors.get(i).setID(donors.get(i).getID()-1);
            }
        }
    }

    /**
     * This is a helper method that I implemented that decrements the ids of the recipient patients when removing
     * @param position
     */
    public void removeRecipients(int position){
        boolean yer=false;
        System.out.println(recipients.size());
        for(int i=0; i<recipients.size();i++){
            if(i==position){
                yer=true;
                recipients.get(i).setID(recipients.get(i).getID()-1);
                continue;

            }
            if(yer){
                recipients.get(i).setID(recipients.get(i).getID()-1);
            }
        }
    }

    /**
     * This helper method that I implemented updates the connection matrix every time you add or remove a patient
     */
    public void addUpdateMatrix(){
        connections= new boolean[donors.size()][recipients.size()];
        for(int i=0; i<donors.size();i++){
            for(int j=0; j<recipients.size();j++){
                if (BloodType.isCompatible(recipients.get(j).getBloodType(), donors.get(i).getBloodType()) && donors.get(i).getOrgan().equalsIgnoreCase(recipients.get(j).getOrgan())) {
                        connections[i][j] = true;

                    }
                else{
                    connections[i][j]=false;

                }
            }
        }

    }

    /**
     * This is a helper method that gets the position of the donor to be removed
     * @param name
     * @return
     */
    public int getPositionDonors(String name) {
        int n=0;
        for (int i = 0; i < donors.size(); i++) {
            if (donors.get(i).getName().equalsIgnoreCase(name)) {
                n=i;
                return n;
            }
        }
        return n;
    }

    /**
     * This is a helper method that gets the position of the recipient to be removed
     * @param name
     * @return
     */
    public int getPositionRecipients(String name){
        int n=0;
        for(int i=0; i<recipients.size();i++){
            if(recipients.get(i).getName().equalsIgnoreCase(name)){
                n=i;
                return n;
            }
        }
        return n;
    }

    /**
     * This method clears the array list of the recipients IDS or donors IDS when adding or removing
     */
    public void clearArrayList(){
        for(int i=0; i<donors.size();i++){
            for(int k=0;k<recipients.size();k++){
                donors.get(i).getIDS().clear();
                recipients.get(k).getIDS().clear();
            }
        }
    }

    /**
     * This helper method that I implemented checks to see if the connections between the
     * recipient and donor is true and adds it
     */
    public void checkifTrue(){
        for(int i=0; i<donors.size();i++){
            for(int k=0; k<recipients.size();k++){
                if(connections[i][k]){
                    donors.get(i).getIDS().add(recipients.get(k).getID());
                    recipients.get(k).getIDS().add(donors.get(i).getID());
                }
            }
        }

    }

}

