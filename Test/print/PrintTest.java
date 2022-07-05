package print;

import mill.FieldStatusException;
import mill.Mill;
import mill.MillImpl;
import mill.PhaseException;
import org.junit.Test;

public class PrintTest {
    Mill game = new MillImpl();
    Printer print = new PrinterImpl();

    @Test
    public void printBoardTest() throws FieldStatusException, PhaseException {
        game.clearBoard();

        game.setPiece(3, 2, 1);
        game.setPiece(4, 4, 1);
        game.setPiece(2, 3, 1);
        game.setPiece(1, 1, 1);

        int[][] board= new int [7][7];
        board = game.defineVoid(board);
        board[2][2] = 2;
        board[1][1] = 1;
        board[2][3] = 1;
        board[4][4] = 1;

        print.printBoard(board);

    }

    @Test
    public void printRulesTest(){print.printRules();}

    @Test
    public void printEndOfGameTest(){print.printEndOfGame(1);}
}
