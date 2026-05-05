import java.util.ArrayList;

public class ChessQuare implements Comparable<ChessQuare> {
    // Main Class used to interpret square accessibility and visited-ness
    // Contained in ChessBoard class

    private int posX, posY; // Position of the square, from 1,1 to n,m
    private boolean visited; // Self-explanatory
    private ArrayList<ChessQuare> AvailableAdj;
    // Resizeable list of available (non-visited),
    // adjacent (reachable by knight movement) squares.
    private boolean isWhite; // Attribute of the color of the square on the chessboard

    ChessQuare(int x, int y) {
        // Main constructor method
        this.posX = x; this.posY = y;
        this.visited = false; // Every square starts unvisited
        this.isWhite = (x+y)%2==0; // Color is based on the parity of the position
        this.AvailableAdj = new ArrayList<ChessQuare>();
    }

    @Override
    public String toString() {
        // Main ToString Override
        return
                "Square : ------------------------------------\n" +
                "Position : " + this.posX + ", " + this.posY + "\n" +
                "Is Visited : " + this.visited + "\n" +
                "Is White : " + this.isWhite;
    }

    public void visits() {this.visited = true;}
    public boolean getVisited() {return this.visited;}

    // x/y getters
    public int getPosX() {return this.posX;}
    public int getPosY() {return this.posY;}

    // AvailableAdj functions
    public void addVailable(ChessQuare Adj) {this.AvailableAdj.add(Adj);}
    public int getVailableNbr() {return this.AvailableAdj.size();}
    public ChessQuare removeAdj() {return this.AvailableAdj.removeFirst();}

    // Comparison function based on len of AvailableAdj
    @Override
    public int compareTo(ChessQuare other) {
        return Integer.compare(this.getVailableNbr(),other.getVailableNbr());
    }

    // Removal and return of the element with lowest available adjacent squares
    public ChessQuare chooseSquare() {
        this.AvailableAdj.sort(null);
        return removeAdj(); // This will remove the first element of the sorted list
    }

    public void displayAdj() {
        if (this.getVailableNbr()==0) {
        System.out.println("No available Adjacent Squares."); return;
        }
        System.out.println(this.getVailableNbr() + " elements :");
        for (int i = 0 ; i < this.getVailableNbr() ; i++) {
            ChessQuare Curr = this.AvailableAdj.get(i);
            System.out.println(
                    Curr.getPosX() + ", " + Curr.getPosY() + " with " + Curr.getVailableNbr() + " Available Adjacent"
            );
        }
    }
}
