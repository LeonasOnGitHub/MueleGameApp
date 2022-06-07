package millImplTests;

import mill.*;
import org.junit.Assert;
import org.junit.Test;

public class JumpPieceTest {
    Mill game = new MillImpl();
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                        good Tests                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void goodTest1() throws FieldStatusException, PhaseException {
        game.clearBoard();

        game.setPiece(3, 2, 1);
        game.setPiece(3, 5, 1);
        game.setPiece(3, 1, 1);

        int[][] board= new int [7][7];
        board = game.defineVoid(board);
        board[5][5]=1;
        board[3][5]=1;
        board[3][1]=1;

        this.game.jumpPiece(3,2,5,5,1);
        Assert.assertArrayEquals(board, this.game.getBoard());
    }
    @Test
    public void goodTest2() throws FieldStatusException, PhaseException {
        game.clearBoard();

        game.setPiece(5, 3, 1);
        game.setPiece(5, 1, 1);
        game.setPiece(5, 5, 1);

        int[][] board= new int [7][7];
        board = game.defineVoid(board);
        board[4][4]=1;
        board[5][5]=1;
        board[5][1]=1;

        this.game.jumpPiece(5,3,4,4,1);
        Assert.assertArrayEquals(board, this.game.getBoard());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                        Edge Tests                                                     //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void fromEdgeTest() throws FieldStatusException, PhaseException {
        game.clearBoard();

        game.setPiece(6, 3, 2);
        game.setPiece(0, 3, 2);
        game.setPiece(6, 6, 2);

        int[][] board= new int [7][7];
        board = game.defineVoid(board);
        board[3][1]=2;
        board[0][3]=2;
        board[6][6]=2;

        this.game.jumpPiece(6,3,3,1,2);
        Assert.assertArrayEquals(board, this.game.getBoard());
    }
    @Test
    public void onEdgeTest() throws FieldStatusException, PhaseException {
        game.clearBoard();

        game.setPiece(6, 6, 1);
        game.setPiece(6, 0, 1);
        game.setPiece(3, 0, 1);

        int[][] board= new int [7][7];
        board = game.defineVoid(board);
        board[0][6]=1;
        board[6][0]=1;
        board[3][0]=1;

        this.game.jumpPiece(6,6,0,6,1);
        Assert.assertArrayEquals(board, this.game.getBoard());
    }
    @Test
    public void toEdgeTest() throws FieldStatusException, PhaseException {
        game.clearBoard();

        game.setPiece(1, 5, 1);
        game.setPiece(1, 1, 1);
        game.setPiece(1, 3, 1);

        int[][] board= new int [7][7];
        board = game.defineVoid(board);
        board[3][6]=1;
        board[1][1]=1;
        board[1][3]=1;

        this.game.jumpPiece(1,5,3,6,1);
        Assert.assertArrayEquals(board, this.game.getBoard());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         Bad Tests                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test (expected = FieldStatusException.class)
    public void badTest_FieldEmpty() throws FieldStatusException, PhaseException {
        game.clearBoard();

        game.setPiece(1, 5, 1);
        game.setPiece(1, 1, 1);
        game.setPiece(1, 3, 1);

        game.jumpPiece(3,4,4,4,1);
    }
    @Test (expected = FieldStatusException.class)
    public void badTest_FieldOccupied() throws FieldStatusException, PhaseException {
        game.clearBoard();


        game.setPiece(1, 1, 1);
        game.setPiece(1, 3, 1);
        game.setPiece(4, 4, 2);
        game.setPiece(2, 2, 1);

        game.jumpPiece(2,2,4,4,1);
    }
    @Test (expected = FieldStatusException.class)
    public void badTest_NotYourToken() throws FieldStatusException, PhaseException {
        game.clearBoard();
        game.setPiece(1, 5, 1);
        game.setPiece(1, 5, 1);
        game.setPiece(1, 3, 1);
        game.setPiece(3, 4, 2);

        game.jumpPiece(3,4,5,1,1);
    }

}
