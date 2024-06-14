package dackelfuettern.logic;

import javax.swing.*;

import dackelfuettern.player.Dackel;
import dackelfuettern.view.Board;
import dackelfuettern.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.Set;


public class Clicker implements ActionListener {
int counter = 0;
ImageIcon icon = new ImageIcon("C:\\Users\\P027161\\Pictures\\Hundi2.jpg");
ArrayList<Dackel> DackelList = Main.getDackelList();
static Set<Point> clickedFields = new HashSet<>();//IDEE VON CHATGPT!

ArrayList<Integer> xWerte = new ArrayList<Integer>();
ArrayList<Integer> yWerte = new ArrayList<Integer>();
JButton[][] ButtonsSpielfeld;


    public Clicker(JButton[][] spielfeld) {
        ButtonsSpielfeld = spielfeld;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton Feld = (JButton) e.getSource();
        Point fieldPosition = new Point((int) Feld.getClientProperty("col"), (int) Feld.getClientProperty("row"));
        System.out.println("ZEILE: " + Feld.getClientProperty("row") + " SPALTE " + Feld.getClientProperty("col") + " länge: " + DackelList.get(counter).getLaenge());
        xWerte.add((int)Feld.getClientProperty("row"));
        yWerte.add((int)Feld.getClientProperty("col"));
        System.out.println("geklickte Zeilen: " + xWerte);
        System.out.println("geklickte Spalten: " + yWerte);
        if(changeButtonColor(Feld, Board.getRichtung(), DackelList.get(counter).getLaenge())){counter ++;}
    }

    public boolean changeButtonColor(JButton Feld, String direction, int laenge){
        if (direction == "horizontal" && checkLegalMove(laenge, Feld, direction)){
            for(int i = 0; i < laenge; i++){
                clickedFields.add(new Point((int) Feld.getClientProperty("col"), (int) Feld.getClientProperty("row") + i));
            ButtonsSpielfeld[(int) Feld.getClientProperty("col")][(int)Feld.getClientProperty("row") +i].setIcon(icon);
              }return true;}
        if (direction == "vertikal" && checkLegalMove(laenge, Feld, direction)){
            for(int i = 0; i < laenge; i++){
                clickedFields.add(new Point((int) Feld.getClientProperty("col") + i, (int) Feld.getClientProperty("row") ));
                ButtonsSpielfeld[(int) Feld.getClientProperty("col") +i][(int)Feld.getClientProperty("row")].setBackground(Color.MAGENTA);
            }return true;}

        return false;

    }

    public boolean checkLegalMove2(int laenge,int x, int y, String direction, JButton Feld){
        if((int)Feld.getClientProperty("col") + laenge > 10 && direction.equals("vertikal") || (int)Feld.getClientProperty("row") + laenge > 10 && direction.equals("horizontal"))
        {return false;}
        int tempX;
        int tempY;
        for(int i = 0; i <= laenge; i++){
           if(DackelList.get(counter).getAusrichtung() == 1){
               
           }


        }


        return true;
    }


    public boolean checkLegalMove(int laenge, JButton Feld, String direction){
        if((int)Feld.getClientProperty("col") + laenge > 10 && direction.equals("vertikal") || (int)Feld.getClientProperty("row") + laenge > 10 && direction.equals("horizontal"))
        {return false;}
      for (int i = 0; i < laenge; i++) {
            Point point;
            if (direction.equals("horizontal")) {
                point = new Point((int) Feld.getClientProperty("col"), (int) Feld.getClientProperty("row") + i);
            } else {
                point = new Point((int) Feld.getClientProperty("col") + i, (int) Feld.getClientProperty("row"));
            }
            if (clickedFields.contains(point)){
                System.out.print("Du hast hier schon einen Hund gesetzt"); return false;
            }}
        return true;}


    public boolean checkMove(String direction, JButton Feld) {
       if(DackelList.get(counter).isTreffer((Integer) Feld.getClientProperty("col"),(Integer) Feld.getClientProperty("row"))){return true;}

       return false;

    }



    public boolean changeButtonColor2(JButton Feld, String direction, int laenge){
        if (direction == "horizontal" && checkMove(direction, Feld)){
            for(int i = 0; i < laenge; i++){
                clickedFields.add(new Point((int) Feld.getClientProperty("col"), (int) Feld.getClientProperty("row") + i));
                ButtonsSpielfeld[(int) Feld.getClientProperty("col")][(int)Feld.getClientProperty("row") +i].setIcon(icon);
            }return true;}
        if (direction == "vertikal" && checkMove(direction, Feld)){
            for(int i = 0; i < laenge; i++){
                clickedFields.add(new Point((int) Feld.getClientProperty("col") + i, (int) Feld.getClientProperty("row") ));
                ButtonsSpielfeld[(int) Feld.getClientProperty("col") +i][(int)Feld.getClientProperty("row")].setBackground(Color.MAGENTA);
            }return true;}

        return false;

    }

}




