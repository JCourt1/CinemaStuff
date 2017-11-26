package application.models.films;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Wrapper class for saving list of seances to XML.
 * 
 * @author josephcourtley
 *
 */
@XmlRootElement(name = "seances")
public class SeanceListWrapper {
	
	private List<Seance> seances;
	
	@XmlElement(name = "seance")
	public List<Seance> getSeances() {
		return seances;
	}
	
	public void setSeances(List<Seance> seances) {
		this.seances = seances;
	}
}
