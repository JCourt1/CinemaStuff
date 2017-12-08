package application;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Wrapper class for saving list of films to XML.
 * 
 * @author josephcourtley
 *
 */
@XmlRootElement(name = "bookings")
public class BookingWrapper {
	
	private List<Booking> bookings;
	
	@XmlElement(name = "booking")
	public List<Booking> getBookings() {
		return bookings;
	}
	
	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
}