package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class MediaPlayerController implements Initializable{

	@FXML private MediaView mv;
	MediaPlayer mp;
	Media me;
	String title = MainClientControl.getButtonId();
	//All that needs to be changed is that the  movie path is loaded from FilmData 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String path = new File("resources/videos/vid.mp4").getAbsolutePath();
		me = new Media(new File(path).toURI().toString());
		mp=new MediaPlayer(me);
		mv.setMediaPlayer(mp);
		mp.play();
		
		//this preserves the aspect ratio of the video, the video is resized according to the scene
		DoubleProperty width = mv.fitWidthProperty();
		DoubleProperty height = mv.fitHeightProperty();
		width.bind(Bindings.selectDouble(mv.sceneProperty(), "width"));
		height.bind(Bindings.selectDouble(mv.sceneProperty(), "height"));
		
	}
	
	public void Play(ActionEvent event) {
		mp.play();
	
	}
	public void Pause(ActionEvent event) {
		mp.pause();
	
	}
	public void backToStart(ActionEvent event) {
		mp.seek(mp.getStartTime());
	
	}
	
	public void Stop(ActionEvent event) {
		mp.seek(mp.getStartTime());
		mp.stop();
	
	}
	
	
	

}
