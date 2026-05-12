package visual.warnsdorf;
import warnsdorf.ChessBoard;
import warnsdorf.ChessQuare;

import javax.swing.*;
import java.awt.*;

public class VisualBoard extends ChessBoard{

    static BoardFrame frame; // main Frame/Window
    private VisualQuare[][] Vquares; // Matrix of visual squares to be used along the ChessQuare matrix
    private final int sqSize; // Visual size of the Squares in px
    static JLabel knightImage; // Loaded Image of the knight
    private DrawingPane drawPane; // Pane to draw the line on

    private final static Color visitedCol = new Color(0xFFFF00);

    public VisualBoard(int n, int m, int x, int y) {
        super(n, m, x, y);

        // Set Frame
        frame = new BoardFrame();
        Vquares = new VisualQuare[n][m];
        sqSize = 470/(Math.max(n, m));

        // Fill the Array
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < m; j++) {
                this.Vquares[i][j] = new VisualQuare(Squares[i][j], sqSize);
                frame.addPanel(this.Vquares[i][j]);
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

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Vquares[x-1][y-1].panel.add(knightImage);

        this.drawPane = new DrawingPane(sqSize);
        this.drawPane.setVisible(true);
        this.drawPane.followLine(x, y);
        frame.setGlassPane(this.drawPane);
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
        try {Thread.sleep(10);}
        catch (java.lang.InterruptedException Except) {System.out.println("Interrupted Sleep");}
        moveLabel(Curr, Next);
        frame.repaint();
        this.drawPane.repaint();
        return Next;
    }

    private void moveLabel(ChessQuare prev, ChessQuare next) {
        this.Vquares[prev.getPosX()-1][prev.getPosY()-1].panel.remove(knightImage);
        this.Vquares[prev.getPosX()-1][prev.getPosY()-1].panel.setBackground(visitedCol);
        this.drawPane.followLine(next.getPosX()-1, next.getPosY()-1);
        this.Vquares[next.getPosX()-1][next.getPosY()-1].panel.add(knightImage);
    }

}
