package millImplTest;

import mill.FieldStatusException;
import mill.*;
import org.junit.Assert;
import org.junit.Test;

public class removePieceTest {
    Mill game = new MillImpl();
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                        good Tests                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void goodTest1() throws FieldStatusException, PhaseException {
        game.clearBoard();
        game.setPiece(3, 2, 1);

        int[][] board= new int [7][7];
        board = game.defineVoid(board);
        board[3][1]=0;

        this.game.removePiece(3,2, 2);
        Assert.assertArrayEquals(board, this.game.getBoard());
    }
    @Test
    public void goodTest2() throws FieldStatusException, PhaseException {
        game.clearBoard();
        game.setPiece(2, 4, 2);

        int[][] board= new int [7][7];
        board = game.defineVoid(board);
        board[2][4]=0;

        this.game.removePiece(2,4, 1);
        Assert.assertArrayEquals(board, this.game.getBoard());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                        Edge Tests                                                     //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void onEdgeTest() throws FieldStatusException, PhaseException {
        game.clearBoard();
        game.setPiece(6, 3, 2);

        int[][] board= new int [7][7];
        board = game.defineVoid(board);
        board[6][3]=0;

        this.game.removePiece(6,3, 1);
        Assert.assertArrayEquals(board, this.game.getBoard());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         Bad Tests                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test (expected = FieldStatusException.class)
    public void badTest_FieldEmpty() throws FieldStatusException {
        game.clearBoard();

        game.removePiece(5,4,1);
    }
    @Test (expected = FieldStatusException.class)
    public void badTest_YourStone() throws FieldStatusException, PhaseException {
        game.clearBoard();
        game.setPiece(4,4,2);

        game.removePiece(4,4, 2);
    }
}
