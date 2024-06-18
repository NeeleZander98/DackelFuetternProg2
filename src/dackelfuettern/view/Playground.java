package dackelfuettern.view;

import javax.swing.*;

import dackelfuettern.Game;
import dackelfuettern.actions.ButtonActions;
import dackelfuettern.player.Player;

import java.awt.*;
import java.util.HashMap;

public class Playground extends JPanel {
//In dieser Klasse wurde sich viel von ChatGPT abgeschaut und benutzt
    private final JButton[][] buttons;
    private final Game game;
    private final Player player;
    private int[][] dackel;
    private HashMap<Integer, Integer> dackelIDSize = new HashMap<>();

        //Initilaisierung Spielfeld
    public Playground(Player player, Game game, int id) {
        System.out.println("Playground " + id + " erstellt");

        this.dackel = new int[10][10];
        this.buttons   = new JButton[dackel.length+1][dackel[0].length+1];

        setLayout(new GridLayout(buttons.length, buttons[0].length));
        setMinimumSize(new Dimension(400, 400));
        setVisible(true);

        this.game = game;
        this.player = player;

        ButtonActions listener = new ButtonActions(game, player);
        //Erstellung der Buttons
        for (int y = 0; y < buttons.length; y++)
            for (int x = 0; x< buttons[y].length; x++) {
                //Horizontal sind die X-Werte und Vertikal die Y-Werte
                buttons[y][x] = new JButton();
                buttons[y][x].setActionCommand(y + "," + x);
                buttons[y][x].addActionListener(listener);
                buttons[y][x].setFocusable(false);
            }
        //Setzen der Beschriftung der Zahlen
        for (int i = 1; i < buttons[0].length; i++) {
            buttons[0][i].setText(String.valueOf(i));
            buttons[0][i].setEnabled(false);
        }
        //Setzen der Beschriftung der Buchstaben
        int charStart = 65;
        for (int i = 1; i < buttons.length; i++) {
            buttons[i][0].setText(String.valueOf((char) (charStart + i - 1)));
            buttons[i][0].setEnabled(false);
        }

        buttons[0][0].setEnabled(false);

        for (JButton[] buttons : buttons)
            for (JButton button : buttons)
                add(button);
    }

    public void placeDackel( int y, int x, int dackelSize, int dackelID ) {
        //Funtkion um Dackel auf dem Spielfeld zu platzieren
        if (this.player.getDackelPlacer().isHorizontal()) {
            for (int i = 0; i < dackelSize; i++) {
                System.out.println("y:" + y + " x:" + x + " - Dackel gesetzt");
                dackel[y][x] = dackelID;
                buttons[y + 1][x + 1].setEnabled(false);
                dackelIDSize.put(dackelID, dackelSize);
                x++;
            }
        } else {
            for (int i = dackelSize; i > 0; i--) {
                System.out.println("y:" + y + " x:" + x + " - Dackel gesetzt" );
                dackel[y][x] = dackelID;
                buttons[y + 1][x + 1].setEnabled(false);
                dackelIDSize.put(dackelID, dackelSize); 
                y--;
            }
        }
        this.player.getDackelPlacer().removeDackelCount(dackelSize);
        this.markAllOwnDackel();
    }

    public void setButtonIcon(int y, int x) {
        buttons[y][x].setIcon(new ImageIcon("src/dackelfuettern/assets/smallhundi.png"));
        this.game.pack();
    }
    //Zum markieren eigener Dackel auf dem Spielfeld
    public void markAllOwnDackel() {
        //Geklickte Dackel sollen angezeigt werden
        for (int x = 1; x < this.buttons.length; x++)
            for (int y = 1; y < this.buttons[x].length; y++)
                if (this.dackel[y - 1][x - 1] != 0)
                    setButtonIcon(y, x);
    }


    public boolean isPlaceable(int y, int x, int dackelSize) {

        boolean placeable;

        if (this.player.getDackelPlacer().isHorizontal()) {
        	placeable = x + dackelSize < dackel.length + 1;
        }
        else {
        	placeable = y + 1 - dackelSize >= 0;
        }
        if (!placeable)
            return false;
        
		return placeable;
    }

}
