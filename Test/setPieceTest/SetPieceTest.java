package setPieceTest;

import Mill.*;
import org.junit.Assert;
import org.junit.Test;



public class SetPieceTest {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         Gut Tests                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void phase1GutTest1() throws FieldStatusException {
        Mill m1 = new MillImpl();
        m1.clearBoard();

        int [][]board= new int[5][5];
        board[1][2]=1;

        m1.setPiece(1, 2, 1);

        Assert.assertArrayEquals(board, m1.getBoard());

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         Rand Tests                                                     //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void phase1RandTest1() throws InputException {
        Mill m1 = new MillImpl();
        m1.clearBoard();

        int [][]board= new int[5][5];
        board[board.length-1][board.length-1] = 2;

        m1.setPiece( board.length-1, board.length-1, 2);

        Assert.assertArrayEquals(board, m1.getBoard());

    }
    @Test
    public void phase1RandTest2() throws InputException {
        Mill m1 = new MillImpl();
        m1.clearBoard();

        int [][]board= new int[5][5];
        board[0][0] = 2;

        m1.setPiece( 0, 0, 2);

        Assert.assertArrayEquals(board, m1.getBoard());

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         Schlecht Tests                                                 //
   ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test(expected = InputException.class)
    public void phase1Schlechtest1() throws InputException {
        Mill m1 = new MillImpl();
        m1.clearBoard();

         m1.setPiece( 2, 3, 2);
         m1.setPiece( 2, 3, 2);


    }


}

