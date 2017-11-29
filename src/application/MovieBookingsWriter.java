package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MovieBookingsWriter {

	public static void writeFile() {
		try {
			String fileName = "src/application/Booking_Database.txt";
	        FileWriter fileWriter =
	            new FileWriter(fileName);

	        BufferedWriter bufferedWriter =
	            new BufferedWriter(fileWriter);
	        
	        bufferedWriter.write("ID;Title;Screening Data;Screening Time; Booking Date; Seat");
	        
	       if(MovieBookings.getNumberOfBookings()>0) {
	    	   bufferedWriter.newLine();
	       for(int i=0; i<MovieBookings.getNumberOfBookings()-1;i++) {
	    	   
	      	for(int j=0; j<(MovieBookings.getBookingData(i).length-1);j++) {
	      		 bufferedWriter.write(MovieBookings.getBookingData(i)[j] + ";");
	      		bufferedWriter.newLine();
	    	   
	       }
	      	int j=MovieBookings.getBookingData(i).length-1;
	      	bufferedWriter.write(MovieBookings.getBookingData(i)[j]);
      		bufferedWriter.newLine();		
	       }
	       bufferedWriter.newLine();
	      	int i =MovieBookings.getNumberOfBookings()-1;
	      	for(int j=0; j<(MovieBookings.getBookingData(i).length-1);j++) {
	      		 bufferedWriter.write(MovieBookings.getBookingData(i)[j] + ";");
	      		bufferedWriter.newLine();
	    	   
	       }
	      	int j=MovieBookings.getBookingData(i).length-1;
	      	bufferedWriter.write(MovieBookings.getBookingData(i)[j]);
     		bufferedWriter.newLine();	
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
		String [] booking;
		ArrayList<String> times = new ArrayList<String>();
		String fileName = "src/application/Booking_Database.txt";
		try {
			
	        // FileReader reads text files in the default encoding.
	        FileReader fileReader = 
	            new FileReader(fileName);

	        // Always wrap FileReader in BufferedReader.
	        BufferedReader bufferedReader = 
	            new BufferedReader(fileReader);
	        bufferedReader.readLine();
	        MovieBookings.reset();
	        while((line = bufferedReader.readLine()) != null) {
	            System.out.println(line); 
	        	
	        	
	        	booking = line.split(";");
	        	
	        	MovieBookings.addBooking(Integer.parseInt(booking[0]), booking[1],  booking[2], booking[3], booking[4], booking[5]); 
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
