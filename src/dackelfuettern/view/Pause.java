package dackelfuettern.view;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dackelfuettern.Game;
import dackelfuettern.actions.PauseActions;

public class Pause extends JPanel {
//Erstellung eines Pausenbildschirms zwischen den beiden Spielern
	public Pause(Game game) {
		System.out.println("Erstelle Pause Anzeige für Spielerwechsel");
		
		setMinimumSize(new Dimension(game.getWidth(), game.getHeight()));
		setVisible(true);
		setLayout(new GridBagLayout());

		JLabel view = new JLabel();
		 
		Image img = new ImageIcon("src/dackelfuettern/assets/Hundi_big_new.png").getImage();
		view = new JLabel(new ImageIcon(img));
		
		JButton resume = new JButton("Fortfahren");
		JLabel title = new JLabel("Pause - nächster Spieler ist an der Reihe.");
		
		resume.addActionListener(new PauseActions(game));
        resume.setActionCommand("resume");
        
        add(view);
        add(title);
        add(resume);
	}

}
