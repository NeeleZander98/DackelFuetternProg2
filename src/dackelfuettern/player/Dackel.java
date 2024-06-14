package dackelfuettern.player;

public class Dackel {

    public static final int HORIZONTAL = 1;
    public static final int VERTIKAL = 2;
    private int laenge;
    int counter = 0;
    int eingabe = 1;

    int startX;
    int startY;
    private int ausrichtung;

    public Dackel(int laenge) {
        this.setLaenge(laenge);
    }

    public boolean getroffen(int eingabe) {
        if(eingabe == getLaenge()) {return true;}
        return false;
    }

    public void counter() {
        if(getroffen(this.eingabe)) {
            counter = counter+1;}
    }

    public boolean isFinished(){
        counter();
        if(counter == getLaenge()) {System.out.println("DER DACKEL IST SATT"); return true;}
        return false;
    }

    public boolean isTreffer(int x, int y){
        int tempX = startX;
        int tempY = startY;
        for(int i = 0; i <= getLaenge(); i++){
            if(getAusrichtung() == HORIZONTAL){
                tempX = startX + i;

            }else{tempY = startY + i;}
            if(x == tempX && y == tempY){return true;}

        }
        return false;
    }

	public int getLaenge() {
		return laenge;
	}

	public void setLaenge(int laenge) {
		this.laenge = laenge;
	}

	public int getAusrichtung() {
		return ausrichtung;
	}

	public void setAusrichtung(int ausrichtung) {
		this.ausrichtung = ausrichtung;
	}
}