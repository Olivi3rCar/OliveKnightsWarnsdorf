package visual.warnsdorf;

import warnsdorf.ChessBoard;
import warnsdorf.ChessQuare;

import javax.swing.*;
import java.awt.*;

public class VisualBoard extends ChessBoard {

    private static BoardFrame frame; // main Frame/Window
    private static VisualQuare[][] Vquares; // Matrix of visual squares to be used along the ChessQuare matrix
    private static JLabel knightImage; // Loaded Image of the knight
    private static DrawingPane drawPane; // Pane to draw the line on

    private final int sizeX, sizeY, baseX, baseY;

    public VisualBoard(int n, int m, int x, int y) {
        super(n, m, x, y);

        this.sizeX = n; this.sizeY = m;
        this.baseX = x; this.baseY = y;
        // Visual size of the Squares in px
        int sqSize = (int) (700 / (float) (Math.max(this.sizeX, this.sizeY)));
        // Set Frame at First Class Call (static)
        if (frame != null) {
            for (int i = 0; i < this.sizeX; i++) {
                for (int j = 0; j < this.sizeY; j++) {
                    Vquares[i][j].unvisits();
                }
            }
        } else {
            frame = new BoardFrame(this.sizeX * sqSize, this.sizeY * sqSize);
            // Fill the Array
            Vquares = new VisualQuare[this.sizeX][this.sizeY];
            for (int i = 0; i < this.sizeX; i++) {
                for (int j = 0; j < this.sizeY; j++) {
                    Vquares[i][j] = new VisualQuare(Squares[i][j], sqSize);
                    frame.addPanel(Vquares[i][j]);
                }
            }
        }

        // Get and Scale the Image if first Class call (static)
        if (knightImage == null) {
            ImageIcon prepreImage = new ImageIcon("knight.png");
            ImageIcon preImage = new ImageIcon(
                    prepreImage.getImage().getScaledInstance(sqSize, sqSize, Image.SCALE_FAST)
            );
            knightImage = new JLabel(preImage);
        }

        frame.setVisible(true);
        Vquares[this.baseX - 1][this.baseY - 1].panel.add(knightImage);

        if (drawPane == null) {
            drawPane = new DrawingPane(sqSize, x - 1, y - 1);
            drawPane.followLine(x - 1, y - 1);
            frame.setGlassPane(drawPane);
        } else {
            drawPane.resetline(sqSize, x - 1, y - 1);
        }
        frame.getGlassPane().setVisible(true);
        frame.repaint();
    }

    protected boolean visualStep(int choiceType, int sizeX, int sizeY) {
        ChessQuare Curr = this.Squares[this.Knight.getPosX()-1][this.Knight.getPosY()-1];
        if (Curr.getVailableNbr() == 0) return false;
        // Uses Warnsdorf to make the knight take a step in the chessboard
        // First, make the choice of where to go depending on the current step
        ChessQuare Next = Curr.chooseSquare(choiceType, sizeX, sizeY);
        // Move the Knight, set new square to visited, remove already visited from curr AvailableAdj
        Next.visits();
        Next.removeVisited();
        Knight.move(Next.getPosX(), Next.getPosY());
        if (choiceType != 1) {
            try {
                Thread.sleep(50);
            } catch (java.lang.InterruptedException Except) {
                System.out.println("Interrupted Sleep");
            }
        }
        // Move the Knight image to appear on the good spot
        moveLabel(Curr, Next);
        frame.repaint();
        drawPane.repaint();
        return true;
    }

    private void moveLabel(ChessQuare prev, ChessQuare next) {
        Vquares[prev.getPosX() - 1][prev.getPosY() - 1].panel.remove(knightImage);
        Vquares[prev.getPosX() - 1][prev.getPosY() - 1].visits();
        drawPane.followLine(next.getPosX() - 1, next.getPosY() - 1);
        Vquares[next.getPosX() - 1][next.getPosY() - 1].panel.add(knightImage);
    }

    public void startAnimation(int choicetype, int delay) {
        Timer timer = new Timer(delay, e -> {
            boolean status = visualStep(choicetype, this.sizeX, this.sizeY);
            if (!status) {((Timer) e.getSource()).stop();}
        });

        timer.start();
    }
}
