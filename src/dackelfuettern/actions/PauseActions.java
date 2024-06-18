package dackelfuettern.actions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dackelfuettern.Game;

public class PauseActions implements ActionListener {

    Game game;

    public PauseActions(Game game) {
        this.game = game;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("resume")) {
        	System.out.println("Action 'resume' wurde erkannt - fahre mit dem Spiel fort");
            this.game.resumeGame();
        }
    }
}
