package visual.warnsdorf;

import javax.swing.*;
import java.awt.*;

public class BoardFrame extends JFrame {

    public BoardFrame() {
        // Frame Setup
        this.setTitle("Warnsdorf's Knight's Tour");
        this.setSize(510,530); this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon Img = new ImageIcon("knight.png");
        this.setIconImage(Img.getImage());
        this.getContentPane().setBackground(new Color(0xcccccc));
        this.setLayout(null);
    }

    public void addPanel(VisualQuare Vquare) {
        this.add(Vquare.panel);
    }
}
