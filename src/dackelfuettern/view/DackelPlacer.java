package dackelfuettern.view;

import java.awt.*;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;

import dackelfuettern.Game;
import dackelfuettern.actions.DackelPlacerActions;

public class DackelPlacer extends JPanel {

    private final JRadioButton  buttonFive  = new JRadioButton("0x großer Dackel (5er)");
    private final JRadioButton  buttonFour  = new JRadioButton("0x mittelgroßer Dackel (4er)");
    private final JRadioButton  buttonThree = new JRadioButton("0x mittlerer Dackel (3er)");
    private final JRadioButton  buttonTwo   = new JRadioButton("0x kleiner Dackel (2er)");
    private final JToggleButton toggleView = new JToggleButton("Horizontal");
    private final JButton       buttonNextPlayer  = new JButton("Weiter");

    private int countFive  = 1;
    private int countFour  = 1;
    private int countThree = 1;
    private int countTwo = 1;

    private boolean horizontal = true;

    Game game;

    ButtonGroup buttonGroup = new ButtonGroup();

    public DackelPlacer(int id, Game game) {
        System.out.println("Spieler mit der ID " + id + " setzt Dackel");
        
        this.game = game;
        
        setMinimumSize(new Dimension(game.getWidth(), game.getHeight()));

        DackelPlacerActions listener = new DackelPlacerActions(this, game, id);

        this.configureButtons(listener);

    }

    public void setDackelCount() {
        //Die Button der Dackel die platziert werden können
        this.buttonFive.setText(this.countFive + this.buttonFive.getText().substring(1));
        this.buttonFour.setText(this.countFour + this.buttonFour.getText().substring(1));
        this.buttonThree.setText(this.countThree + this.buttonThree.getText().substring(1));
        this.buttonTwo.setText(this.countTwo + this.buttonTwo.getText().substring(1));
    }
    
   private void configureButtons(DackelPlacerActions listener) {
	   
	   // Standard zum Start des Spiels wird der Button mit 2-Elementen auf true gesetzt, damit der ausgewählt ist (Über / mit ChatGPT)
	   this.buttonTwo.setSelected(true);

	   this.buttonTwo.setFocusable(false);
	   this.buttonThree.setFocusable(false);
	   this.buttonFour.setFocusable(false);
	   this.buttonFive.setFocusable(false);

	   // toggleView ist der Wechsel zwischen Horizontal und Vertikaler sicht
	   this.toggleView.addActionListener(listener);
	   this.toggleView.setActionCommand("toggle");
	   this.toggleView.setFocusable(false);

	   this.buttonNextPlayer.setMinimumSize(new Dimension(100, 100));
	   this.buttonNextPlayer.setActionCommand("next");
	   this.buttonNextPlayer.addActionListener(listener);
	   this.buttonNextPlayer.setFocusable(false);
	   this.buttonNextPlayer.setEnabled(false);

       setDackelCount();

       this.buttonGroup.add(buttonFive);
       this.buttonGroup.add(buttonFour);
       this.buttonGroup.add(buttonThree);
       this.buttonGroup.add(buttonTwo);

       add(buttonFive);
       add(buttonFour);
       add(buttonThree);
       add(buttonTwo);
       add(toggleView);
       add(buttonNextPlayer);
   }

    public int getSelectedDackelSize() {
        //Holt sich die größe der jeweiligen Dackel und setzt dackelSize dem entsprechenden Wert zu
        int dackelSize = 0;

        if (this.buttonFive.isSelected()) {
            dackelSize = 5;
        }
        if (this.buttonFour.isSelected()) {
        	dackelSize = 4;
        }          
        if (this.buttonThree.isSelected()) {
        	dackelSize = 3;
        }
        if (this.buttonTwo.isSelected()) {
        	dackelSize = 2;
        }
           
        return dackelSize;
    }

    public void removeDackelCount(int dackelID) {
        //Ein SWITCH case der die Buttons deaktiviert nachdem sie gedrückt wurden
        switch (dackelID) {
            case 5:
            	this.countFive--;
                if (this.countFive == 0) {
                	this.buttonFive.setEnabled(false);
                }
            break;
            case 4:
            	this.countFour--;
                if (this.countFour == 0) {
                	this.buttonFour.setEnabled(false);
                }
            break;
            case 3:
            	this.countThree--;
                if (this.countThree == 0) {
                	this.buttonThree.setEnabled(false);
                }
            break;
            case 2:
            	this.countTwo--;
                if (this.countTwo == 0) {
                	this.buttonTwo.setEnabled(false);
                }
            break;
        }

        setDackelCount();
    }

    public boolean isAvailable() {
        // Überprüft, ob der ausgewählte Dackel in der aktuellen Größe noch verfügbar ist
    	if (getSelectedDackelSize() == 5) {
    		return countFive != 0;
    	}
    	else if (getSelectedDackelSize() == 4) {
    		return countFour != 0;
    	}
    	else if (getSelectedDackelSize() == 3) {
    		return countThree != 0;
    	}
    	else if (getSelectedDackelSize() == 2) {
    		return countTwo != 0;
    	}
    	else {
    		return false;
    	}
    }

    public void changeToggleView() {
        //Ändert den Button und die jeweilige Ausrichtung in der Buttons platziert werden können
        if (this.toggleView.getText().equals("Horizontal")) {
        	this.toggleView.setText("Vertikal");
        }
        else {
        	this.toggleView.setText("Horizontal");
        }
        setHorizontal(!isHorizontal());
    }

    public JToggleButton getToggleView() {
		return this.toggleView;
	}

	public boolean isHorizontal() {
        return this.horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public void checkDackelSizeCount(int dackelSize) {

       switch(dackelSize) {
           case 2: 
        	   if(this.countTwo == 0) {
        		   this.buttonThree.setSelected(true);
        	   }
           break;
           case 3: 
        	   if(this.countThree == 0) {
        		   this.buttonFour.setSelected(true); 
        	   }
        	   break;
           case 4: 
        	   if(this.countFour == 0) {
        		   this.buttonFive.setSelected(true); 
        	   }
        	   break;
       }

    }
    
    public void setNextClickable() {
        if((1 - this.countFive + 1 - this.countFour + 1 - this.countThree + 1 - this.countTwo) != 0) {
        	this.buttonNextPlayer.setEnabled(true);
        } 
    }
    
    public int[] getPlacedDackel() {
        int[] dackel = new int[4];

        dackel[0] = 1 - this.countTwo;
        dackel[1] = 1 - this.countThree;
        dackel[2] = 1 - this.countFour;
        dackel[3] = 1 - this.countFive;

        return dackel;
    }
}