package warnsdorf;

import java.util.Scanner;

public class Main {
    static void main() {
        System.out.println("warnsdorf.Main Start.");

        // Get attributes
        int n, m, x, y;
        int tries = 1;
        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter Width : ");
        n = userInput.nextInt();
        System.out.print("Enter Height : ");
        m = userInput.nextInt();
        System.out.print("Enter Starting x : ");
        x = userInput.nextInt();
        System.out.print("Enter Starting y : ");
        y = userInput.nextInt();

        System.out.println("Warnsdorfing...");

        ChessBoard Board = new ChessBoard(n, m, x, y);

        while (!Board.warnsdorf(false)) {
            //Board.displayNonVisited();
            Board = new ChessBoard(n, m, x, y);
            tries++;
        }
        System.out.println("Warnsdorf Knight's tour complete on a " + n + "x" + m + " board " +
                "at starting position " + x + ", " + y + " with " + tries + " tries.");
    }
}