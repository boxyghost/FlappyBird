package brynn.javafx.flappyBird;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
	
	AnimationTimer gameLoop;
	
	@FXML
	private AnchorPane plane;
	
	@FXML
	private Rectangle bird;
	
	double yDelta = 0.2;
	double time = 0;
	int jumpHeight = 100;
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		load();
		
		gameLoop = new AnimationTimer() {
			@Override
			public void handle(long l) {
				update();
			}
		};
		gameLoop.start();
	}
	
	@FXML
	void pressed(KeyEvent event) {
		if(event.getCode() == KeyCode.SPACE) {
			fly();
		}
	}
	
	private void fly() {
		time = 0;
		if(bird.getLayoutY() + bird.getY() <= jumpHeight) {
			moveBirdY(-(bird.getLayoutY() + bird.getY()));
			return;
		}
		
		moveBirdY(-jumpHeight);
	}
	
	// This is called every game frame
	private void update() {
		time++;
		// Move the bird down according to acceleration
		moveBirdY(yDelta * time);
		
		// Check if bird is dead, go to menu on death
		if (checkDeath()) {
			resetBird();
		}
	}
	
	private void load() {
		System.out.println("Game is starting");
	}
	
	
	// Move the bird around
	private void moveBirdY(double delta) {
		bird.setY(bird.getY() + delta);
	}
	
	// Did bird go out of bounds?
	private boolean checkDeath() {
		double birdY = bird.getLayoutY() + bird.getY();
		return birdY >= plane.getHeight();
	}
	
	private void resetBird() {
		bird.setY(0);
		time = 0;
	}

}
