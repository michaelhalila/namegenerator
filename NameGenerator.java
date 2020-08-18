/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namegenerator;

/**
 *
 * @author Michael
 */

import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javafx.application.Application;

public class NameGenerator {
    
    // nb. name lists in .txt files and UTF-8 encoding
    
    public static final int bloodBowlMaxNameLength = 24;
        
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // 1.0: Halfling name generator, 25.10.2018 TOR-WPG
        // 1.1 method generateBloodBowlHalflingName
        // added Imperial name generator
        // refactoring: method generateHobbitNickname and checkNameLength
        // 2.0 added javafx graphical interface and teen-name method
        
        System.out.println("Name generator 1.1: Halfling and Imperial name generator");
        System.out.println("");
        
        String imperialName = generateMaleImperialName();
        System.out.println("");
        System.out.println("Imperial name: " + imperialName);
        
        System.out.println("");
        String halflingName = generateBloodBowlHalflingName();
        
        System.out.println("");
        System.out.println("Halfling name: " + halflingName);
        if (halflingName.length() > 25) {
            System.out.println("Problem! Name too long.");
        }
        
        System.out.println("");
        System.out.println("Dark Elf Blood Bowl Names");
        
        String darkElfLinemanName = generateBloodBowlDarkElfLinemanName();
        String darkElfBlitzerName = generateBloodBowlDarkElfBlitzerName();
        
        System.out.println("Lineman: " + darkElfLinemanName);
        System.out.println("Blitzer: " + darkElfBlitzerName);
        System.out.println("");
        
        Application.launch(GraphicInterface.class);
        
    }
    
    //general methods
    
    public static ArrayList<String> generateNameList(String filename) {
        
        //reads a .txt file consisting of line-separated names into an arraylist
        
        ArrayList<String> names = new ArrayList<>();
        
        try (Scanner fileReader = new Scanner(new File(filename))) {
            while (fileReader.hasNextLine()) {
                names.add(fileReader.nextLine());
            }
        } catch (Exception e) {
            System.out.println("File could not be read. " + e.getMessage());
        }
        
        return names;
    }
    
    public static String generateName(ArrayList<String> names) {
    
        //returns a random name from the arraylist
        
        if (names.size() > 0) {
            Random random = new Random();
            int index = random.nextInt(names.size());
            return names.get(index);
        } else {
            return "Name could not be generated.";
        }
    }
    
    public static boolean checkNameLength(String name, int length) {
        
        //check if the name length is equal or under to allowed length
        
        return name.length() <= length;
    }
    
    public static ArrayList<String> getNamesShorterThan (ArrayList<String> names, int length) {
        ArrayList<String> shorterNames = new ArrayList<>();
        for (String name : names) {
            if (name.length() < length) {
                shorterNames.add(name);
            }
        }
        return shorterNames;
    }
    
    //other name methods
    
    public static String generateFinnishTeenName() {
        ArrayList<String> teenNames = generateNameList("ftfn.txt");
        String name = generateName(teenNames);
        return name;
    }
    
    public static String generateMamboName() {
        ArrayList<String> mamboNames = generateNameList("mno5.txt");
        String name = generateName(mamboNames);
        return name;
    }
    
    //Dark Elf Blood Bowl methods
    
    public static String generateBloodBowlDarkElfLinemanName() {
        ArrayList<String> darkElfFirstNames = generateNameList("demfn.txt");
        String name = generateName(darkElfFirstNames);
        return name;
    }
    
    public static String generateBloodBowlDarkElfBlitzerName() {
        ArrayList<String> darkElfFirstNames = generateNameList("demfn.txt");
        ArrayList<String> darkElfLastNames = generateNameList("demln.txt");
        ArrayList<String> darkElfTitles = generateNameList("detitle.txt");
        String name = "could not generate name";
        String firstName = generateName(darkElfFirstNames);

        
        Random random = new Random();
        int generateTitle = random.nextInt(3);
        
        if (generateTitle == 0) {
            while (true) {
                String title = generateName(darkElfTitles);
                name = title + " " + firstName;
                if (checkNameLength(name, NameGenerator.bloodBowlMaxNameLength)) {
                    break;
                }
            }
        } else {
            while (true) {
                String lastName = generateName(darkElfLastNames);                
                name = firstName + " " + lastName;
                if (checkNameLength(name, NameGenerator.bloodBowlMaxNameLength)) {
                    break;
                    }
                }

        }
        
        return name;
    }

    //Imperial methods
    
    public static String generateMaleImperialName() {
        
        //generates a random male imperial name
        
        ArrayList<String> imperialMaleFirstNames = generateNameList("40kmfn.txt");
        ArrayList<String> imperialMaleLastNames = generateNameList("40kmln.txt");
        String wholeName = generateName(imperialMaleFirstNames) + " " + generateName(imperialMaleLastNames);
        return wholeName;
    }
    
    //Hobbit methods
    
    public static String generateBloodBowlHalflingName() {

        ArrayList<String> hobbitLastNames = generateNameList("hobbitln.txt");
        String wholeName;
        String firstName;
        String lastName;
        String nameWithNickName = "X";
        boolean isNickName = false;
        boolean isAddLastName = false;
        
        while (true) {

            //while loop generates name and checks its length
            
            ArrayList<String> hobbitFirstNames = generateNameList("hobbitfn.txt");
            firstName = generateName(hobbitFirstNames);        
            
                //while loop to stop certain names from being generated
        
            while (true) {
                lastName = generateName(hobbitLastNames);
                
                if (firstName.equals("Guybrush") && lastName.equals("Threepwood")) {
                    lastName = generateName(hobbitLastNames);
                } else {
                    break;
                }
            }
        
            wholeName = firstName + " " + lastName;
            System.out.println(wholeName);
            
            if (checkNameLength(wholeName,NameGenerator.bloodBowlMaxNameLength)) {
                break;
            } else {
                System.out.println("Name too long!");
            }
        }
        
        if (checkNameLength(wholeName,NameGenerator.bloodBowlMaxNameLength)) {
        
            //generate nickname, checking it is not the same as the first name

            System.out.println("Trying nickname!");
            String nickname = generateHobbitNickname(firstName);
            nameWithNickName = firstName + " \"" + nickname + "\" " + lastName;
            System.out.println(nameWithNickName);
                
            
            //check combined name length, if not over maximum add nickname
            
            if (checkNameLength(nameWithNickName,NameGenerator.bloodBowlMaxNameLength)) {
                wholeName = nameWithNickName;
                isNickName = true;
                System.out.println("Nickname generated!");                
                } else {
                System.out.println("Name too long!");
                }
                        
            }
        
        while (wholeName.length() <= NameGenerator.bloodBowlMaxNameLength) {
            
            //try adding additional last names
            System.out.println("Trying additional surname!");
            String addLastName = generateName(hobbitLastNames);
            System.out.println(wholeName + "-" + addLastName);
            if (wholeName.length() + addLastName.length() + 1 <= NameGenerator.bloodBowlMaxNameLength) {
                if (addLastName.equals("of the Marish")) {
                    wholeName = wholeName + " " + addLastName;
                    isAddLastName = true;
                    System.out.println("Surname incremented!");
                    break;
                } else if (lastName.equals("of the Marish")) {
                    wholeName = firstName + " " + addLastName + " " + lastName;
                    isAddLastName = true;
                    System.out.println("Surname incremented!");
                    break;
                } else {
                    wholeName = wholeName + "-" + addLastName;
                    }
                    isAddLastName = true;
                    System.out.println("Surname incremented!");
                    
            } else {
                System.out.println("Name too long!");
                break;
            }

        }
        
        // try to add a shorter nickname to names without additional surnames or nicknames
        
        if (isNickName == false && isAddLastName == false) {
            
            //LeCheck
            
            if (lastName.equals("LeChuck")) {
                if (wholeName.length() < (NameGenerator.bloodBowlMaxNameLength - 6)) {
                    wholeName = firstName + " \"G.P.\" " + lastName;
                    System.out.println("LeCheck!");
                    }
                } else {
                    System.out.println("Trying shorter nickname!");
                    int availableCharacters = NameGenerator.bloodBowlMaxNameLength - wholeName.length();
                    if (availableCharacters > 6) {
                        ArrayList<String> hobbitNickNames = generateNameList("hobbitnn.txt");
                        ArrayList<String> availableNickNames = getNamesShorterThan(hobbitNickNames, availableCharacters - 2);
                        if (!availableNickNames.isEmpty()) {
                            String newNick = generateName(availableNickNames);
                            if (!newNick.isEmpty()) {
                                wholeName = firstName + " \"" + newNick + "\" " + lastName;
                                System.out.println(wholeName);
                            } else {
                                System.out.println("No nicknames found! - although this should never happen");
                            }
                        } else {
                            System.out.println("No nicknames found!");
                        }
                    } else {
                        System.out.println("No space!");
                    }
                }
            }
        
        return wholeName;
        
    }
    
    public static String generateHobbitNickname(String noName) {
        ArrayList<String> hobbitNickNames = generateNameList("hobbitnn.txt");
        String name;
        while (true) {
            name = generateName(hobbitNickNames);
            if (!name.equals(noName)) {
            return name;
            }
        }
    }
    
}
