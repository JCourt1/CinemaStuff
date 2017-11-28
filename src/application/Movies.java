package application;

import java.util.ArrayList;
public class Movies {
	
	private static ArrayList<String> titles = new  ArrayList<String>();
	private static ArrayList<String> descriptions = new  ArrayList<String>();
	private static ArrayList<String> startdates = new  ArrayList<String>();
	private static ArrayList<String> enddates = new  ArrayList<String>();
	private static ArrayList<ArrayList<String>> times = new  ArrayList<ArrayList<String>>();
	



public static void addMovie(String title,  String description, String  startdate, String enddate, ArrayList<String> times) {
	Movies.titles.add(title);
	Movies.descriptions.add(description);
	Movies.startdates.add(startdate);
	Movies.enddates.add(enddate);
	Movies.times.add(times);
}



public static ArrayList<Object> getMovieData(int m){
	ArrayList<Object> movie =  new ArrayList<Object>();
	movie.add(Movies.titles.get(m));
	movie.add(Movies.descriptions.get(m));
	movie.add(Movies.startdates.get(m));
	movie.add(Movies.enddates.get(m));
	movie.add(Movies.times.get(m));
	return movie;
}

public static int getNumberOfMovies() {
	return Movies.titles.size();
}

public static void reset() {
	Movies.titles.clear();
	Movies.descriptions.clear();
	Movies.startdates.clear();
	Movies.enddates.clear();
	Movies.times.clear();
}



}
