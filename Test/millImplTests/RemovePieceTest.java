package millImplTests;

import mill.FieldStatusException;
import mill.*;
import org.junit.Assert;
import org.junit.Test;

public class RemovePieceTest {
    Mill game = new MillImpl();
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                        good Tests                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void goodTest1() throws FieldStatusException, PhaseException {
        game.clearBoard();
        game.setPiece(3, 2, 1);
        game.setPiece(6, 0, 1);
        game.setPiece(6, 3, 1);
        game.setPiece(6, 6, 1);

        int[][] board= new int [7][7];
        board = game.defineVoid(board);
        board[6][0] = 1;
        board[6][3] = 1;
        board[6][6] = 1;

        if (game.closedAMill()){
            this.game.removePiece(3,2, 2);
        }
        Assert.assertArrayEquals(board, this.game.getBoard());
    }
    @Test
    public void goodTest2() throws FieldStatusException, PhaseException {
        game.clearBoard();
        game.setPiece(2, 4, 2);
        game.setPiece(6, 0, 1);
        game.setPiece(6, 3, 1);
        game.setPiece(6, 6, 1);

        int[][] board= new int [7][7];
        board = game.defineVoid(board);
        board[6][0] = 1;
        board[6][3] = 1;
        board[6][6] = 1;

        if (game.closedAMill()) {
            this.game.removePiece(2, 4, 1);
        }
        Assert.assertArrayEquals(board, this.game.getBoard());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                        Edge Tests                                                     //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void onEdgeTest() throws FieldStatusException, PhaseException {
        game.clearBoard();
        game.setPiece(6, 3, 2);
        game.setPiece(0, 0, 1);
        game.setPiece(0, 3, 1);
        game.setPiece(0, 6, 1);

        int[][] board= new int [7][7];
        board = game.defineVoid(board);
        board[0][0] = 1;
        board[0][3] = 1;
        board[0][6] = 1;

        if (game.closedAMill()) {
            this.game.removePiece(6, 3, 1);
        }
        Assert.assertArrayEquals(board, this.game.getBoard());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         Bad Tests                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test (expected = FieldStatusException.class)
    public void badTest_FieldEmpty() throws FieldStatusException {
        game.clearBoard();

        game.removePiece(4,4,1);
    }
    @Test (expected = FieldStatusException.class)
    public void badTest_YourStone() throws FieldStatusException, PhaseException {
        game.clearBoard();
        game.setPiece(4,4,2);

        game.removePiece(4,4, 2);
    }
    @Test
    public void badTest_noMill() throws FieldStatusException, PhaseException {
        game.clearBoard();
        game.setPiece(3, 2, 1);
        game.setPiece(6,6,2);

        int[][] board= new int [7][7];
        board = game.defineVoid(board);
        board[3][2] = 1;
        board[6][6] = 1;

        if (game.closedAMill()){
            this.game.removePiece(3,2, 2);
        }
        Assert.assertArrayEquals(board, this.game.getBoard());
    }
}
