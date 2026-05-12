package visual.warnsdorf;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class DrawingPane extends JComponent {
    private final int sqSize;
    private Path2D path;

    public DrawingPane(int size, int startX, int startY) {
        super();
        this.setOpaque(false);
        this.setVisible(true);
        this.sqSize = size;
        this.path = new Path2D.Double();
        this.path.moveTo((startX*this.sqSize) + ((double) this.sqSize /2),
                (startY*this.sqSize) + ((double) this.sqSize /2));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // setting the drawline
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0x800020));
        g2d.setStroke(new BasicStroke((float) sqSize /25));
        g2d.draw(this.path);
    }

    public void followLine(int x, int y) {
        int centerX = (x*this.sqSize) + (this.sqSize/2);
        int centerY = (y*this.sqSize) + (this.sqSize/2);
        this.path.lineTo(centerX, centerY);
        this.repaint();
    }

    public void resetline(int size, int startX, int startY) {
        this.path.reset();
        this.path.moveTo((startX * this.sqSize) + ((double) this.sqSize / 2),
                (startY*this.sqSize) + ((double) this.sqSize /2));
    }
}
