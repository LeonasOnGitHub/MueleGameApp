package millImplTest;

import mill.*;
import org.junit.Assert;
import org.junit.Test;

public class JumpPieceTest {
    Mill m1 = new MillImpl();
    BoardEngine engine = new BoardEngineImpl();
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                        good Tests                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void goodTest1() throws FieldStatusException, PhaseException {
        m1.clearBoard();

        engine.setGamePhase(1);
        m1.setPiece(3, 2, 1);

        int[][] board= new int [7][7];
        board[5][5]=1;

        engine.setGamePhase(2);
        m1.jumpPiece(3,2,5,5,1);
        Assert.assertArrayEquals(board, m1.getBoard());
    }
    @Test
    public void goodTest2() throws FieldStatusException, PhaseException {
        m1.clearBoard();

        engine.setGamePhase(1);
        m1.setPiece(5, 3, 2);

        int[][] board= new int [7][7];
        board[4][4]=1;

        engine.setGamePhase(2);
        m1.jumpPiece(5,3,4,4,1);
        Assert.assertArrayEquals(board, m1.getBoard());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                        Edge Tests                                                     //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void fromEdgeTest() throws FieldStatusException, PhaseException {
        m1.clearBoard();

        engine.setGamePhase(1);
        m1.setPiece(6, 3, 2);

        int[][] board= new int [7][7];
        board[3][1]=2;

        engine.setGamePhase(2);
        m1.jumpPiece(6,3,3,1,2);
        Assert.assertArrayEquals(board, m1.getBoard());
    }
    @Test
    public void onEdgeTest() throws FieldStatusException, PhaseException {
        m1.clearBoard();

        engine.setGamePhase(1);
        m1.setPiece(6, 6, 1);

        int[][] board= new int [7][7];
        board[0][6]=1;

        engine.setGamePhase(2);
        m1.jumpPiece(6,6,0,6,1);
        Assert.assertArrayEquals(board, m1.getBoard());
    }
    @Test
    public void toEdgeTest() throws FieldStatusException, PhaseException {
        m1.clearBoard();

        engine.setGamePhase(1);
        m1.setPiece(1, 5, 1);

        int[][] board= new int [7][7];
        board[3][6]=1;

        engine.setGamePhase(2);
        m1.jumpPiece(1,5,3,6,1);
        Assert.assertArrayEquals(board, m1.getBoard());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         Bad Tests                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test (expected = FieldStatusException.class)
    public void badTest_FieldEmpty() throws FieldStatusException {
        m1.clearBoard();

        engine.setGamePhase(2);
        m1.jumpPiece(3,4,4,4,1);
    }
    @Test (expected = FieldStatusException.class)
    public void badTest_FieldOccupied() throws FieldStatusException, PhaseException {
        m1.clearBoard();

        engine.setGamePhase(1);
        m1.setPiece(4,4,2);
        m1.setPiece(2, 2, 1);

        engine.setGamePhase(2);
        m1.jumpPiece(2,2,4,4,1);
    }
    @Test (expected = FieldStatusException.class)
    public void badTest_NotYourToken() throws FieldStatusException, PhaseException {
        m1.clearBoard();

        engine.setGamePhase(1);
        m1.setPiece(3,4,2);

        engine.setGamePhase(2);
        m1.jumpPiece(3,4,5,1,1);
    }

}
