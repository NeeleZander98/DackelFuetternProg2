package dackelfuettern.view;


import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dackelfuettern.Game;


public class DackelShower extends JPanel {

    int[] dackelLeft = new int[4];

    Game game;

    JLabel lFive  = new JLabel();
    JLabel lFour  = new JLabel();
    JLabel lThree = new JLabel();
    JLabel lTwo   = new JLabel();


    public DackelShower(Game game, int id) {
        System.out.println("DackelShower " + id + " erstellt");
        this.game = game;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setMinimumSize( new Dimension(game.getWidth(), game.getHeight()));

        add(this.lTwo);
        add(this.lThree);
        add(this.lFour);
        add(this.lFive);
    }

    public void setDackelLeft(int[] dackelLeft) {
        this.dackelLeft = dackelLeft;
        this.lTwo.setText("2er Dackel: " + dackelLeft[0]);
        this.lThree.setText("3er Dackel: " + dackelLeft[1]);
        this.lFour.setText("4er Dackel: " + dackelLeft[2]);
        this.lFive.setText("5er Dackel: " + dackelLeft[3]);
    }

    public void removeFoundDackel(int dackelSize) {
    	System.out.println("Dackel mit der Gröeße " + dackelSize + " wurde gefüttert");
    	if (dackelSize == 2) {
    		this.dackelLeft[0]--;
    	}
    	else if (dackelSize == 3) {
    		this.dackelLeft[1]--;
    	}
    	else if (dackelSize == 4) {
    		this.dackelLeft[2]--;
    	}
    	else if (dackelSize == 5) {
    		this.dackelLeft[3]--;
    	}
        setDackelLeft(this.dackelLeft);
    }

    public int getPlacedDackel() {
        int dackelleft = 0;

        for (int dackelCount: dackelLeft) {
            dackelleft += dackelCount;
        }
        return dackelleft;
    }
}
