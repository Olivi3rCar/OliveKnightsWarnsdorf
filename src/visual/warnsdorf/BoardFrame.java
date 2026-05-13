package visual.warnsdorf;

import javax.swing.*;
import java.awt.*;

public class BoardFrame extends JFrame {

    public BoardFrame(int sizeX, int sizeY) {
        // Frame Setup
        this.setTitle("Warnsdorf's Knight's Tour");
        this.setSize(sizeX + 12, sizeY + 35); this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon Img = new ImageIcon("knight.png");
        this.setIconImage(Img.getImage());
        this.getContentPane().setBackground(new Color(0xcccccc));
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
    }

    public void addPanel(VisualQuare Vquare) {
        this.add(Vquare.panel);
    }
}
