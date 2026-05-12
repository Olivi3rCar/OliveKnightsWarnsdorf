package warnsdorf;

import java.util.ArrayList;
import java.util.Random;

public class ChessQuare implements Comparable<ChessQuare> {
    // Main Class used to interpret square accessibility and visited-ness
    // Contained in warnsdorf.ChessBoard class

    protected final int posX, posY; // Position of the square, from 1,1 to n,m
    protected boolean visited; // Self-explanatory
    protected final ArrayList<ChessQuare> AvailableAdj;
    // Resizeable list of available (non-visited),
    // adjacent (reachable by knight movement) squares.
    protected final boolean isWhite; // Attribute of the color of the square on the chessboard

    public ChessQuare(int x, int y) {
        // Main constructor method
        this.posX = x; this.posY = y;
        this.visited = false; // Every square starts unvisited
        this.isWhite = (x+y)%2==0; // Color is based on the parity of the position
        this.AvailableAdj = new ArrayList<ChessQuare>();
    }

    public ChessQuare(ChessQuare other) {
        // Copy constructor method
        this.posX = other.posX; this.posY = other.posY;
        this.visited = false; // Every square starts unvisited
        this.isWhite = (other.posX+other.posY)%2==0; // Color is based on the parity of the position
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

    public String shortPos(){return this.posX + ", " + this.posY;} // Short Position Display

    // x/y getters
    public int getPosX() {return this.posX;}
    public int getPosY() {return this.posY;}

    // AvailableAdj functions
    public void addVailable(ChessQuare Adj) {this.AvailableAdj.add(Adj);}
    public int getVailableNbr() {return this.AvailableAdj.size();}
    public ChessQuare removeAdj(int index) {return this.AvailableAdj.remove(index);}
    public void removeVisited() {this.AvailableAdj.removeIf(ChessQuare::getVisited);}

    public int getNextLevelAvailableNbrSum() {
        // Useful for Pohl's Method
        int sum = 0;
        for (ChessQuare chessQuare : this.AvailableAdj) {
            sum += chessQuare.getVailableNbr();
        }
        return sum;
    }

    // Comparison function based on len of AvailableAdj
    @Override
    public int compareTo(ChessQuare other) {
        return Integer.compare(this.getVailableNbr(),other.getVailableNbr());
    }

    public int chooseMin(int minSize, int choiceType) {
        // Return the choice made between 2 or more squares
        // To be called only if more than 1 square has the minimal getVailableNbr
        switch (choiceType) {
            case 1 :
                // Random
                Random randomNbr = new Random();
                return randomNbr.nextInt(minSize);
            case 2:
                // Pohl : Check for the sum of vailableNbr for all tied squares, return the minimal one
                int minIndex = 0;
                int minAdjSum = this.AvailableAdj.getFirst().getNextLevelAvailableNbrSum();
                for (int i = 1; i < minSize; i++) {
                    int newMin = this.AvailableAdj.get(i).getNextLevelAvailableNbrSum();
                    if (newMin<minAdjSum)  {minIndex = i; minAdjSum = newMin;}
                }
                return minIndex;
            case 3 :
                // Roth
                return 0;
            default : return 0;
        }
    }
    // Removal and return of the element with lowest available adjacent squares
    public ChessQuare chooseSquare(int choiceType) {
        // This assumes AvailableAdj is Not empty
        // If there is only one available Adjacent square, move to it
        if (this.AvailableAdj.size() == 1) {return removeAdj(0);}

        // Else, get the AvAdj with lowest AvAdj number
        this.AvailableAdj.sort(null);

        // If there is more than 1 Square with the minimal number, choose using chooseMin
        int nbrMin = 0;
        while (nbrMin < this.AvailableAdj.size() &&
                this.AvailableAdj.get(nbrMin).getVisited() == this.AvailableAdj.getFirst().getVisited())
        {nbrMin++;}
        int toRemove = 0;
        if (nbrMin > 1) {
            toRemove = this.chooseMin(nbrMin, choiceType);
            //System.out.println(toRemove);
        }

        return removeAdj(toRemove); // This will remove an element with minimal Available Adj
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

    public boolean getWhite() {
        return this.isWhite;
    }
}
