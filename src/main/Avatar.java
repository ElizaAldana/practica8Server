package main;

import processing.core.PApplet;

public class Avatar {
	
	private int x,y, color;
	private PApplet main;
	private boolean upMov=false;
	private boolean downMov=false;
	private boolean rightMov=false;
	private boolean leftMov=false;
	
	

	public Avatar(PApplet main, int x, int y, int color) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.main = main;
	}
	
	public void pintar() {
		main.fill(this.color);
		main.ellipse(this.x,this.y,50,50);
		
		//Se dibuja constantemente
		if(upMov) {
			this.y-=3;
		}
		if(downMov) {
			this.y+=3;
		}
		if(rightMov) {
			this.x+=3;
		}
		if(leftMov) {
			this.x-=3;
		}
	}
	
	
	public void moveRight() {
		this.x+=3;
	}
	public void moveLeft() {
		this.x-=3;
	}
	public void moveUp() {
		this.y-=3;
	}
	public void moveDown() {
		this.y+=3;
	}
	
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	//up
	public void activateUpmov() {
		upMov = true;
	}
	public void desactivateUpmove() {
		upMov = false;
	}

	//down
	public void activateDownmove() {
		downMov = true;
	}
	public void desactivateDownmove() {
		downMov = false;
	}
	
	//right
	public void activateRightmove() {
		rightMov = true;
	}
	public void desactivateRightmove() {
		rightMov = false;
	}
	
	//left
	public void activateLeftmove() {
		leftMov = true;
	}

	public void desactivateLeftmove() {
		leftMov = false;
	}
}
