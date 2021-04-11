package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import com.google.gson.Gson;

import processing.core.PApplet;

public class Main extends PApplet {
	

	//Globales
	int xBolita = -1000;
	int yBolita = -1000;
	int screen;
	
	private Avatar player1;
	private Avatar player2;

	
	private TCPconnectionP1 conexionP1;
	private TCPconnectionP2 conexionP2;

	private ArrayList<Bala> balasP1;
	private ArrayList<Bala> balasP2;
	
	public static void main(String[] args) {
		PApplet.main("main.Main");
	}

	// 1
	public void settings() {
		size(500, 500);
	}

	// 1
	public void setup() {
		screen = 0;
		conexionP1 = new TCPconnectionP1();
		//Metodo de suscripcion
		conexionP1.setMain(this);
		conexionP1.start();
	
		conexionP2 = new TCPconnectionP2();
		//Metodo de suscripcion
		conexionP2.setMain(this);
		conexionP2.start();
		
		player1 = new Avatar(this, 100, 100,color(255,0,0));
		player2 = new Avatar(this, 400, 400,color(0,0,255));

		
		//Array de las balas
		balasP1 = new ArrayList<>();
		balasP2 = new ArrayList<>();
		
	}

	// Inifito
	public void draw() {
		String ganador = "";
		switch(screen) {
		case 0:
			background(0, 0, 0);
			fill(255, 0, 0);
			ellipse(xBolita, yBolita, 50, 50);
			
			player1.pintar();
			player2.pintar();
			
			for (int i=0 ; i<balasP1.size(); i++) {
				Bala b1 = balasP1.get(i);
				b1.pintar();
				 
				if(b1.getX()<0) {
					balasP2.remove(i);
					break;
				}
				float p1 = dist(player2.getX(),player2.getY(), (balasP1.get(i).getX()), balasP1.get(i).getY());
				if(p1<5) {
					screen = 1;
					ganador="P1";
				}
				
			}
			for (int i=0 ; i<balasP2.size(); i++) {
				Bala b2 = balasP2.get(i);
				b2.pintar();
				 
				if(b2.getX()<0) {
					balasP2.remove(i);
					break;
				}
				float p2 = dist(player1.getX(),player1.getY(), (balasP2.get(i).getX()), balasP2.get(i).getY());
				if(p2<5) {
					screen = 1;
					ganador="P2";
				}
			}
			break;
			
		case 1:
			background(0);
			textSize(30);
			text("Game Over, Ganó "+ ganador, 100, 250);
			//System.out.println("si me pegó");
			
			break;
		}
		
	}
	
	
	//El metodo de notificacion: Aqui se recibe la informacion del evento
	public void notificar(Coordenada c, Object obj) {
	
	//obj será un objeto de TCPconnectionP1?
	if(obj instanceof TCPconnectionP1) {
		System.out.println("JUGADOR 1:" + c.getAccion());
		
		switch(c.getAccion()) {
		//Down
		case "DOWNSTART":
			player1.activateDownmove();
			break;
		case "DOWNSTOP":
			player1.desactivateDownmove();
			break;
		//Up	
		case "UPSTART":
			player1.activateUpmov();
			break;
		case "UPSTOP":
			player1.desactivateUpmove();
			break;
		//Right	
		case "RIGHTSTART":
			player1.activateRightmove();
			break;	
		case "RIGHTSTOP":
			player1.desactivateRightmove();
			break;	
		//Left
		case "LEFTSTART":
			player1.activateLeftmove();
			break;
		case "LEFTSTOP":
			player1.desactivateLeftmove();
			break;
			
		//Disparo
		case "FIRE":
			Bala bala = new Bala(this, player1.getX(), player1.getY(), false);
			balasP1.add(bala);
			break;
		}
		
		
		
	}else if (obj instanceof TCPconnectionP2) {
		System.out.println("JUGADOR 2:" + c.getAccion());
		
		switch(c.getAccion()) {
		
		//Down
		case "DOWNSTART":
			player2.activateDownmove();
			break;
		case "DOWNSTOP":
			player2.desactivateDownmove();
			break;
	    //Up
		case "UPSTART":
			player2.activateUpmov();
			break;
		case "UPSTOP":
			player2.desactivateUpmove();
			break;
		//Left	
		case "LEFTSTART":
			player2.activateLeftmove();
			break;
		case "LEFTSTOP":
			player2.desactivateLeftmove();
			break;
		//Right	
		case "RIGHTSTART":
			player2.activateRightmove();
			break;
		case "RIGHTSTOP":
			player2.desactivateRightmove();
			break;
			
		//Disparo
		case "FIRE":
			Bala bala = new Bala(this, player2.getX(), player2.getY(), false);
			balasP2.add(bala);
			break;
		}

	}
	
//	if(obj.equals(conexionP1)) { sirve too
//		
//	}else if(obj.equals(conexionP2)) {
//		
//	}
	
	//obj será un objeto de TCPconnectionP2?	
	System.out.println(c.getAccion());
	}

	
}
