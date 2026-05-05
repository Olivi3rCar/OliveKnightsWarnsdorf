import java.util.ArrayList;

public class ChessQuare {
    // Main Class used to interpret square accessibility and visited-ness
    // Contained in ChessBoard class

    private int posX, posY; // Position of the square, from 1,1 to n,m
    private boolean visited; // Self-explanatory
    private ArrayList<ChessQuare> AvailableAdj = new ArrayList<ChessQuare>();
    // Resizeable list of available (non-visited),
    // adjacent (reachable by knight movement) squares.
    private boolean isWhite; // Attribute of the color of the square on the chessboard

    ChessQuare(int x, int y) {
        // Main constructor method
        this.posX = x; this.posY = y;
        this.visited = false; // Every square starts unvisited
        this.isWhite = (x+y)%2==0; // Color is based on the parity of the position
    }

    @Override
    public String toString() {
        // Main ToString Override
        return
                "Square : ------------------------------------\n" +
                "Position : " + this.posX + ", " + this.posY + "\n" +
                "Is Visited :" + this.visited + "\n" +
                "Is White :" + this.isWhite;
    }

    public void visits() {
        this.visited = true;
    }

}
