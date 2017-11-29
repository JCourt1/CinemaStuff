package application;


import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class CinemaRoomController {
	
	
	
	@FXML
	private Rectangle   a1;
	
	@FXML
	private Rectangle   a2;
	
	@FXML
	private Rectangle   a3;
	
	@FXML
	private Rectangle   a4;
	
	@FXML
	private Rectangle   a5;
	
	@FXML
	private Rectangle   a6;
	
	@FXML
	private Rectangle   b1;
	
	@FXML
	private Rectangle   b2;
	
	@FXML
	private Rectangle   b3;
	
	@FXML
	private Rectangle   b4;
	
	@FXML
	private Rectangle   b5;
	
	@FXML
	private Rectangle   b6;
	
	@FXML
	private Rectangle   c1;
	
	@FXML
	private Rectangle   c2;
	
	@FXML
	private Rectangle   c3;
	
	@FXML
	private Rectangle   c4;
	
	@FXML
	private Rectangle   c5;
	
	@FXML
	private Rectangle   c6;
	
	@FXML
	private Rectangle   d1;
	
	@FXML
	private Rectangle   d2;
	
	@FXML
	private Rectangle   d3;
	
	@FXML
	private Rectangle   d4;
	
	@FXML
	private Rectangle   d5;
	
	@FXML
	private Rectangle   d6;
	
	@FXML
	private Rectangle   e1;
	
	@FXML
	private Rectangle   e2;

	@FXML
	private Rectangle   e3;
	
	@FXML
	private Rectangle   e4;
	
	@FXML
	private Rectangle   e5;
	
	@FXML
	private Rectangle   e6;
	
	@FXML
	private Rectangle   f1;
	
	@FXML
	private Rectangle   f2;
	
	@FXML
	private Rectangle   f3;
	
	@FXML
	private Rectangle   f4;
	
	@FXML
	private Rectangle   f5;
	
	@FXML
	private Rectangle   f6;
	
	

	
	
	
	
	String[] seatList  = {"a1","a2","a3","a4","a5","a6","b1","b2","b3","b4","b5","b6","c1","c2","c3","c4","c5","c6","d1","d2","d3","d4","d5","d6","e1","e2","e3","e4","e5","e6","f1","f2","f3","f4","f5","f6"};
	
	
	Map<String,Rectangle> hashtable = new HashMap<String,Rectangle>();

	private static int[] clickCounts = new int[36];
	
	@FXML
	void initialize() {	
		
		
		hashtable.put("a1", a1);
		hashtable.put("a2", a1);
		hashtable.put("a3", a1);
		hashtable.put("a4", a1);
		hashtable.put("a5", a1);
		hashtable.put("a6", a1);
		hashtable.put("b1", b1);
		hashtable.put("b2", b2);
		hashtable.put("b3", b3);
		hashtable.put("b4", b4);
		hashtable.put("b5", b5);
		hashtable.put("b6", b6);
		hashtable.put("c1", c1);
		hashtable.put("c2", c2);
		hashtable.put("c3", c3);
		hashtable.put("c4", c4);
		hashtable.put("c5", c5);
		hashtable.put("c6", c6);
		hashtable.put("d1", d1);
		hashtable.put("d2", d2);
		hashtable.put("d3", d3);
		hashtable.put("d4", d4);
		hashtable.put("d5", d5);
		hashtable.put("d6", d6);
		hashtable.put("e1", e1);
		hashtable.put("e2", e2);
		hashtable.put("e3", e3);
		hashtable.put("e4", e4);
		hashtable.put("e5", e5);
		hashtable.put("e6", e6);
		hashtable.put("f1", f1);
		hashtable.put("f2", f2);
		hashtable.put("f3", f3);
		hashtable.put("f4", f4);
		hashtable.put("f5", f5);
		hashtable.put("f6", f6);
		
		
		
		
		System.out.println("INITIALIZED");

		
		for(int i=0; i<MovieBookings.getBookedSeats().size(); i++) {
		for(int j=0; j<seatList.length;j++) {
			if(seatList[j].equals(MovieBookings.getBookedSeats().get(i))) {
				
			hashtable.get(seatList[j]).setFill(Color.web("#ea2020"));
				
			}
			else{
				hashtable.get(seatList[i]).setFill(Color.web("#156835"));
			}
			}
		}
		
		
	}
	
	
	


@FXML
public void ClickedLabel(MouseEvent event) throws Exception{
	
	hashtable.put("a1", a1);
	hashtable.put("a2", a1);
	hashtable.put("a3", a1);
	hashtable.put("a4", a1);
	hashtable.put("a5", a1);
	hashtable.put("a6", a1);
	hashtable.put("b1", b1);
	hashtable.put("b2", b2);
	hashtable.put("b3", b3);
	hashtable.put("b4", b4);
	hashtable.put("b5", b5);
	hashtable.put("b6", b6);
	hashtable.put("c1", c1);
	hashtable.put("c2", c2);
	hashtable.put("c3", c3);
	hashtable.put("c4", c4);
	hashtable.put("c5", c5);
	hashtable.put("c6", c6);
	hashtable.put("d1", d1);
	hashtable.put("d2", d2);
	hashtable.put("d3", d3);
	hashtable.put("d4", d4);
	hashtable.put("d5", d5);
	hashtable.put("d6", d6);
	hashtable.put("e1", e1);
	hashtable.put("e2", e2);
	hashtable.put("e3", e3);
	hashtable.put("e4", e4);
	hashtable.put("e5", e5);
	hashtable.put("e6", e6);
	hashtable.put("f1", f1);
	hashtable.put("f2", f2);
	hashtable.put("f3", f3);
	hashtable.put("f4", f4);
	hashtable.put("f5", f5);
	hashtable.put("f6", f6);
	
		
		int j=0;
		char char1 = event.getSource().toString().charAt(13);
		char char2 = event.getSource().toString().charAt(14);
		String  seat = Character.toString(char1)+Character.toString(char2);
		
		for(int i=0; i<seatList.length; i++) {
			if(seatList[i].equals(seat)) {
				j=i;
			}
		}
		
		clickCounts[j]++;
		if(clickCounts[j]%2!=0) {
		hashtable.get(seat).setFill(Color.YELLOW);
		}
		else {
			hashtable.get(seat).setFill(Color.GREEN);
		}
		
		
	
	
	
	
	
}


}
