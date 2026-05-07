package warnsdorf;

public abstract class ChessBoard {
    // Main Class used to represent and build warnsdorf.ChessQuare objects,
    // Calculate and Interpret the knight's movements

    protected int width, height; // width and height of the chessboard in number of squares
    protected ChessQuare[][] Squares; // Main Array containing all squares (non-resizeable)
    protected ChessKnight Knight;

    public ChessBoard(int n, int m, int x, int y) {
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
        System.out.println("Knight is at Position : " + this.Knight.shortKnightPos());
    }

    public boolean allVisited() {
        // Verifies if all squares have been visited
        for (ChessQuare[] row : this.Squares) {
            for (ChessQuare Curr : row) {
                if (!Curr.getVisited()) {return false;}
            }
        }
        return true;
    }

    public void displayNonVisited() {
        StringBuilder nonVis = new StringBuilder("Non Visited : ");
        for (ChessQuare[] row : this.Squares) {
            for (ChessQuare Curr : row) {
                if (!Curr.getVisited()) {nonVis.append(Curr.shortPos()).append(" ; ");}
            }
        }
        System.out.println(nonVis);
    }

    // ---------- Warnsdorf -----------

    private ChessQuare step(ChessQuare Curr, int choiceType) {
        // Uses Warnsdorf to make the knight take a step in the chessboard
        // First, make the choice of where to go depending on the current step
        ChessQuare Next = Curr.chooseSquare(choiceType);
        // Move the Knight, set new square to visited, remove already visited from curr AvailableAdj
        Next.visits();
        Next.removeVisited();
        Knight.move(Next.getPosX(), Next.getPosY());
        return Next;
    }

    public boolean warnsdorf(int choiceType, boolean displays) {
        // Implementation of Warnsdorf's Rule :
        // Always go to the square with the lowest number of available adjacent Squares.
        // Starts on the Knight's first space
        ChessQuare Position = this.Squares[this.Knight.getPosX()-1][this.Knight.getPosY()-1];
        if (displays) {
            System.out.println("Starting at position :" + this.Knight.shortKnightPos());
        }
        // As long as there is movement possible, take a step
        while (Position.getVailableNbr() > 0) {
            Position = this.step(Position, choiceType);
            if (displays) {
                System.out.println("Moved to :" + this.Knight.shortKnightPos());
            }
        }
        if (displays) {
            System.out.println("Tour finished. ");
            if (this.allVisited()) {System.out.println("All Squares have been visited.");}
            else {System.out.println("All Squares have NOT been visited.");}
        }
        return this.allVisited();
    }

    public boolean warnsdorf(int choicetype) {
        return warnsdorf(choicetype, false);
    }

    public boolean warnsdorf() {
        return warnsdorf(0, false);
    }

    protected abstract ChessQuare step(ChessQuare Curr, int choiceType);
}
