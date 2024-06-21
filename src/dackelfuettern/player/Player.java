package dackelfuettern.player;

import java.awt.*;

import javax.swing.JPanel;

import dackelfuettern.Game;
import dackelfuettern.view.DackelPlacer;
import dackelfuettern.view.DackelShower;
import dackelfuettern.view.Playground;


public class Player extends JPanel {
	
	private final DackelPlacer dackelPlacer;
    private final Playground   playground;
    private final DackelShower dackelShower;
    private final GridBagConstraints cons = new GridBagConstraints();

    public Player(Game game, int id) {
        //Spieler erstellen und mit nötigen Funktionen ausstaten
    	 System.out.println("Spieler mit der ID: " + id + " erstellt ");
         setLayout(new GridBagLayout());
         setMinimumSize(new Dimension(game.getWidth(), game.getHeight()));

         this.playground = new Playground(this, game, id);
         this.dackelPlacer = new DackelPlacer(id, game);
         this.dackelShower = new DackelShower(game, id);

         cons.gridx = 0;
         cons.gridy = 0;
         cons.fill = GridBagConstraints.BOTH;

         cons.weightx = 1;
         cons.weighty = 1;

         add(this.playground);

         cons.gridy++;
         add(this.dackelPlacer, cons);
    }

    public Playground getPlayground() {
        return playground;
    }

    public DackelPlacer getDackelPlacer() {
        return dackelPlacer;
    }


    public void prepareGame() {

        remove(dackelPlacer);
        add(dackelPlacer, cons);
        playground.removeBlocked();
    }
    
    public DackelShower getDackelShower() {
        return dackelShower;
    }
}