package visual.warnsdorf;

import warnsdorf.ChessBoard;

import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main {
    static void main() {
        System.out.println("Visual Main Start");

        SetupFrame setup = new SetupFrame();
        setup.setVisible(true);

        // Get attributes
//        int n, m, x, y, c;
//        int tries = 1;
//        Scanner userInput = new Scanner(System.in);
//        System.out.print("Enter Width : ");
//        n = userInput.nextInt();
//        System.out.print("Enter Height : ");
//        m = userInput.nextInt();
//        System.out.print("Enter Starting x : ");
//        x = userInput.nextInt();
//        System.out.print("Enter Starting y : ");
//        y = userInput.nextInt();
//        System.out.print("Enter Choicetype (0-default, 1-random): ");
//        c = userInput.nextInt();
//
//        System.out.println("Warnsdorfing...");
//
//        VisualBoard Test = new VisualBoard(n,m, x, y);
//        while (!Test.warnsdorf(c, false)) {
//            //Board.displayNonVisited();
//            Test = new VisualBoard(n, m, x, y);
//        }
//
//        try {sleep(1000); Test.repaintFrame();}
//        catch (InterruptedException except) {System.out.println("Interrupted");}
//
//        System.out.println("Finished");
    }
}
