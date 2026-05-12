package visual.warnsdorf;

import javax.swing.*;
import java.awt.*;

public class SetupFrame extends JFrame {

    private JSpinner[] spinners;
    private JLabel[] labels;
    private JButton playButton;

    public SetupFrame() {
        // Frame Setup
        this.setTitle("Warnsdorf's Knight's Setup");
        this.setSize(510,530); this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon Img = new ImageIcon("knight.png");
        this.setIconImage(Img.getImage());
        this.getContentPane().setBackground(new Color(0xcccccc));
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);

        // Components setup
        // Texts
        this.labels = new JLabel[4];
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0 :
                    this.labels[i] = new JLabel("Width : ");
                    break;
                case 1 :
                    this.labels[i] = new JLabel("Length : ");
                    break;
                case 2 :
                    this.labels[i] = new JLabel("Starting X : ");
                    break;
                case 3 :
                    this.labels[i] = new JLabel("Starting Y : ");
                    break;
                default:
                    this.labels[i] = new JLabel("???");
            }
            this.labels[i].setBounds(50, 20 + (i*40), 100, 20);
            this.add(this.labels[i]);
        }

        // Number Fields

        // Start Button
        this.playButton = new JButton("Start");
        this.playButton.setBounds(100, 400, 300, 50);
        this.add(playButton);
    }
}
