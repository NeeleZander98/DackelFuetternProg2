package dackelfuettern;

import java.awt.*;
import java.util.HashMap;

import javax.swing.*;

import dackelfuettern.player.Player;
import dackelfuettern.view.Pause;

public class Game extends JFrame {
	
	private int pausedPlayer = 0;
	private Player player1;
	private Player player2;
	private Pause pause;
	private int clicks = 0;
	private String status = "setDackel";

	public Game() {
		setLayout(new CardLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Dackel füttern");
		
		setFocusable(true);
		
		
		// Erstellen der Spieler und des Pause Screens bei der Initalisierung des Spiels
		this.player1 = new Player(this, 1);
        this.player2 = new Player(this, 2);
        this.pause = new Pause(this);
     
	}
	
	   public void initGame() {
	        status = "setDackel";
	        System.out.println("Initalisiere das Spiel .. Spiel-Status ist aktuell: " + getStatus());
	        setMinimumSize(new Dimension(600, 600));
	        setVisible(true);

	        // Hinzufügen von den bereits initalisierten Spielern / Pause-Dialog
	        add(this.player1);
	        add(this.player2);
	        add(this.pause);

	        // Der erste Spieler wird auf true gesetzt, weil er als erstes dran ist zu setzen
	        this.player1.setVisible(true);
	        this.player2.setVisible(false);
	        this.pause.setVisible(false);

	        pack();
	    }
	   
	   public void pauseGame(int pausedPlayer) {

	        this.pausedPlayer = pausedPlayer;
			//Den Pausenbildschirm zwischen den Spielern aktivieren
	        this.player1.setVisible(false);
	        this.player2.setVisible(false);
	        this.pause.setVisible(true);
	    }


	    public void resumeGame() {
		//Das Spielfeld von Spieler 2 wird aktiviert
	        if(this.pausedPlayer == 2) {
	            this.player1.setVisible(true);
	            this.player2.setVisible(false);
	            this.pause.setVisible(false);
	        } 
	        else if (pausedPlayer == 1) { 
	            this.player1.setVisible(false);
	            this.player2.setVisible(true);
	            this.pause.setVisible(false);
	        }
	    }
	    
	    public void startGame() {

	        int[][] tempDackel = player1.getPlayground().getDackel();

	        player1.getPlayground().setDackel( player2.getPlayground().getDackel() );
	        player2.getPlayground().setDackel( tempDackel);

	        HashMap<Integer, Integer> tempIDSize = player1.getPlayground().getDackelSize();

	        player1.getPlayground().setDackelSize( player2.getPlayground().getDackelSize() );
	        player2.getPlayground().setDackelSize( tempIDSize );

	        player1.getPlayground().activateDackel();
	        player2.getPlayground().activateDackel();

	        player1.getDackelShower().setDackelLeft( player2.getDackelPlacer().getPlacedDackel() );
	        player2.getDackelShower().setDackelLeft( player1.getDackelPlacer().getPlacedDackel() );

	        setStatus("fuettern");

	        System.out.println( "Game-Status: " + getStatus());
	    }

	    public String getStatus() {
	        return status;
	    }
	    
	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public Player getCurrentPlayer(int id) {
		//Schauen welcher Spieler gerade am Zug ist
	        if (id == 1) {
	        	return player1;
	        }
	        else {
	        	return player2;
	        }
	    }
	    
	    public int getClicks() {
	        return clicks;
	    }

	    public void setClicks(int clicks) {
	        this.clicks = clicks;
	    }

}
