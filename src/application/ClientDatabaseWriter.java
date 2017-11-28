package application;

import java.io.*;
import java.util.ArrayList;

public class ClientDatabaseWriter {
	
	
	
	
	
	public static void writeFile() {
	try {
		String fileName = "C:\\Users\\David\\Documents\\UCL\\Introductory programming\\coursework\\CinemaBooking\\src\\application\\Client_Database.txt";
        FileWriter fileWriter =
            new FileWriter(fileName);

        BufferedWriter bufferedWriter =
            new BufferedWriter(fileWriter);
        
        bufferedWriter.write("ID;Username;Password;First Name;Last Name;Email");
        
       if(Client.getNumberOfClients()>0) {
    	   bufferedWriter.newLine();
       for(int i=0; i<Client.getNumberOfClients()-1;i++) {
    	   
      	for(int j=0; j<(Client.getClientData(i).size()-1);j++) {
      		 bufferedWriter.write(Client.getClientData(i).get(j) + ";");
      		 
    	   
       }
      	
      	bufferedWriter.write(Client.getClientData(i).get(Client.getClientData(i).size()-1)); //this is so that there is not comma after last word of line
      	bufferedWriter.newLine();
       }
       int i=Client.getNumberOfClients()-1;
   		for(int j=0; j<(Client.getClientData(i).size()-1);j++) {
 		 bufferedWriter.write(Client.getClientData(i).get(j) + ";");
   		}
   		bufferedWriter.write(Client.getClientData(i).get(Client.getClientData(i).size()-1));
        bufferedWriter.close();
	}
	
	else {
		bufferedWriter.close();	
	}
	}   
	catch(IOException e) {
		e.printStackTrace();
	}
	
}
	
public static void readFile() {
	
	String line = null;
	String [] client;
	String fileName = "C:\\Users\\David\\Documents\\UCL\\Introductory programming\\coursework\\CinemaBooking\\src\\application\\Client_Database.txt";
	try {
		
        // FileReader reads text files in the default encoding.
        FileReader fileReader = 
            new FileReader(fileName);

        // Always wrap FileReader in BufferedReader.
        BufferedReader bufferedReader = 
            new BufferedReader(fileReader);
        bufferedReader.readLine();
        Client.reset();
        while((line = bufferedReader.readLine()) != null) {
            System.out.println(line); 
        	
        	
        	client = line.split(";");
        	Client.addToEmptyList(Integer.parseInt(client[0]), client[1], client[2], client[3], client[4], client[5]);
        }

        // Always close files.
        bufferedReader.close();         
    }
    catch(FileNotFoundException ex) {
        System.out.println(
            "Unable to find file '" + 
            fileName + "'");                
    }
    catch(IOException ex) {
        System.out.println(
            "Error reading file " 
            + fileName + "");                  
        // Or we could just do this: 
        // ex.printStackTrace();
    }
	
}
	
	
}
