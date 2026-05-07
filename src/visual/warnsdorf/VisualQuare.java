package visual.warnsdorf;

import warnsdorf.ChessQuare;

import javax.swing.*;
import java.awt.*;

public class VisualQuare extends ChessQuare {

    public JPanel panel;

    public VisualQuare(ChessQuare toCopy, int size) {
        super(toCopy);

        this.panel = new JPanel();
        panel.setBackground(new Color((this.isWhite) ? 0xf2fca2 : 0x384d28));
        panel.setBounds((this.posX-1)*size, (this.posY-1)*size, size, size);
    }
}
