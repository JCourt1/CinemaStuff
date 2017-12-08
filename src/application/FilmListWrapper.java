package application;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Wrapper class for saving list of films to XML.
 * 
 * @author josephcourtley
 *
 */
@XmlRootElement(name = "films")
public class FilmListWrapper {
	
	private List<Film> films;
	
	@XmlElement(name = "film")
	public List<Film> getFilms() {
		return films;
	}
	
	public void setFilms(List<Film> films) {
		this.films = films;
	}
}