package dackelfuettern.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import dackelfuettern.Game;
import dackelfuettern.player.Player;
import dackelfuettern.view.Playground;


public class ButtonActions implements ActionListener {

    private final Game game;
    private final Player player;
    private Playground playground;
    private final int id;
    private int dackel = 0;


    public ButtonActions(Game game, Player player, int id) {
        this.game = game;
        this.player = player;
        this.id = id; 
    }

  
    public void actionPerformed( ActionEvent e ) {
        this.playground = this.player.getPlayground();
        
        // Video über Tokenizer angeguckt: https://www.youtube.com/watch?v=diCjee_KOyY&ab_channel=KBTutorials -> Immer Problem gehabt, wie die gedrückten Koordinaten rauszufinden waren
         //Tokenizer wird genutzt, um gedrückte Koordinatien herauszufinden
        StringTokenizer tokenizer = new StringTokenizer( e.getActionCommand(), "," );
      
        int yCord = Integer.parseInt( tokenizer.nextToken() ) - 1;       
        int xCord = Integer.parseInt( tokenizer.nextToken() ) - 1;
        // Überprüfen, ob der Dackelplatzierer verfügbar ist und die Dackel platziert werden können
        if (this.game.getStatus().equals("setDackel")) {
            if (this.player.getDackelPlacer().isAvailable() && this.playground.isPlaceable(yCord, xCord, this.player.getDackelPlacer().getSelectedDackelSize())) {
                this.dackel++;
                this.playground.placeDackel(yCord, xCord, this.player.getDackelPlacer().getSelectedDackelSize(), dackel);
                this.player.getDackelPlacer().checkDackelSizeCount(this.player.getDackelPlacer().getSelectedDackelSize());
                this.player.getDackelPlacer().setNextClickable();
            }
        
	    } else if (this.game.getStatus().equals("fuettern")) {
	        System.out.println( "Spieler hat bei - y:" + yCord + " x:" + xCord + " versucht einen Dackel zu füttern");
	
	        if (this.playground.getDackel()[yCord][xCord] != 0) {
	            this.feedDackel(yCord, xCord);
	
	        } else { 
	            this.playground.setButtonIcon(++yCord, ++xCord, "lecki");
	            this.playground.changeButton(yCord, xCord, false);
	            this.game.pauseGame(id);
	        }
    }
    }
    
    private void feedDackel(int y, int x) {
        this.dackel = playground.getCord(y, x);

        this.playground.getDackel()[y][x] = -dackel;
        this.playground.changeButton(y + 1, x + 1, false);
        this.playground.setButtonIcon(y + 1, x + 1, "foundDackel");
    }

}
