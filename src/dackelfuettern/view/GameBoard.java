package dackelfuettern.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameBoard extends JFrame {
    int counter = 0;
    int counter2 = 0;
    int counterPlayer1 = 0;
    int counterPlayer2 = 0;

    int currentPlayer = 1;

    int maxBoats = 15;
static JButton[][] feldSpieler1;
JButton[][] feldSpieler2;




       public GameBoard(  ){




        }

        public void run(){
            setTitle("Schiffe Versenken");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(600, 600);
            setLayout(new GridLayout(3,2));


            JLabel spieler1 = new JLabel("spieler1");

            JLabel spieler2 = new JLabel("spieler2");
            spieler1.setHorizontalAlignment(JLabel.CENTER);
            spieler2.setHorizontalAlignment(JLabel.CENTER);


            JPanel Spielfeld1 = new JPanel();
            Spielfeld1.setLayout(new GridLayout(10, 10));
            feldSpieler1 = new JButton[10][10];
            for(int i = 0; i < 10; i++){
                for(int i2 = 0; i2 < 10; i2++){
                    JButton button = new JButton();
                    button.setPreferredSize(new Dimension(10, 10 ));
                    button.addActionListener(new Clicker());
                    feldSpieler1[i][i2] = button;
                    Spielfeld1.add(button);
                }
            }

            JPanel Spielfeld2 = new JPanel();

            feldSpieler2 = new JButton[10][10];
            Spielfeld2.setLayout(new GridLayout(10, 10));
            for(int i = 0; i < 10; i++){
                for(int i2 = 0; i2 < 10; i2++){
                    JButton button2 = new JButton();

                    button2.setPreferredSize(new Dimension(10,10));
                    feldSpieler2[i][i2] = button2;
                    button2.addActionListener(new Clicker());
                    Spielfeld2.add(button2);
                }

            }
            JButton checkButton = new JButton("Gedrückte Knöpfe");
            checkButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    checkPressedButton();
                }
            });
            add(checkButton);
            add(spieler1);
            add(Spielfeld1);
            add(spieler2);
            add(Spielfeld2);

            setVisible(true);

        }


    public class Clicker implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {



            JButton Feld = (JButton) e.getSource();
            int row = -1, col = -1;
            JButton[][] currentField;
            if (currentPlayer == 1) {
                currentField = feldSpieler1;
            } else {
                currentField = feldSpieler2;
            }

            for (int i = 0; i < 10; i++){
                for(int j = 0; j < 10; j++) {
                   if(currentField[i][j] == Feld){
                       row = i;
                       col = j;
                       break;
                    }

                }

            }

        if(currentPlayer == 1){
            if(counter < 5){Feld.setBackground(Color.MAGENTA);
            if(counter == 1){          }}


            else if(counter < 9){Feld.setBackground(Color.CYAN);}
            else if(counter < 12){Feld.setBackground(Color.GREEN);}
            else if(counter < 14){Feld.setBackground(Color.yellow);}
            counter = counter+1;
            if(counter == 15){ currentPlayer = 2;
                JOptionPane.showMessageDialog(null, "Spieler 1 hat alle Boote ausgewählt. Spieler 2 ist jetzt an der Reihe.");
                }
            Feld.setEnabled(false);}
        if(currentPlayer == 2){
            if(counter2 < 5){Feld.setBackground(Color.MAGENTA);}
            else if(counter2 < 9){Feld.setBackground(Color.CYAN);}
            else if(counter2 < 12){Feld.setBackground(Color.GREEN);}
            else if(counter2 < 14){Feld.setBackground(Color.yellow);}
            counter2 = counter2+1;

        }
        }
    }

    public void checkPressedButton(){
           StringBuilder gedrueckteKnoepfe = new StringBuilder("Gedrückte Buttons:\n");

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
            if(feldSpieler1[i][j].getBackground() == Color.MAGENTA){
              gedrueckteKnoepfe.append("Spieler1 Button: ").append(i).append(", ").append(j).append("\n");
            }
            }


    }JOptionPane.showMessageDialog(this, gedrueckteKnoepfe.toString());}






}
