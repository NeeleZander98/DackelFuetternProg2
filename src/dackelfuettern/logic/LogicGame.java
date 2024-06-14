package dackelfuettern.logic;

public class LogicGame {

    public LogicGame() {
    }

//Benamung überdenken -> gann das nicht in eine andere klasse
    public static boolean checkBooteGesetzt(int counter){
    if (counter == 10){
        System.out.println("SPIEL LÄUFT");
        return true;
    }
        System.out.print("SPIEL SOLLTE VORBEI SEIN");
        return false;
    }
}
