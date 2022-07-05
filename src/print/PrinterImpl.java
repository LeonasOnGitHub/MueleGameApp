package print;

public class PrinterImpl implements Printer{
    @Override
    public void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
             switch (board[i][j]){
                 case -1:
                     System.out.print("(#) ");
                     break;
                 case 1:
                     System.out.print("(W) ");
                     break;
                 case 2:
                     System.out.print("(S) ");
                     break;
                 case 0:
                     System.out.print("(O) ");
                     break;
             }
            }
            System.out.println();
        }
    }

    @Override
    public void printRules() {
        StringBuilder b = new StringBuilder();

        b.append("\n");
        b.append("\n");
        b.append("RULES OF THE GAME:");
        b.append("\n");

        System.out.println((b.toString()));
    }

    @Override
    public void printEndOfGame(int winner) {
        StringBuilder win = new StringBuilder();
        StringBuilder lose = new StringBuilder();

        win.append("\n");
        win.append("\n");
        win.append("W     W     W     W     W     W     W     W     W     W     W\n");
        win.append(" I     I     I     I     I     I     I     I     I     I     I\n");
        win.append("  N     N     N     N     N     N     N     N     N     N     N\n");
        win.append("   N     N     N     N     N     N     N     N     N     N     N\n");
        win.append("    E     E     E     E     E     E     E     E     E     E     E\n");
        win.append("     R     R     R     R     R     R     R     R     R     R     R\n");

        lose.append("\n");
        lose.append("\n");
        lose.append("L    L    L    L    L    L    L    L    L    L    L    L    L    L\n");
        lose.append(" O    O    O    O    O    O    O    O    O    O    O    O    O    O\n");
        lose.append("  S    S    S    S    S    S    S    S    S    S    S    S    S    S\n");
        lose.append("   E    E    E    E    E    E    E    E    E    E    E    E    E    E\n");
        lose.append("    R    R    R    R    R    R    R    R    R    R    R    R    R    R\n");

        System.out.println((win.toString()));
        System.out.println((lose.toString()));
    }
}
