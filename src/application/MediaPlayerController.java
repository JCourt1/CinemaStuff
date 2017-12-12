package application;

import java.io.File;
import java.net.URL;

import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

/**
 * This is controller class that controls the MediaPlayer.fxml window. It loads
 * and plays the video file into a JavaFX mediaplayer.
 * 
 * @author David
 *
 */
public class MediaPlayerController implements Initializable {

	/***************************************************************************************
	 * This whole class is taken from the following sources: 
	 * 
	 * Title: JavaFx Tutorial
	 * For Beginners 31 - Creating Media Player in JavaFX Author:
	 * ProgrammingKnowledge Date: 20/01/2016 Availability:
	 * https://www.youtube.com/watch?v=sjiS4mhb0gQ
	 *
	 ***************************************************************************************/

	@FXML
	private Duration duration;
	@FXML
	private Slider timeSlider;
	@FXML
	private Label playTime;
	@FXML
	private Slider volumeSlider;
	@FXML
	private HBox mediaBar;
	@FXML
	private Button playButton;

	@FXML
	private MediaView mv;
	MediaPlayer mp;
	Media me;

	private final boolean repeat = false;
	private boolean stopRequested = false;
	private boolean atEndOfMedia = false;

	String title = MainClientControl.getButtonId();

	/**
	 * This is the main method of the MediaPlayerController class. It is called when
	 * the MediaPlayer.fxml window is opened. It first gets the path of the media to
	 * be played. Loads the media into the JavaFX MediaPlayer and the MediaPlayer
	 * into the JavaFX MediaView.
	 * 
	 */

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String path = new File("resources/videos/vid.mp4").getAbsolutePath();
		me = new Media(new File(path).toURI().toString());
		mp = new MediaPlayer(me);

		mp.currentTimeProperty().addListener(new InvalidationListener() {
			public void invalidated(Observable ov) {
				updateValues();
			}
		});

		mp.setOnPlaying(new Runnable() {
			public void run() {
				if (stopRequested) {
					mp.pause();
					stopRequested = false;
				} else {
					playButton.setText("||");
				}
			}
		});

		mp.setOnPaused(new Runnable() {
			public void run() {
				System.out.println("onPaused");
				playButton.setText(">");
			}
		});

		mp.setOnReady(new Runnable() {
			public void run() {
				duration = mp.getMedia().getDuration();
				updateValues();
			}
		});

		mp.setCycleCount(repeat ? MediaPlayer.INDEFINITE : 1);
		mp.setOnEndOfMedia(new Runnable() {
			public void run() {
				if (!repeat) {
					playButton.setText(">");
					stopRequested = true;
					atEndOfMedia = true;
				}
			}
		});

		HBox.setHgrow(timeSlider, Priority.ALWAYS);
		timeSlider.setMinWidth(50);
		timeSlider.setMaxWidth(Double.MAX_VALUE);
		timeSlider.valueProperty().addListener(new InvalidationListener() {
			public void invalidated(Observable ov) {
				if (timeSlider.isValueChanging()) {
					// multiply duration by percentage calculated by slider position
					mp.seek(duration.multiply(timeSlider.getValue() / 100.0));
				}
			}
		});

		volumeSlider.valueProperty().addListener(new InvalidationListener() {
			public void invalidated(Observable ov) {
				if (volumeSlider.isValueChanging()) {
					mp.setVolume(volumeSlider.getValue() / 100.0);
				}
			}
		});

		mv.setMediaPlayer(mp);
		DoubleProperty width = mv.fitWidthProperty();
		DoubleProperty height = mv.fitHeightProperty();
		width.bind(Bindings.selectDouble(mv.sceneProperty(), "width"));
		height.bind(Bindings.selectDouble(mv.sceneProperty(), "height"));
	}

	/**
	 * This method plays the media.
	 * 
	 * @param ActionEvent
	 */
	public void Play(ActionEvent event) {

		Status status = mp.getStatus();

		if (status == Status.UNKNOWN || status == Status.HALTED) {
			// don't do anything in these states
			return;
		}

		if (status == Status.PAUSED || status == Status.READY || status == Status.STOPPED) {
			// rewind the movie if we're sitting at the end
			if (atEndOfMedia) {
				mp.seek(mp.getStartTime());
				atEndOfMedia = false;
			}
			mp.play();
		} else {
			mp.pause();
		}

	}

	/**
	 * This method pauses the media.
	 * 
	 * @param ActionEvent
	 */
	public void Pause(ActionEvent event) {
		mp.pause();

	}

	/**
	 * This method rewinds the media to the start.
	 * 
	 * @param ActionEvent
	 */
	public void backToStart(ActionEvent event) {
		mp.seek(mp.getStartTime());

	}

	/**
	 * This method rewinds the media to the start and stops it.
	 * 
	 * @param ActionEvent
	 */
	public void Stop(ActionEvent event) {
		mp.seek(mp.getStartTime());
		mp.stop();

	}

	/**
	 * This method returns the path of the media to be played. The path is saved in
	 * the Film.java class and is retrieved through the JavaFX ObservableList
	 * FilmData by using it's getter method 'getFilmData()'
	 * 
	 * @return String
	 */
	public String returnTrailerPath() {
		String path = null;
		for (Film film : MainApplication.getFilmData()) {
			if (film.getName().equals(MainClientControl.getButtonId())) {
				path = film.getPath();
			}
		}
		return path;
	}

	protected void updateValues() {
		if (playTime != null && timeSlider != null && volumeSlider != null) {
			Platform.runLater(new Runnable() {
				public void run() {
					Duration currentTime = mp.getCurrentTime();
					playTime.setText(formatTime(currentTime, duration));
					timeSlider.setDisable(duration.isUnknown());
					if (!timeSlider.isDisabled() && duration.greaterThan(Duration.ZERO)
							&& !timeSlider.isValueChanging()) {
						timeSlider.setValue(currentTime.divide(duration).toMillis() * 100.0);
					}
					if (!volumeSlider.isValueChanging()) {
						volumeSlider.setValue((int) Math.round(mp.getVolume() * 100));
					}
				}
			});
		}
	}

	private static String formatTime(Duration elapsed, Duration duration) {
		int intElapsed = (int) Math.floor(elapsed.toSeconds());
		int elapsedHours = intElapsed / (60 * 60);
		if (elapsedHours > 0) {
			intElapsed -= elapsedHours * 60 * 60;
		}
		int elapsedMinutes = intElapsed / 60;
		int elapsedSeconds = intElapsed - elapsedHours * 60 * 60 - elapsedMinutes * 60;

		if (duration.greaterThan(Duration.ZERO)) {
			int intDuration = (int) Math.floor(duration.toSeconds());
			int durationHours = intDuration / (60 * 60);
			if (durationHours > 0) {
				intDuration -= durationHours * 60 * 60;
			}
			int durationMinutes = intDuration / 60;
			int durationSeconds = intDuration - durationHours * 60 * 60 - durationMinutes * 60;
			if (durationHours > 0) {
				return String.format("%d:%02d:%02d/%d:%02d:%02d", elapsedHours, elapsedMinutes, elapsedSeconds,
						durationHours, durationMinutes, durationSeconds);
			} else {
				return String.format("%02d:%02d/%02d:%02d", elapsedMinutes, elapsedSeconds, durationMinutes,
						durationSeconds);
			}
		} else {
			if (elapsedHours > 0) {
				return String.format("%d:%02d:%02d", elapsedHours, elapsedMinutes, elapsedSeconds);
			} else {
				return String.format("%02d:%02d", elapsedMinutes, elapsedSeconds);
			}
		}
	}

}