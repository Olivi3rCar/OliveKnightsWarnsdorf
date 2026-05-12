package visual.warnsdorf;

public class Main {
    static void main() {
        System.out.println("Visual Main Start");

        VisualBoard Test = new VisualBoard(8,8, 1, 1);
        while (!Test.warnsdorf(1, false)) {
            //Board.displayNonVisited();
            Test = new VisualBoard(8, 8, 1, 1);
        }
    }
}
