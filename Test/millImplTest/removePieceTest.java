package millImplTest;

import Mill.FieldStatusException;
import Mill.*;
import org.junit.Assert;
import org.junit.Test;

public class removePieceTest {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                        good Tests                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void goodTest1() throws FieldStatusException {
        Mill m1 = new MillImpl();
        m1.clearBoard();
        m1.setPiece(3, 2, 1);

        int[][] board= new int [7][7];
        board[3][1]=0;

        m1.removePiece(3,2,0);
        Assert.assertArrayEquals(board, m1.getBoard());
    }
    @Test
    public void goodTest2() throws FieldStatusException {
        Mill m1 = new MillImpl();
        m1.clearBoard();
        m1.setPiece(2, 4, 2);

        int[][] board= new int [7][7];
        board[2][4]=0;

        m1.removePiece(2,4,0);
        Assert.assertArrayEquals(board, m1.getBoard());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                        Edge Tests                                                     //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void onEdgeTest() throws FieldStatusException {
        Mill m1 = new MillImpl();
        m1.clearBoard();
        m1.setPiece(6, 3, 2);

        int[][] board= new int [7][7];
        board[6][3]=0;

        m1.removePiece(6,3,0);
        Assert.assertArrayEquals(board, m1.getBoard());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         Bad Tests                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test (expected = FieldStatusException.class)
    public void badTest_FieldEmpty() throws FieldStatusException {
        Mill m1 = new MillImpl();
        m1.clearBoard();

        m1.jumpPiece(3,4,4,4,1);
    }
    @Test (expected = FieldStatusException.class)
    public void badTest_NotYourStone() throws FieldStatusException {
        Mill m1 = new MillImpl();
        m1.clearBoard();
        m1.setPiece(4,4,2);

        m1.removePiece(4,4,1);
    }
}
