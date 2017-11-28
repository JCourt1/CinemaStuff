package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MovieDatabaseWriter {

	public static void writeFile() {
		try {
			String fileName = "C:\\Users\\David\\Documents\\UCL\\Introductory programming\\coursework\\CinemaBooking\\src\\application\\Cinema_Database.txt";
	        FileWriter fileWriter =
	            new FileWriter(fileName);

	        BufferedWriter bufferedWriter =
	            new BufferedWriter(fileWriter);
	        
	        bufferedWriter.write("Title;Description;Start Date;End Date;Viewing Times");
	        
	       if(Movies.getNumberOfMovies()>0) {
	    	   bufferedWriter.newLine();
	       for(int i=0; i<Movies.getNumberOfMovies()-1;i++) {
	    	   
	      	for(int j=0; j<(Movies.getMovieData(i).size()-1);j++) {
	      		 bufferedWriter.write((String)Movies.getMovieData(i).get(j) + ";");
	      		 
	    	   
	       }
	      	
	      	int j=(Movies.getMovieData(i).size()-1);
	      	ArrayList<String> times = new ArrayList<String>();
	      	times = (ArrayList<String>) Movies.getMovieData(i).get(j); 
	      	
	      	for(int k=0; k<(times.size()-1); k++) {
	      	bufferedWriter.write(times.get(k) + ",");
	      	}
	      	int k=(times.size()-1);
	      	bufferedWriter.write(times.get(k));
	        bufferedWriter.newLine();
	       }
	       
	       int i=Movies.getNumberOfMovies()-1;
	   	for(int j=0; j<(Movies.getMovieData(i).size()-1);j++) {
     		 bufferedWriter.write((String)Movies.getMovieData(i).get(j) + ";");
     		 
   	   
      }
     	
     	int j=(Movies.getMovieData(i).size()-1);
     	ArrayList<String> times = new ArrayList<String>();
     	times = (ArrayList<String>) Movies.getMovieData(i).get(j); 
     	
     	for(int k=0; k<(times.size()-1); k++) {
     	bufferedWriter.write(times.get(k) + ",");
     	}
     	int k=(times.size()-1);
     	bufferedWriter.write(times.get(k));
       
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
		String [] movie;
		ArrayList<String> times = new ArrayList<String>();
		String fileName = "C:\\Users\\David\\Documents\\UCL\\Introductory programming\\coursework\\CinemaBooking\\src\\application\\Cinema_Database.txt";
		try {
			
	        // FileReader reads text files in the default encoding.
	        FileReader fileReader = 
	            new FileReader(fileName);

	        // Always wrap FileReader in BufferedReader.
	        BufferedReader bufferedReader = 
	            new BufferedReader(fileReader);
	        bufferedReader.readLine();
	        Movies.reset();
	        while((line = bufferedReader.readLine()) != null) {
	            System.out.println(line); 
	        	
	        	
	        	movie = line.split(";");
	        	String timeString = movie[movie.length-1];
	        	String[] timeList = timeString.split(",");
	        	for(int i=0; i<timeList.length;i++) {
	        		times.add(timeList[i]);
	        	}
	        	
	        	Movies.addMovie(movie[0], movie[1], movie[2], movie[3], times); 
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
