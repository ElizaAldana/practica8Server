package main;

import processing.core.PApplet;

public class Bala {

	private PApplet app;
	private int x,y;
	private boolean shootright;
	
	public Bala(PApplet app, int x, int y, boolean shootright) {
		this.app = app;
		this.x = x;
		this.y = y;
		this.shootright = shootright;
	}
	
	public void pintar() {
		app.fill(255);
		app.ellipse(this.x,this.y,10,10);
		
		if(shootright) {
			x+=5;
		}else {
			x-=5;
		}
	}

	public PApplet getApp() {
		return app;
	}

	public void setApp(PApplet app) {
		this.app = app;
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

	public boolean isShootright() {
		return shootright;
	}

	public void setShootright(boolean shootright) {
		this.shootright = shootright;
	}

	
	
}
