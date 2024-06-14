package dackelfuettern.view;

import javax.swing.*;

import dackelfuettern.logic.Clicker;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpielFeld {

    private static JButton[][] spielfeld;
    private Clicker clicker;
    private Dimension dim;
    private GridLayout gridLayout;


    public SpielFeld(JButton[][] spielfeld, Dimension dimension, Clicker clicker, GridLayout gridLayout) {
        this.spielfeld = spielfeld;
        this.dim = dimension;
        this.clicker = clicker;
        this.gridLayout = gridLayout;
    }

    public JPanel createSpielfeld() {
        JPanel spielfeld = new JPanel();
        spielfeld.setLayout(new GridLayout(gridLayout.getRows(), gridLayout.getColumns()));

        for (int i = 0; i < gridLayout.getRows(); i++) {
            for (int j = 0; j < gridLayout.getColumns(); j++) {
                JButton button = createJButton(i, j);
                spielfeld.add(button);
                this.spielfeld[i][j] = button;
            }
        }
        return spielfeld;
    }

    private JButton createJButton(int col, int row) {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(this.dim.width,this.dim.height));
        button.putClientProperty("col", col);
        button.putClientProperty("row", row);
        button.addActionListener(this.clicker);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(e.getSource() == Board.class) {
            		 Board.createAndShowDialog();
            		 Board.countUpCounter();
            	}
            }
        });
        return button;
    }
}
