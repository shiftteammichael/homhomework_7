/**
 * The TransplantDriver class acts as a main driver for the application
 * @author Michael Hom
 * @id 112536073
 * @Recitaiton 09
 */

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TransplantDriver {
    static String DONOR_FILE="donors.txt";
    static String RECIPIENT_FILE="recipients.txt";
    public static void main(String [] args) throws IOException {
        TransplantGraph test;
        try {
            FileInputStream file = new FileInputStream("transplant.obj");
            ObjectInputStream fin = new ObjectInputStream(file);
            test = (TransplantGraph) fin.readObject();
            System.out.println("Loading data from transplant.obj...");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("transplant.obj does not exist. Creating new Transplant Graph...:");
            test = TransplantGraph.buildFromFiles(DONOR_FILE, RECIPIENT_FILE);
            test.addUpdateMatrix();
            test.checkifTrue();
            System.out.println("Loading data from 'donors.txt'...");
            System.out.println("Loading data from 'recipients.txt'...");
        }
            Scanner in = new Scanner(System.in);
            while(true) {
                System.out.println("Menu:\n" +
                        "    (LR) - List all recipients\n" +
                        "    (LO) - List all donors\n" +
                        "    (AO) - Add new donor\n" +
                        "    (AR) - Add new recipient\n" +
                        "    (RO) - Remove donor\n" +
                        "    (RR) - Remove recipient\n" +
                        "    (SR) - Sort recipients\n" +
                        "    (SO) - Sort donors\n" +
                        "    (Q) - Quit");

                System.out.println("Please Select an Option: ");
                String input=in.nextLine();
                if(input.equalsIgnoreCase("LR")){
                    test.printAllRecipients();
                }
                if(input.equalsIgnoreCase("LO")){
                    test.printAllDonors();
                }
                if(input.equalsIgnoreCase("AO")){
                    try {
                        System.out.println("Please enter the organ donor name: ");
                        String name = in.nextLine();
                        System.out.println("Please enter the organs " + name + " is donating:");
                        String organ = in.nextLine();
                        System.out.println("Please enter the blood type of " + name + ": ");
                        String bloodType = in.nextLine();
                        System.out.println("Please enter the age of " + name + ":");
                        int age = in.nextInt();
                        String bloodTypes = bloodType.substring(0, 1).toUpperCase() + bloodType.substring(1);
                        BloodType yer = new BloodType(bloodTypes);
                        String cap = organ.substring(0, 1).toUpperCase() + organ.substring(1);
                        Patient donor = new Patient(test.donors.size(), name, age, cap, yer);
                        test.addDonor(donor);
                        test.addUpdateMatrix();
                        test.clearArrayList();
                        test.checkifTrue();

                        System.out.println("The organ donor with ID" + donor.getID() + " has been succesfully added to the donor list");
                    }
                    catch(IllegalArgumentException ex){
                        System.out.println(ex.getMessage());
                    }
                }
                if(input.equalsIgnoreCase("AR")){
                    try {
                        System.out.println("Please enter new Recipient's name: ");
                        String name = in.nextLine();
                        System.out.println("Please enter the Recipient's blood type: ");
                        String bloodType = in.nextLine();
                        System.out.println("Please enter the Recipient's age: ");
                        int age = in.nextInt();
                        in.nextLine();
                        System.out.println("Please enter the organ needed: ");
                        String organ = in.nextLine();
                        String bloodTypes = bloodType.substring(0, 1).toUpperCase() + bloodType.substring(1);
                        String cap = organ.substring(0, 1).toUpperCase() + organ.substring(1);
                        BloodType yer = new BloodType(bloodTypes);
                        Patient recipient = new Patient(test.recipients.size(), name, age, cap, yer);
                        test.addRecipient(recipient);
                        test.addUpdateMatrix();
                        test.clearArrayList();
                        test.checkifTrue();
                        System.out.println(name + " is now on the organ transplant waitlist!");
                    }
                    catch(IllegalArgumentException ex){
                        System.out.println(ex.getMessage());
                    }
                }
                if(input.equalsIgnoreCase("RO")){
                    try {
                        System.out.println("Please enter the name of the organ donor to remove: ");
                        String name = in.nextLine();
                        int position = test.getPositionDonors(name);
                        test.removeDonor(name);
                        test.removeDonors(position);
                        test.addUpdateMatrix();
                        test.clearArrayList();
                        test.checkifTrue();
                        System.out.println(name + " was removed from the organ donor list.");
                    }
                    catch(IllegalArgumentException ex){
                        System.out.println(ex.getMessage());
                    }

                }
                if(input.equalsIgnoreCase("RR")){
                    try {
                        System.out.println("Please enter the name of the recipient to remove: ");
                        String name = in.nextLine();
                        int position = test.getPositionRecipients(name);
                        test.removeRecipient(name);
                        test.removeRecipients(position);
                        test.addUpdateMatrix();
                        test.clearArrayList();
                        test.checkifTrue();
                        System.out.println(name + " was removed from the organ transplant waitlist.");
                    }
                    catch(IllegalArgumentException ex){
                        System.out.println(ex.getMessage());
                    }

                }
                if(input.equalsIgnoreCase("SR")){
                    boolean condition=true;
                    while(condition){
                        System.out.println("" +
                                "    (I) Sort by ID\n" +
                                "    (N) Sort by Number of Recipients\n" +
                                "    (B) Sort by Blood Type\n" +
                                "    (O) Sort by Organ Donated\n" +
                                "    (Q) Back to Main Menu");
                        System.out.println("Please Select an option:");
                        String input1=in.nextLine();
                        if (input1.equalsIgnoreCase("I")) {
                            TransplantGraph yer= new TransplantGraph();
                            for(int i=0; i<test.donors.size();i++){
                                yer.donors.add(test.donors.get(i));
                            }
                            for(int k=0; k<test.recipients.size();k++){
                                yer.recipients.add(test.recipients.get(k));
                            }
                            yer.addUpdateMatrix();
                            yer.clearArrayList();
                            yer.checkifTrue();
                            Collections.sort(yer.recipients);
                            yer.printAllRecipientsHelper();
                        }
                        if (input1.equalsIgnoreCase("N")) {
                            TransplantGraph yer= new TransplantGraph();
                            for(int i=0; i<test.donors.size();i++){
                                yer.donors.add(test.donors.get(i));
                            }
                            for(int k=0; k<test.recipients.size();k++){
                                yer.recipients.add(test.recipients.get(k));
                            }
                            yer.addUpdateMatrix();
                            yer.clearArrayList();
                            yer.checkifTrue();
                            Collections.sort(yer.recipients, new NumConnectionsComparator());
                            yer.printAllRecipientsHelper();
                        }
                        if (input1.equalsIgnoreCase("B")) {
                            TransplantGraph yer= new TransplantGraph();
                            for(int i=0; i<test.donors.size();i++){
                                yer.donors.add(test.donors.get(i));
                            }
                            for(int k=0; k<test.recipients.size();k++){
                                yer.recipients.add(test.recipients.get(k));
                            }
                            yer.addUpdateMatrix();
                            yer.clearArrayList();
                            yer.checkifTrue();
                            Collections.sort(yer.recipients, new BloodTypeComparator());
                            yer.printAllRecipientsHelper();
                        }
                        if (input1.equalsIgnoreCase("O")) {
                            TransplantGraph yer= new TransplantGraph();
                            for(int i=0; i<test.donors.size();i++){
                                yer.donors.add(test.donors.get(i));
                            }
                            for(int k=0; k<test.recipients.size();k++){
                                yer.recipients.add(test.recipients.get(k));
                            }
                            yer.addUpdateMatrix();
                            yer.clearArrayList();
                            yer.checkifTrue();
                            Collections.sort(yer.recipients,new OrganComparator());
                            yer.printAllRecipientsHelper();
                        }
                        if (input1.equalsIgnoreCase("Q")) {
                            condition=false;
                        }
                    }
                }
                if(input.equalsIgnoreCase("SO")){
                    boolean condition=true;
                    while(condition){
                        System.out.println(
                                "    (I) Sort by ID\n" +
                                "    (N) Sort by Number of Donors\n" +
                                "    (B) Sort by Blood Type\n" +
                                "    (O) Sort by Organ Needed\n" +
                                "    (Q) Back to Main Menu");
                        System.out.println("Please Select an option:");
                        String input1=in.nextLine();
                        if (input1.equalsIgnoreCase("I")) {
                            TransplantGraph yer= new TransplantGraph();
                            for(int i=0; i<test.donors.size();i++){
                                yer.donors.add(test.donors.get(i));
                            }
                            for(int k=0; k<test.recipients.size();k++){
                                yer.recipients.add(test.recipients.get(k));
                            }
                            yer.addUpdateMatrix();
                            yer.clearArrayList();
                            yer.checkifTrue();
                            Collections.sort(yer.donors);
                            yer.printAllDonorsHelper();
                        }
                        if (input1.equalsIgnoreCase("N")) {
                            TransplantGraph yer= new TransplantGraph();
                            for(int i=0; i<test.donors.size();i++){
                                yer.donors.add(test.donors.get(i));
                            }
                            for(int k=0; k<test.recipients.size();k++){
                                yer.recipients.add(test.recipients.get(k));
                            }
                            yer.addUpdateMatrix();
                            yer.clearArrayList();
                            yer.checkifTrue();
                            Collections.sort(yer.donors, new NumConnectionsComparator());
                            yer.printAllDonorsHelper();
                        }
                        if (input1.equalsIgnoreCase("B")) {
                            TransplantGraph yer= new TransplantGraph();
                            for(int i=0; i<test.donors.size();i++){
                                yer.donors.add(test.donors.get(i));
                            }
                            for(int k=0; k<test.recipients.size();k++){
                                yer.recipients.add(test.recipients.get(k));
                            }
                            yer.addUpdateMatrix();
                            yer.clearArrayList();
                            yer.checkifTrue();
                            Collections.sort(yer.donors, new BloodTypeComparator());
                            yer.printAllDonorsHelper();
                        }
                        if (input1.equalsIgnoreCase("O")) {
                            TransplantGraph yer= new TransplantGraph();
                            for(int i=0; i<test.donors.size();i++){
                                yer.donors.add(test.donors.get(i));
                            }
                            for(int k=0; k<test.recipients.size();k++){
                                yer.recipients.add(test.recipients.get(k));
                            }
                            yer.addUpdateMatrix();
                            yer.clearArrayList();
                            yer.checkifTrue();
                            Collections.sort(yer.donors, new OrganComparator());
                            yer.printAllDonorsHelper();
                        }
                        if (input1.equalsIgnoreCase("Q")) {
                            condition=false;
                        }
                    }

                }
                if(input.equalsIgnoreCase("Q")){
                    FileOutputStream file= new FileOutputStream("transplant.obj");
                    ObjectOutputStream fourr= new ObjectOutputStream(file);
                    fourr.writeObject(test);
                    fourr.close();

                    System.out.println("Writing data to transplant.obj");
                    System.exit(0);
                }
            }
        }
    }


/**
 try {
 FileInputStream file = new FileInputStream("transplant.obj");
 ObjectInputStream fin = new ObjectInputStream(file);
 test = (TransplantGraph) fin.readObject();
 System.out.println("Loading data from transplant.obj...");
 } catch (IOException | ClassNotFoundException ex) {
 System.out.println("transplant.obj does not exist. Creating new Transplant Graph...:");
 test = TransplantGraph.buildFromFiles(DONOR_FILE, RECIPIENT_FILE);
 test.addUpdateMatrix();
 test.checkifTrue();
 System.out.println("Loading data from 'donors.txt'...");
 System.out.println("Loading data from 'recipients.txt'...");
 }
 /* *
 /**
 FileOutputStream file= new FileOutputStream("transplant.obj");
 ObjectOutputStream fourr= new ObjectOutputStream(file);
 fourr.writeObject(test);
 fourr.close();
 System.out.println("Writing data to transplant.obj");
 /**
 *
 */
