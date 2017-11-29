package application;
import java.util.ArrayList;


public class Client {
	
	private static ArrayList<Integer> ids = new ArrayList<Integer>();
	private  static ArrayList<String> usernames  = new ArrayList<String>();
	private static ArrayList<String> passwords  = new ArrayList<String>();
	private  static ArrayList<String> firstnames = new ArrayList<String>();
	private static ArrayList<String> lastnames = new ArrayList<String>();
	private static ArrayList<String> emails = new ArrayList<String>();
	private static ArrayList<ArrayList<String[]>>  movieslist = new ArrayList<ArrayList<String[]>>();
	
	
	
	public static int lastID() { 
		if(Client.ids.size()>0) {
			return Client.ids.get(Client.ids.size()-1);
		}
		
		else {
			int zero = 0;
			return zero;
			}
		
	}
	
	public static ArrayList<Integer> getIDs(){
		return Client.ids;
	}
	public static ArrayList<String> getUserNames() {
		return Client.usernames;
	}
	public static ArrayList<String> getPasswords() {
		return Client.passwords;
	}
	
	
	public static void setUserName(int id, String username) {
		int index = Client.ids.indexOf(id);
		Client.usernames.set(index, username);
	}
	
	public static void setPassword(int id, String password) {
		int index = Client.ids.indexOf(id);
		Client.passwords.set(index, password);
	}
	
	public static void setEmail(int id, String email) {
		int index = Client.ids.indexOf(id);
		Client.emails.set(index, email);
	}
	public static void setFirstName(int id, String firstname) {
		int index = Client.ids.indexOf(id);
		Client.firstnames.set(index, firstname);
	}
	public static void setLastName(int id, String lastname) {
		int index = Client.ids.indexOf(id);
		Client.lastnames.set(index, lastname);
	}
	
	
	
	

	public static void addToList(String username, String password, String firstName, String lastName, String email) {
		Client.usernames.add(username);
		Client.passwords.add(password);
		Client.firstnames.add(firstName);
		Client.lastnames.add(lastName);
		Client.emails.add(email);
		Client.ids.add(Client.lastID() + 1);
		
		
		
	}
	
	public static void addToEmptyList(int id, String username, String password, String firstName, String lastName,  String email) {
		Client.ids.add(id);
		Client.usernames.add(username);
		Client.passwords.add(password);
		Client.firstnames.add(firstName);
		Client.lastnames.add(lastName);
		Client.emails.add(email);
		
	}
	
	public static void reset() {
		Client.usernames.clear();
		Client.passwords.clear();
		Client.firstnames.clear();
		Client.lastnames.clear();
		Client.emails.clear();
		Client.ids.clear();
	}
	
	public static int getNumberOfClients() {
		return Client.ids.size();
	}
	
	public static ArrayList<String> getClientDataExternal(int id) {
		ArrayList<String> details = new ArrayList<String>();
		int index = Client.ids.indexOf(id);
		details.add(Integer.toString(Client.ids.get(index)));
		details.add(Client.usernames.get(index));
		details.add(Client.passwords.get(index));
		details.add(Client.firstnames.get(index));
		details.add(Client.lastnames.get(index));
		details.add(Client.emails.get(index));
		return details;
		
		
	}
	public static ArrayList<String> getClientData(int m) {
		ArrayList<String> details = new ArrayList<String>();
		details.add(Integer.toString(Client.ids.get(m)));
		details.add(Client.usernames.get(m));
		details.add(Client.passwords.get(m));
		details.add(Client.firstnames.get(m));
		details.add(Client.lastnames.get(m));
		details.add(Client.emails.get(m));
		return details;
		
		
	}
	
	
	
	
	public static void printClientDetails(int id) {
		int index = Client.ids.indexOf(id);
		System.out.println("ID: "+ Client.ids.get(index));
		System.out.println("username:  " + Client.usernames.get(index));
		System.out.println("password: " + Client.passwords.get(index));
		System.out.println("First Name:  " + Client.firstnames.get(index));
		System.out.println("Last Name: " + Client.lastnames.get(index));
		System.out.println("Email: " + Client.emails.get(index));
		
	}
	
	
	
	

	
	
	
	

}
