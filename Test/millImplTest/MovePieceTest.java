package millImplTest;

import Mill.*;
import org.junit.Assert;
import org.junit.Test;

public class MovePieceTest {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         Gut Tests                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void gutTest1() throws FieldStatusException, MovementExeption {
        Mill m1 = new MillImpl();
        m1.clearBoard();
        m1.setPiece(3, 2, 1);

        int[][] board= new int [7][7];
        board[2][2]=1;

        m1.movePiece(3,2,2,2,1);
        Assert.assertArrayEquals(board, m1.getBoard());
    }
    @Test
    public void gutTest2() throws FieldStatusException, MovementExeption {
        Mill m1 = new MillImpl();
        m1.clearBoard();
        m1.setPiece(4, 4, 2);

        int[][] board= new int [7][7];
        board[4][3]=2;

        m1.movePiece(4,4,4,3,2);
        Assert.assertArrayEquals(board, m1.getBoard());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         Rand Tests                                                     //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void vomRandTest() throws FieldStatusException, MovementExeption {
        Mill m1 = new MillImpl();
        m1.clearBoard();
        m1.setPiece(6, 3, 2);

        int[][] board= new int [7][7];
        board[5][3]=2;

        m1.movePiece(6,3,5,3,2);
        Assert.assertArrayEquals(board, m1.getBoard());
    }
    @Test
    public void amRandTest() throws FieldStatusException, MovementExeption {
        Mill m1 = new MillImpl();
        m1.clearBoard();
        m1.setPiece(6, 6, 1);

        int[][] board= new int [7][7];
        board[3][6]=1;

        m1.movePiece(6,6,3,6,1);
        Assert.assertArrayEquals(board, m1.getBoard());
    }
    @Test
    public void zumRandTest() throws FieldStatusException, MovementExeption {
        Mill m1 = new MillImpl();
        m1.clearBoard();
        m1.setPiece(3, 5, 1);

        int[][] board= new int [7][7];
        board[3][6]=1;

        m1.movePiece(3,5,3,6,1);
        Assert.assertArrayEquals(board, m1.getBoard());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         Schlecht Tests                                                 //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test (expected = FieldStatusException.class)
    public void schlechtTestFeldLeer() throws FieldStatusException, MovementExeption {
        Mill m1 = new MillImpl();
        m1.clearBoard();

        m1.movePiece(3,4,4,4,1);
    }
    @Test (expected = FieldStatusException.class)
    public void schlechtTestFeldBesetzt() throws FieldStatusException, MovementExeption {
        Mill m1 = new MillImpl();
        m1.clearBoard();
        m1.setPiece(4,4,2);

        m1.movePiece(3,4,4,4,1);
    }
    @Test (expected = FieldStatusException.class)
    public void schlechtTestNichtDeinStein() throws FieldStatusException, MovementExeption {
        Mill m1 = new MillImpl();
        m1.clearBoard();
        m1.setPiece(3,4,2);
        m1.movePiece(3,4,4,4,1);
    }
    @Test (expected = MovementExeption.class)
    public void schlechtTestEntfernung() throws FieldStatusException, MovementExeption {
        Mill m1 = new MillImpl();
        m1.clearBoard();
        m1.setPiece(3,4,1);

        m1.movePiece(3,4,3,6,1);
    }
}

