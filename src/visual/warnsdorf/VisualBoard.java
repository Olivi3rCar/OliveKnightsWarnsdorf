package visual.warnsdorf;
import warnsdorf.ChessBoard;
import warnsdorf.ChessQuare;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class VisualBoard extends ChessBoard{

    static BoardFrame frame;
    private VisualQuare[][] Vquares;
    private final int sqSize;
    JLabel knightImage;

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

        ImageIcon preImage = new ImageIcon("knight.png");

        knightImage = new JLabel(preImage);


        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Vquares[x-1][y-1].panel.add(knightImage);
    }

    protected ChessQuare step(ChessQuare Curr, int choiceType) {
        // Uses Warnsdorf to make the knight take a step in the chessboard
        // First, make the choice of where to go depending on the current step
        ChessQuare Next = Curr.chooseSquare(choiceType);
        // Move the Knight, set new square to visited, remove already visited from curr AvailableAdj
        Next.visits();
        Next.removeVisited();
        Knight.move(Next.getPosX(), Next.getPosY());
        return Next;
    }

}
