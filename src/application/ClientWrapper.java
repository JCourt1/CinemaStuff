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
@XmlRootElement(name = "clients")
public class ClientWrapper {
	
	private List<Client> clients;
	
	@XmlElement(name = "client")
	public  List<Client> getClients() {
		return clients;
	}
	
	public  void setClients(List<Client> clients) {
		this.clients = clients;
	}
}