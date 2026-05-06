package visual.warnsdorf;

public class Main {
    static void main() {
        System.out.println("Visual Main Start");

        VisualBoard Test = new VisualBoard(3, 4, 1, 1);
        Test.warnsdorf(0);
    }
}
