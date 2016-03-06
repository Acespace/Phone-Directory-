package main.java;

import java.io.*;
import java.util.Properties;

//variable
public class PhoneDirectory {
	
/*The variable function returns the path to properties file. For use in the subsequent functions.
 */
InputStream variable() throws FileNotFoundException{
		
		InputStream fileInput = null;
		fileInput = new FileInputStream("/Users/Alston/Documents/CODES/Large-Scale-Programming-/LargeScale/Assignment3/src/main/java/resources/phone.properties");// attaching to property file
		
		return fileInput;
	
	}
	
/*The address book is opened, by accessing the properties file.
 * Then the name and number are written to the file. 
 * Close the file, and print updates to the console
 */

	public void addEntry(String name, String number) throws IOException {
		//print name and number
	
		Properties prop = new Properties();
		prop.load(variable());
	
		Writer fileWriter = new FileWriter((prop.getProperty("path")), true);  
		fileWriter.write(name + " : " + number);
		fileWriter.write(System.lineSeparator());
		fileWriter.close();
		
		System.out.println("Just added: ");
		System.out.println("Name: " + name);
		System.out.println("Phone number: " + number);
		//pass
	}
	
	/*
	 * Two files are opened, the address file and a new temporary file.
	 * Every line that does not contain the passed in name is added to the tempoprary file.
	 * The main address book is then overridden by the temporary file.
	 * 
	 */
	
	public void deleteEntry(String name) throws IOException {
	
		Properties prop = new Properties(); //creating new variable
		prop.load(variable());
		String line = null;
		     
		try {
	        //making path to main
			BufferedReader file = new BufferedReader(new FileReader (prop.getProperty("path")));
			
			//creating new file
			File temp = new File("/Users/Alston/Documents/CODES/Large-Scale-Programming-/LargeScale/Assignment3/src/main/java/resources/temp.txt ");//creating new temporary file
		    BufferedWriter writer = new BufferedWriter(new FileWriter(temp));// making the file writable
		       
	        while ((line = file.readLine()) != null) {
	        	
	        	if (!line.toLowerCase().contains(name.toLowerCase())) { //searching file for entries that do not contain name to delete
	        		writer.write(line); //writing to temporary file
	
	        }
        	file.close();//closing addressbook file
 	        writer.close();//closing temporary file 
 	        writer = new BufferedWriter(new FileWriter(prop.getProperty("path"))); //opening writing file
 	        
 	        writer.write(line); // overwriting the file

			System.out.println("Deleted: ");
			System.out.println("Name: " + name);

	        }
		}

	     catch (Exception e) {
	        System.out.println("");
	    }
	}
    /*
     * Iterate through the addressBook file until the name is found.
     * The last index of the string is the number.
     * Save and return that value.
     * 
     */
	public String getNumber(String name) throws IOException {
		Properties prop = new Properties(); //creating new variable
		prop.load(variable());
			
		try {
	        // input the file content to the String "input"
	        BufferedReader file = new BufferedReader(new FileReader((prop.getProperty("path"))));
	        String line;
	        
	        while ((line = file.readLine()) != null) {
	        	
	        	if (line.toLowerCase().contains(name.toLowerCase())) {
	        		String[] splitString = line.split(" ");
	        		        		
	    	        file.close();
	    	        int index_of_number = splitString.length;

	    	        return splitString[index_of_number -1];
	        	}
	        }
			file.close();
			
	    } catch (Exception e) {
	        System.out.println("Problem reading file.");
	    }
		
		return "That user was not found";
	}	
	
	/*Locate the requested name in the addressBook file, then delete it using the deleteEntry
	 * function. Afterward add the same name with the updated number.
	 */
	
	public void changeEntry(String name, String number) throws IOException{
	
		deleteEntry(name);
		addEntry(name, number);
		
	}
}
	
