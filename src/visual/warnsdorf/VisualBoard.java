package visual.warnsdorf;
import warnsdorf.ChessBoard;
import warnsdorf.ChessQuare;

import javax.swing.*;

public class VisualBoard extends ChessBoard{

    static BoardFrame frame;
    private VisualQuare[][] Vquares;
    private int sqSize;
    ImageIcon knightImage;
    JLabel knightLabel;

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

        knightLabel = new JLabel();
        knightImage = new ImageIcon("knight.png");
        knightLabel.setIcon(knightImage);

        frame.setVisible(true);
        Vquares[x][y].panel.add(knightLabel);
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
        return Next;
    }

}
