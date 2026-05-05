void main() {
    System.out.println("Main Start");
    int n = 6, m = 6;
    int tries = 1;
    ChessBoard Board = new ChessBoard(n, m, 1, 1);

    //System.out.println(Board);
    //Board.displayAvailable(1,1);
    //Board.displayAvailable(3,2);
    //Board.displayKnightPos();

    while (!Board.warnsdorf(false)) {
        //Board.displayNonVisited();
        Board = new ChessBoard(n, m, 1, 1);
        tries++;
    }
    System.out.println("Warnsdorf Knight's tour complete on a "+n+"x"+m+" board with "+tries+" tries.");
}