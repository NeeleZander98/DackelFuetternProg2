package dackelfuettern.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dialog {

    private JDialog dialog;

    public Dialog(String title, boolean modal, int width, int height, Frame owner) {
        this.dialog = new JDialog(owner, title, modal);
        this.dialog.setSize(width, height);
        this.dialog.setLayout(new FlowLayout());
    }


    public void createLabelAndButton(String labelText, String vertikalButtonText, String horizontalButtonText) {
        JLabel messageLabel = new JLabel(labelText);
        JButton VertikalButton = new JButton(vertikalButtonText);
        JButton HorizontalButton = new JButton(horizontalButtonText);
        VertikalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: hier ebenfalls wir im Spielfeld ggf. die Logik umändern, sodass wir nicht direkt auf die Variable richtung zugreifen und die verändern
                Board.setRichtung("vertikal");
                dialog.dispose(); // Schließen des Dialogs
            }
        });

        HorizontalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: hier ebenfalls wir im Spielfeld ggf. die Logik umändern, sodass wir nicht direkt auf die Variable richtung zugreifen und die verändern
                Board.setRichtung("horizontal");
                dialog.dispose(); // Schließen des Dialogs
            }
        });
        dialog.add(messageLabel);
        dialog.add(HorizontalButton);
        dialog.add(VertikalButton);
        dialog.setVisible(true);
    }
}
