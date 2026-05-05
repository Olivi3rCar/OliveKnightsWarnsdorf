void main() {
    System.out.println("Main Start");
    int n = 5, m = 5;

    ChessBoard Board = new ChessBoard(n, m, 1, 1);
    System.out.println(Board);
    Board.displayAvailable(1,1);
    Board.displayAvailable(3,2);
    Board.displayKnightPos();
}