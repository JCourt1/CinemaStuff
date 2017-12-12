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

/**
 * This is controller class that controls the MediaPlayer.fxml window. 
 * It loads and plays the video file into a JavaFX mediaplayer.   
 * 
 * @author David
 *
 */
public class MediaPlayerController implements Initializable{

	/***************************************************************************************
	 * This whole class is taken from the following source:
	 * Title: JavaFx Tutorial For Beginners 31 - Creating Media Player in JavaFX
	 * Author: ProgrammingKnowledge
	 * Date: 20/01/2016
	 * Availability: https://www.youtube.com/watch?v=sjiS4mhb0gQ
	 *
	 ***************************************************************************************/
	@FXML private MediaView mv;
	MediaPlayer mp;
	Media me;
	
	String title = MainClientControl.getButtonId();
	
	/**
	 * This is the main method of the MediaPlayerController class. 
	 * It is called when the MediaPlayer.fxml window is opened.
	 * It first gets the path of the media to be played. Loads the media into the JavaFX MediaPlayer and the MediaPlayer into the JavaFX MediaView.
	 * 
	 */

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String path = new File(returnTrailerPath()).getAbsolutePath();
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
	
	/**
	 * This method plays the media.
	 * @param ActionEvent 
	 */
	public void Play(ActionEvent event) {
		mp.play();
	
	}
	/**
	 * This method pauses the media.
	 * @param ActionEvent
	 */
	public void Pause(ActionEvent event) {
		mp.pause();
	
	}
	
	/**
	 * This method rewinds the media to the start.
	 * @param ActionEvent
	 */
	public void backToStart(ActionEvent event) {
		mp.seek(mp.getStartTime());
	
	}
	
	/**
	 * This method rewinds the media to the start and stops it.
	 * @param ActionEvent
	 */
	public void Stop(ActionEvent event) {
		mp.seek(mp.getStartTime());
		mp.stop();
	
	}
	/**
	 * This method returns the path of the media to be played. The path is saved in the Film.java class and is retrieved through the JavaFX ObservableList FilmData
	 * by using it's getter method 'getFilmData()'
	 * @return String
	 */
	public String returnTrailerPath() {
		String path = null;
		for(Film film: MainApplication.getFilmData()) {
			if(film.getName().equals(MainClientControl.getButtonId())){
				path=film.getPath();
			}
		}
		return path;
	}
	
	
	

}
