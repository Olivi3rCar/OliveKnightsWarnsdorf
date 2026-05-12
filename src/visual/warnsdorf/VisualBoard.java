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

    public VisualBoard(int n, int m, int x, int y) {
        super(n, m, x, y);

        // Visual size of the Squares in px
        int sqSize = 500 / (Math.max(n, m));
        // Set Frame at First
        if (frame != null) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    Vquares[i][j].unvisits();
                }
            }
        } else {
            frame = new BoardFrame();
            // Fill the Array
            Vquares = new VisualQuare[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
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
        Vquares[x - 1][y - 1].panel.add(knightImage);

        if (drawPane == null) {
            drawPane = new DrawingPane(sqSize, x - 1, y - 1);
            drawPane.followLine(x - 1, y - 1);
            frame.setGlassPane(drawPane);
        } else {
            drawPane.resetline(sqSize, x - 1, y - 1);
        }
        frame.getGlassPane().setVisible(true);
    }

    @Override
    protected ChessQuare step(ChessQuare Curr, int choiceType) {
        // Uses Warnsdorf to make the knight take a step in the chessboard
        // First, make the choice of where to go depending on the current step
        ChessQuare Next = Curr.chooseSquare(choiceType);
        // Move the Knight, set new square to visited, remove already visited from curr AvailableAdj
        Next.visits();
        Next.removeVisited();
        Knight.move(Next.getPosX(), Next.getPosY());
        if (choiceType != 1) {
            try {
                Thread.sleep(150);
            } catch (java.lang.InterruptedException Except) {
                System.out.println("Interrupted Sleep");
            }
        }
        // Move the Knight image to appear on the good spot
        moveLabel(Curr, Next);
        frame.repaint();
        drawPane.repaint();
        return Next;
    }

    private void moveLabel(ChessQuare prev, ChessQuare next) {
        Vquares[prev.getPosX() - 1][prev.getPosY() - 1].panel.remove(knightImage);
        Vquares[prev.getPosX() - 1][prev.getPosY() - 1].visits();
        drawPane.followLine(next.getPosX() - 1, next.getPosY() - 1);
        Vquares[next.getPosX() - 1][next.getPosY() - 1].panel.add(knightImage);
    }

    public void repaintFrame() {
        frame.repaint();
    }
}
