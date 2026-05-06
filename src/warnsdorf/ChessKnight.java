package warnsdorf;

public class ChessKnight {
    // Main class used to interpret the movements and the Warnsdorf algorithm

    private int posX, posY; // Position of the Knight

    public ChessKnight(int x, int y) {
        // Main constructor
        this.posX = x; this.posY = y;
    }

    @Override
    public String toString() {
        // Main String Override
        return "Knight :\nPosition : " + this.posX + ", " +this.posY;
    }

    public String shortKnightPos(){return this.posX + ", " + this.posY;} // Short Position Display

    public void move(int x, int y) {
        // PositionSetter
        this.posX = x; this.posY = y;
    }
    public int getPosX() {return this.posX;} // posX getter
    public int getPosY() {return this.posY;} // posY getter
}
