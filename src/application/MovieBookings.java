package application;
import java.util.ArrayList; 

public class MovieBookings {
	
	private static ArrayList<Integer> ids = new  ArrayList<Integer>();
	private static ArrayList<String> titles = new ArrayList<String>();
	private static ArrayList<String> screeningDates = new ArrayList<String>();
	private static ArrayList<String> screeningTimes = new ArrayList<String>();
	private static ArrayList<String> bookingDates = new ArrayList<String>();
	private static ArrayList<String> seats = new ArrayList<String>();



public static void addBooking(int id, String title, String screeningdate, String screeningtime, String bookingdate, String seat) {
	MovieBookings.ids.add(id);
	MovieBookings.titles.add(title);
	MovieBookings.screeningDates.add(screeningdate);
	MovieBookings.screeningTimes.add(screeningtime);
	MovieBookings.bookingDates.add(bookingdate);
	MovieBookings.seats.add(seat);
}

public static String[] getBookingData(int index) {
	String[] movie= {Integer.toString(MovieBookings.ids.get(index)),MovieBookings.titles.get(index),MovieBookings.screeningDates.get(index),MovieBookings.screeningTimes.get(index),MovieBookings.bookingDates.get(index), MovieBookings.seats.get(index)};
	return movie;
}

public static int getNumberOfBookings() {
	return MovieBookings.ids.size();
}

public static void reset() {
	MovieBookings.ids.clear();
	MovieBookings.titles.clear();
	MovieBookings.screeningDates.clear();
	MovieBookings.screeningTimes.clear();
	MovieBookings.bookingDates.clear();
	MovieBookings.seats.clear();
}

}