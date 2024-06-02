package brynn.javafx.flappyBird;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Obstacles {
	
	private AnchorPane plane;
	private double planeHeight;
	private double planeWidth;
	Random random = new Random();
	
	public Obstacles(AnchorPane plane, double planeHeight, double planeWidth) {
		this.plane = plane;
		this.planeHeight = planeHeight;
		this.planeWidth = planeWidth;

	}
	
	public ArrayList<Rectangle> createObstacles(){
		
		int width = 25;
		double xPos = planeWidth;
		double space = 200;
		double rth = random.nextInt((int)(planeHeight - space - 100)) + 50;
		double rbh = planeHeight - space - rth;
		
		Rectangle top = new Rectangle(xPos, 0, width, rth);
		Rectangle bottom = new Rectangle(xPos, rth + space, width, rbh);
		
		plane.getChildren().addAll(top, bottom);
		return new ArrayList<>(Arrays.asList(top, bottom));
	}
	
	public void moveObstacles(ArrayList<Rectangle> obstacles) {
		ArrayList<Rectangle> outOfScreen = new ArrayList<>();
		
		for (Rectangle r: obstacles) {
			moveRectangle(r, -0.75);
			
			if(r.getX() <= -r.getWidth()) {
				outOfScreen.add(r);
			}
		}
		obstacles.removeAll(outOfScreen);
		plane.getChildren().removeAll(outOfScreen);
	}
	
	private void moveRectangle(Rectangle r, double delta) {
		r.setX(r.getX() + delta);
	}

}
