package dackelfuettern.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class BoardGame extends JFrame {
    private JButton[][] spielfeld1;
    private JButton[][] spielfeld2;

    private JDialog dialog;

    private boolean[][] spielfeld1Selected;
    private boolean[][] spielfeld2Selected;

    private int spieler = 1;

    private HashSet<Point> erlaubtePunkte;

    public BoardGame(JButton[][] spielfeld1, JButton[][] spielfeld2) {
        this.spielfeld1 = spielfeld1;
        this.spielfeld2 = spielfeld2;
        this.erlaubtePunkte = erlaubtePunkte;
        spielfeld1Selected = new boolean[10][10];
        spielfeld2Selected = new boolean[10][10];
    }

    public void run() {
        setTitle("Schiffe versenken");
        setLayout(new BorderLayout());
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        if (spieler == 1) {
            Spielfeld1();
        } else if (spieler == 2) {
            Spielfeld2();
        }
    }

    // Generell wäre es auch denkbar ein Spielfeld als eine Klasse auszulagern -> wenn ihr sowieso zwei Spielfelder befüllt ist es voraussichtlich für Greve dann wieder ein Faktor das das "objektorientiert" sein soll
    private void Spielfeld1() {
        // Erstellen des JDialog
        dialog = new JDialog(this, "SCHIFFE VERSENKEN", true); // true bedeutet, dass der Dialog modal ist
        dialog.setSize(600, 600); // Dialoggröße anpassen
        dialog.setLayout(new BorderLayout());

        // Label und Button im Dialog
        JLabel messageLabel = new JLabel("Wähle das Feld aus, welches du füttern möchtest", JLabel.CENTER);
        dialog.add(messageLabel, BorderLayout.NORTH);

        // Panel für die Buttons
        JPanel spielfeldPanel = new JPanel();
        spielfeldPanel.setLayout(new GridLayout(10, 10));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JButton button3 = new JButton();
                button3.setPreferredSize(new Dimension(50, 50)); // Größe der Buttons anpassen
                button3.putClientProperty("col", i);
                button3.putClientProperty("row", j);

                Point point = new Point(i, j);
                if (!erlaubtePunkte.contains(point)) {
                    button3.setEnabled(false); // Button deaktivieren, wenn Punkt nicht erlaubt
                }

                if (spielfeld1Selected[i][j]) {
                    button3.setBackground(Color.CYAN); // Farbe setzen, falls Feld bereits ausgewählt
                }

                button3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int col = (int) button3.getClientProperty("col");
                        int row = (int) button3.getClientProperty("row");
                        if (button3.getBackground() == Color.CYAN) {
                            button3.setBackground(null);
                            spielfeld1Selected[col][row] = false;
                        } else {
                            button3.setBackground(Color.CYAN);
                            spielfeld1Selected[col][row] = true;
                        }
                    }
                });

                spielfeldPanel.add(button3);
                spielfeld1[i][j] = button3;
            }
        }
        dialog.add(spielfeldPanel, BorderLayout.CENTER);

        // Wechsel-Button und Test-Button
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton button = new JButton("Wechsel zu Spieler 2");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
                spieler = 2;
                Spielfeld2();
            }
        });
        JButton buttonTest = new JButton("Test");
        buttonTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonTest.setBackground(Color.MAGENTA);
            }
        });
        buttonPanel.add(button);
        buttonPanel.add(buttonTest);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(this); // Zentriert den Dialog relativ zum Hauptfenster
        dialog.setVisible(true);
    }

    private void Spielfeld2() {
        // Erstellen des JDialog
        dialog = new JDialog(this, "SCHIFFE VERSENKEN", true); // true bedeutet, dass der Dialog modal ist
        dialog.setSize(600, 600); // Dialoggröße anpassen
        dialog.setLayout(new BorderLayout());

        // Label und Button im Dialog
        JLabel messageLabel = new JLabel("Wähle das Feld aus, welches du füttern möchtest", JLabel.CENTER);
        dialog.add(messageLabel, BorderLayout.NORTH);

        // Panel für die Buttons
        JPanel spielfeldPanel = new JPanel();
        spielfeldPanel.setLayout(new GridLayout(10, 10));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JButton button3 = new JButton();
                button3.setPreferredSize(new Dimension(50, 50)); // Größe der Buttons anpassen
                button3.putClientProperty("col", i);
                button3.putClientProperty("row", j);

                Point point = new Point(i, j);
                if (!erlaubtePunkte.contains(point)) {
                    button3.setEnabled(false); // Button deaktivieren, wenn Punkt nicht erlaubt
                }

                if (spielfeld2Selected[i][j]) {
                    button3.setBackground(Color.CYAN); // Farbe setzen, falls Feld bereits ausgewählt
                }

                button3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int col = (int) button3.getClientProperty("col");
                        int row = (int) button3.getClientProperty("row");
                        if (button3.getBackground() == Color.CYAN) {
                            button3.setBackground(null);
                            spielfeld2Selected[col][row] = false;
                        } else {
                            button3.setBackground(Color.CYAN);
                            spielfeld2Selected[col][row] = true;
                        }
                    }
                });

                spielfeldPanel.add(button3);
                spielfeld2[i][j] = button3;
            }
        }
        dialog.add(spielfeldPanel, BorderLayout.CENTER);

        // Wechsel-Button und Test-Button
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton button = new JButton("Wechsel zu Spieler 1");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
                spieler = 1;
                Spielfeld1();
            }
        });
        JButton buttonTest = new JButton("Test");
        buttonTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonTest.setBackground(Color.MAGENTA);
            }
        });
        buttonPanel.add(button);
        buttonPanel.add(buttonTest);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(this); // Zentriert den Dialog relativ zum Hauptfenster
        dialog.setVisible(true);
    }
}