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

    }

    @Override
    public void printEndOfGame(int winner) {

    }
}
