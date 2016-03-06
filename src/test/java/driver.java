package test.java;

import java.io.IOException;

import main.java.PhoneDirectory;

/*Testing addEntry, deleteEntry, getNumber, and changeEntry functions.
 * The last value in the addressBook file will be Ace Clark with an updated number.
 */
public class driver{

public static void main(String[] args) throws IOException {

	String num= "";
	PhoneDirectory newDirectory = new PhoneDirectory();

   newDirectory.addEntry("Ace Clark", "7732014546");
   newDirectory.addEntry("Kandice", "7777008777"); //Testing the addition of only the first name
   newDirectory.addEntry("Anna Ruth Williams", "334234234"); //Testing the addition of first, middle, and last names
    
        
    newDirectory.deleteEntry("Anna");
    newDirectory.deleteEntry("Kandice");
   
    newDirectory.changeEntry("ACE", "2025574988");
	num = newDirectory.getNumber("Ace");
	
	System.out.print(num);

}

}

 