package brynn.javafx.flappyBird;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;


public class Bird {
	
	private Rectangle bird;
	private int jumpHeight;
	CollisionHandler collisionHandler = new CollisionHandler();
	
	public Bird(Rectangle bird, int jumpHeight) {
		this.bird = bird;
		this.jumpHeight = jumpHeight;
	}
	
	// Decide how far to move the bird (don't clip the top)
	public void fly() {
		double movement = -jumpHeight;
		if(bird.getLayoutY() + bird.getY() <= jumpHeight) {
			movement = -(bird.getLayoutY() + bird.getTranslateY());
		}
		moveBirdY(movement);
	}
	
	// Actually move the rectangle
	public void moveBirdY(double delta) {
		bird.setY(bird.getY()+delta);
	}
	
	// Check if dead
	public boolean isBirdDead(ArrayList<Rectangle> obstacles, AnchorPane plane) {
		double birdY = bird.getLayoutY() + bird.getY();
		
		if(collisionHandler.collisionDetection(obstacles, bird)) {
			return true;
		}
		
		return birdY >= plane.getHeight();		
	}

}
