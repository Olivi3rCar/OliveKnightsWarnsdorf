package visual.warnsdorf;

import warnsdorf.ChessQuare;

import javax.swing.*;
import java.awt.*;

public class VisualQuare extends ChessQuare {

    public JPanel panel;
    private final Color visitedCol, unvisitedCol;

    public VisualQuare(ChessQuare toCopy, int size) {
        super(toCopy);

        this.panel = new JPanel();
        this.unvisitedCol = new Color((this.isWhite) ? 0xf2fca2 : 0x384d28);
        this.visitedCol = new Color((this.isWhite) ? 0xeeee00 : 0xaaaa00);
        this.panel.setBackground(this.unvisitedCol);
        panel.setBounds((this.posX-1)*size, (this.posY-1)*size, size, size);
    }

    @Override
    public void visits() {
        super.visits();
        this.panel.setBackground(this.visitedCol);
        this.panel.repaint();
    }
    public void unvisits() {
        this.panel.setBackground(this.unvisitedCol);
        this.panel.repaint();
    }
}
