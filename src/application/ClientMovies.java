package application;

import java.util.ArrayList;

public class ClientMovies {
	
	private static ArrayList<String[]> personalMovieList = new ArrayList<String[]>();
	
	private static String details[] = new String[3];
	
	public static void setName(String name) {
		 ClientMovies.details[0]=name;
	}
	public static void setDate(String date) {
		ClientMovies.details[1]=date;
	}
	public static void setTime(String time) {
		ClientMovies.details[2]=time;
	}
	
	public static void setMovieDetails(String name, String date, String time) {
		ClientMovies.details[0]=name;
		ClientMovies.details[1]=date;
		ClientMovies.details[2]=time;
	}
	
	public static String[] getmovie(){
		return ClientMovies.details;
	}
	
	public static void addToPersonalList() {
		ClientMovies.personalMovieList.add(ClientMovies.details);
		
	}
	
	public static ArrayList<String[]> getPersonalList() {
		return ClientMovies.personalMovieList;	
	}
	
	// there may a problem with having movies in the array list, for all the other details there is 
	//always an element in the list but with movies it could be that the person hasn't booked a movie  
	//at all and so the element count doesn't match anymore.
	
	
	

}
