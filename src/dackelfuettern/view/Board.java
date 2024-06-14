package dackelfuettern.view;

import javax.swing.*;

import dackelfuettern.logic.Clicker;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Board extends JFrame {
    // TODO: eigene Config Klasse erstellen
    ImageIcon icon2 = new ImageIcon("src/dackelfuettern/assets/Hundi.jpg");

    static JButton[][] spielfeld1 = new JButton[10][10];
    static JButton[][] spielfeld2 = new JButton[10][10];
    Clicker clickerPlayerOne = new Clicker(spielfeld1);
    Clicker clickerPlayerTwo = new Clicker(spielfeld2);

    public static int counter = 0;

    private static String richtung;

    private JDialog dialog;
    private String title;
    // Idee: es gäbe auch die Möglichkeit die Spieler in eine Liste zu befüllen, sodass dies nicht "hart" im Code festgelegt wird
    private JLabel playerOne;
    private JLabel playerTwo;

    public Board(String title, JLabel playerOne, JLabel playerTwo, BoardSize boardSize) {
        this.title = title;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.playerOne.setHorizontalAlignment(JLabel.CENTER);
        this.playerTwo.setHorizontalAlignment(JLabel.CENTER);
        this.configureBoard(boardSize);
    }

    public static String getRichtung() {
        return Board.richtung;
    }

    public static void setRichtung(String changedRichtung) {
        richtung = changedRichtung;
    }

    public JButton[][] getSpielfeld1() {
        return spielfeld1;
    }

    public JButton[][] getSpielfeld2() {
        return spielfeld2;
    }
    public static int getCounter() {
        return counter;
    }

    public static void countUpCounter() {
        counter++;
    }

    public boolean checkFinish(){
        if(counter == 10){System.out.println("Alle Dackel platziert"); return true;}
        return false;
    }

    private void configureBoard(BoardSize boardSize) {
        setTitle(this.title);
        setSize(boardSize.getWidth(), boardSize.getHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    // TODO: Bennennung ändern -> eventuell "startSpielFeld()"?
    public void run(){
        Dimension dimension = new Dimension(10, 10);
        GridLayout gridLayout = new GridLayout(10, 10);
        SpielFeld spielfeld1 = new SpielFeld(this.spielfeld1, dimension, clickerPlayerOne, gridLayout);
        SpielFeld spielfeld2 = new SpielFeld(this.spielfeld2, dimension, clickerPlayerTwo, gridLayout);

        JPanel Spielfeld1 = spielfeld1.createSpielfeld();
        JPanel Spielfeld2 = spielfeld2.createSpielfeld();

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("../src/assets/Hundi.jpg");
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new GridLayout(4,1));
       // TODO: @Dennis welcher Player kam nochmal im Original Code da rein (also welche Variable)?
        panel.add(this.playerOne);
        panel.add(Spielfeld1);
        panel.add(this.playerTwo);
        panel.add(Spielfeld2);
        setContentPane(panel);
        setVisible(true);

        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFinish()) {
                    ((Timer) e.getSource()).stop();
                    dispose();
                }
            }
        });
        timer.start();
    }


     public static void createAndShowDialog() {
        // TODO: theoretisch könnten wir die Methode nun komplett ablösen und die neue Klasse Dialog stattdessen immer verwenden ->
         // wenn wir die Methode komplett ablösen, müssen wir noch schauen, wie es mit der Übergabe der "Board"-Klasse als Komponente am Besten funktioniert, damit der Dialog dem Board zugeordnet wird
         // mache mir hier nochmal Gedanken von der STrukut
       Dialog dialog = new Dialog("SCHIFFE VERSENKEN", true, 400, 300, null);
       dialog.createLabelAndButton("Möchtest du dein Schiff Vertikal oder Horizontal platzieren?", "Vertikal", "Horizontal");
    }
}


