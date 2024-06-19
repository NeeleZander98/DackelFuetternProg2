package dackelfuettern.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dackelfuettern.Game;
import dackelfuettern.view.DackelPlacer;


public class DackelPlacerActions implements ActionListener {

    DackelPlacer  dackelPlacer;
    Game game;
    int  id;

    public DackelPlacerActions(DackelPlacer dackelPlacer, Game game, int id) {
        this.dackelPlacer = dackelPlacer;
        this.game = game;
        this.id = id;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	//Überprüfen, ob der Aktionstyp "Next" oder "toggle" ist
        if(e.getActionCommand().equals("toggle")) {
        	this.dackelPlacer.changeToggleView();
            System.out.println("Spieler wechselt die Ansicht zu: " + this.dackelPlacer.getToggleView().getText());
        }
        else if(e.getActionCommand().equals("next")) {
        	System.out.println("Alle Dackel vom Spieler gesetzt - wechsle zum nächsten Spieler.");
            this.game.pauseGame(this.id);
            this.game.getCurrentPlayer(this.id).prepareGame();
            this.game.setClicks(this.game.getClicks() + 1);
        }
        
        if (this.game.getClicks() == 2) {
        	System.out.println("Beide Spieler haben alle Dackel gesetzt - starte das Spiel.");
        	this.game.startGame();
        }

    }
}
