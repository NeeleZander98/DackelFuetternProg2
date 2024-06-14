package dackelfuettern;
import javax.swing.*;

import dackelfuettern.logic.LogicGame;
import dackelfuettern.player.Dackel;
import dackelfuettern.view.Board;
import dackelfuettern.view.BoardGame;
import dackelfuettern.view.BoardSize;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    // TODO: Benamungen + generelle Ordnerstruktur noch einmal überdenken

    static ArrayList<Dackel> DackelList = new ArrayList<>();
    public static void main(String[] args) {

        JLabel spieler1 = new JLabel("Spieler1");
        JLabel spieler2 = new JLabel("Spieler2");
        BoardSize boardSize = new BoardSize(600,600);

        Board Spielfeld = new Board("Dackel fuettern", spieler1, spieler2, boardSize);

        LogicGame Logic = new LogicGame();
        BoardGame DackelFuettern= new BoardGame(Spielfeld.getSpielfeld1(), Spielfeld.getSpielfeld2());
        Spieler Spieler1 = new Spieler();
        Spieler Spieler2 = new Spieler();

        Dackel B1S1 = new Dackel(1);
        Dackel B2S1 = new Dackel(2);
        Dackel B3S1 = new Dackel(3);
        Dackel B4S1 = new Dackel(4);
        Dackel B5S1 = new Dackel(5);

        DackelList.add(B1S1);
        DackelList.add(B2S1);
        DackelList.add(B3S1);
        DackelList.add(B4S1);
        DackelList.add(B5S1);

        Dackel B1S2 = new Dackel(1);
        Dackel B2S2 = new Dackel(2);
        Dackel B3S2 = new Dackel(3);
        Dackel B4S2 = new Dackel(4);
        Dackel B5S2 = new Dackel(5);


        Spielfeld.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {

                DackelFuettern.run();
            }
        });
        Spielfeld.run();


        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.



    }



    public static ArrayList<Dackel> getDackelList(){return DackelList;}
}



