public class ChessBoard {
    // Main Class used to represent and build ChessQuare objects,
    // Calculate and Interpret the knight's movements

    private int width, height; // width and height of the chessboard in number of squares
    private ChessQuare[][] Squares; // Main Array containing all squares (non-resizeable)
    private ChessKnight Knight;

    ChessBoard(int n, int m, int x, int y) {
        // Main constructor and Instantiator

        // Squares Instantiation
        this.width = n; this.height = m;
        this.Squares = new ChessQuare[n][m]; // Instantiate the Main Array

        // Fill the Array
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < m; j++) {
                this.Squares[i][j] = new ChessQuare(i + 1, j + 1);
            }
        }

        // Knight Instantiation
        this.Knight = new ChessKnight(x, y);
        this.Squares[x-1][y-1].visits();
        this.setAll();
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

    public void displayAvailable(int x, int y) {
        // Displays Available Adjacent Squares for Square x, y
        this.Squares[x-1][y-1].displayAdj();
    }

    private void setAvailable(int x, int y) {
        // Sets the AvailableAdj for Square at position x, y
        // Values for x & y between 0 & 0 and width & length respectively
        int[][] mvmt = {
                {x+2, y+1}, {x+2, y-1}, {x-2, y+1}, {x-2, y-1},
                {x+1, y+2}, {x+1, y-2}, {x-1, y+2}, {x-1, y-2},
        }; // Each possible movements for a knight on a chessboard
        for (int[] ints : mvmt) {
            int cx = ints[0], cy = ints[1];
            // If the movement is inbounds and the square is not yet visited
            if (
                    cx < this.width && cx >= 0 &&
                    cy < this.height && cy >= 0 &&
                    !this.Squares[cx][cy].getVisited()
            ) {
                // add to available
                this.Squares[x][y].addVailable(this.Squares[cx][cy]);
            }
        }
    }

    private void setAll() {
        // Sets the Available Adjacent Squares list for all Squares on the board
        for (int i = 0 ; i < this.width ; i++) {
            for (int j = 0 ; j < this.height ; j++){
                this.setAvailable(i, j);
            }
        }
    }

    public void displayKnightPos() {
        System.out.println("Knight is at Position : " + this.Knight.getPosX() + ", " + this.Knight.getPosY());
    }


}
