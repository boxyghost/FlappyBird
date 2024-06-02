package brynn.javafx.flappyBird;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{
	
	AnimationTimer gameLoop;
	
	@FXML
	private AnchorPane plane;
	
	@FXML
	private Rectangle bird;
	
	@FXML
	private Text score;
	
	private double accelerationTime = 0;
	private int gameTime = 0;
	private int scoreCounter = 0;
	
	
	private Bird flappy;
	private Obstacles wallGenerator;
	
	ArrayList<Rectangle> obstacles = new ArrayList<>();
	
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		int jumpHeight = 75;
		flappy = new Bird(bird, jumpHeight);
		double planeHeight = 600;
		double planeWidth = 400;
		wallGenerator = new Obstacles(plane, planeHeight, planeWidth);
		
		gameLoop = new AnimationTimer() {
			@Override
			public void handle(long l) {
				update();
			}
		};
		
		load();
		gameLoop.start();
	}
	
	@FXML
	void pressed(KeyEvent event) {
		if(event.getCode() == KeyCode.SPACE) {
			flappy.fly();
			accelerationTime = 0;
		}
	}
	
	
	// This is called every game frame
	private void update() {
		// Move bird
		gameTime++;
		accelerationTime++;
		double yDelta = 0.02;
		flappy.moveBirdY(yDelta*accelerationTime);
		
		// Update points
		if (pointChecker(obstacles, bird)) {
			scoreCounter++;
			score.setText(String.valueOf(scoreCounter));
		}
		
		// Add another wall every 500 frames
		wallGenerator.moveObstacles(obstacles);
		if(gameTime % 500 == 0) {
			obstacles.addAll(wallGenerator.createObstacles());
		}
		
		if(flappy.isBirdDead(obstacles, plane)) {
			resetGame();
		}
	}

	
	private void load() {
		obstacles.addAll(wallGenerator.createObstacles());
	}
	
	private void resetGame() {
		bird.setY(0);
		plane.getChildren().removeAll(obstacles);
		obstacles.clear();
		gameTime = 0;
		accelerationTime = 0;
		scoreCounter = 0;
		score.setText(String.valueOf(scoreCounter));
	}
	
	private boolean pointChecker(ArrayList<Rectangle> walls, Rectangle bird) {
		for(Rectangle r: walls) {
			int birdX = (int) (bird.getLayoutX() + bird.getX());
			if(((int)(r.getLayoutX() + r.getX()) == birdX)){
	                return true;
	        }
		}
		return false;
	}
	

}
