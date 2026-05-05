public class ChessBoard {
    // Main Class used to represent and build ChessQuare objects,
    // Calculate and Interpret the knight's movements

    private int width, height; // width and height of the chessboard in number of squares
    private ChessQuare[][] Squares; // Main Array containing all squares (non-resizeable)

    ChessBoard(int n, int m) {
        this.width = n; this.height = m;
        this.Squares = new ChessQuare[n][m]; // Instantiate the Main Array

        // Fill the Array
        for (int i = 0; i < n ; i++) {
            for (int j = 0 ; j < m ; j++) {
                this.Squares[i][j] = new ChessQuare(i+1, j+1);
            }
        }

    }

    @Override
    public String toString() {
        // Main ToString Override
        StringBuilder rStr = new StringBuilder("Board : \n");
        for (int i = 0; i < this.width ; i++) {
            for (int j = 0 ; j < this.height ; j++) {
                rStr.append(this.Squares[i][j].toString()).append("\n");
            }
        }
        return rStr.toString();
    }
}
