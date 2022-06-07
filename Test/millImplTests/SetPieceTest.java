package millImplTests;

import mill.*;
import org.junit.Assert;
import org.junit.Test;



public class SetPieceTest {
    Mill game = new MillImpl();

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         Gut Tests                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void phase1GutTest1() throws FieldStatusException, PhaseException {
        game.clearBoard();

        int [][]board = new int[7][7];
        board = game.defineVoid(board);
        board[2][2]=1;

        this.game.setPiece(2, 2, 1);

        Assert.assertArrayEquals(board, this.game.getBoard());

    }
    @Test
    public void phase1GutTest2() throws FieldStatusException, PhaseException {
        game.clearBoard();

        int [][]board= new int[7][7];
        board = game.defineVoid(board);
        board[4][4]=1;

        this.game.setPiece(4, 4, 1);

        Assert.assertArrayEquals(board, this.game.getBoard());

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         Rand Tests                                                     //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void phase1RandTest1() throws FieldStatusException, PhaseException {
        game.clearBoard();

        int [][]board= new int[7][7];
        board = game.defineVoid(board);
        board[board.length-1][board.length-1] = 2;

        this.game.setPiece( board.length-1, board.length-1, 2);

        Assert.assertArrayEquals(board, this.game.getBoard());

    }
    @Test
    public void phase1RandTest2() throws FieldStatusException, PhaseException {
        game.clearBoard();

        int [][]board= new int[7][7];
        board = game.defineVoid(board);
        board[0][0] = 2;

        this.game.setPiece( 0, 0, 2);

        Assert.assertArrayEquals(board, this.game.getBoard());

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         Schlecht Tests                                                 //
   ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test(expected = FieldStatusException.class)
    public void phase1Schlechtest1() throws FieldStatusException, PhaseException {
         game.clearBoard();

         game.setPiece( 2, 3, 2);
         game.setPiece( 2, 3, 2);


    }


}

