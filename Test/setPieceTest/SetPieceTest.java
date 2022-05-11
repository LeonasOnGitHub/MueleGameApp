package setPieceTest;

import Mill.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

public class SetPieceTest {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         Gut Tests                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void phase1GutTest1() throws InputException {
        Mill m1 = new MillImpl();
        m1.clearBoard();
        int [][]board= m1.getBoard();
        board[1][2]=1;

        int [][] boardTest =m1.setPiece(1, 2, 1);

        Assert.assertArrayEquals(board, boardTest);

    }
    @Test
    public void phase2GutTest1() throws InputException {
        Mill m1 = new MillImpl();
        m1.clearBoard();
        int [][]board= m1.getBoard();
        board[4][3]=2;

        int [][] boardTest = m1.movePiece(2, 4, 2, 3, 2);
        
        Assert.assertArrayEquals(board, boardTest);

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         Rand Tests                                                     //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void phase1RandTest1() throws InputException {
        Mill m1 = new MillImpl();
        m1.clearBoard();
        int [][]board= m1.getBoard();
        board[board.length-1][board.length-1] = 2;

        int[][] boardTest = m1.setPiece( board.length-1, board.length-1, 2);

        Assert.assertArrayEquals(board, boardTest);

    }
    @Test
    public void phase1RandTest2() throws InputException {
        Mill m1 = new MillImpl();
        m1.clearBoard();
        int [][]board= m1.getBoard();
        board[0][0] = 2;

        int[][] boardTest = m1.setPiece( 0, 0, 2);

        Assert.assertArrayEquals(board, boardTest);

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

