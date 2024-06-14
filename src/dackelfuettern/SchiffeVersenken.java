package dackelfuettern;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchiffeVersenken extends JFrame {
    private JButton[][] spielfeld1 = new JButton[10][10];
    private JButton[][] spielfeld2 = new JButton[10][10];
    private ActionListener clickerPlayerOne = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Add your action logic for player one here
        }
    };
    private ActionListener clickerPlayerTwo = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Add your action logic for player two here
        }
    };

    public SchiffeVersenken() {
        run();
    }

    public void run() {
        setTitle("Dackel füttern");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new BorderLayout());

        // Create the background panel
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load the image
                ImageIcon icon = new ImageIcon("src/dackelfuettern/assets/Hundi.jpg");
                // Draw the image as background
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new GridLayout(4, 1));

        JLabel spieler1 = new JLabel("Spieler1");
        JLabel spieler2 = new JLabel("Spieler2");
        spieler1.setHorizontalAlignment(JLabel.CENTER);
        spieler2.setHorizontalAlignment(JLabel.CENTER);

        JPanel spielfeld1Panel = new JPanel();
        spielfeld1Panel.setOpaque(false); // Make the panel transparent
        spielfeld1Panel.setLayout(new GridLayout(10, 10));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(10, 10));
                button.putClientProperty("col", i);
                button.putClientProperty("row", j);
                spielfeld1Panel.add(button);
                spielfeld1[i][j] = button;
                button.addActionListener(clickerPlayerOne);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        createAndShowDialog();
                    }
                });
            }
        }

        JPanel spielfeld2Panel = new JPanel();
        spielfeld2Panel.setOpaque(false); // Make the panel transparent
        spielfeld2Panel.setLayout(new GridLayout(10, 10));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JButton button2 = new JButton();
                button2.setPreferredSize(new Dimension(10, 10));
                spielfeld2Panel.add(button2);
                button2.putClientProperty("col", i);
                button2.putClientProperty("row", j);
                spielfeld2Panel.add(button2);
                spielfeld2[i][j] = button2;
                button2.addActionListener(clickerPlayerTwo);
                button2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        createAndShowDialog();
                    }
                });
            }
        }

        backgroundPanel.add(spieler1);
        backgroundPanel.add(spielfeld1Panel);
        backgroundPanel.add(spieler2);
        backgroundPanel.add(spielfeld2Panel);

        setContentPane(backgroundPanel);
        setVisible(true);
    }

    private void createAndShowDialog() {
        // Implement your dialog creation logic here
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SchiffeVersenken::new);
    }
}
