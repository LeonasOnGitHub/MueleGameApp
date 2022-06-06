package millImplTest;

import mill.FieldStatusException;
import mill.*;
import org.junit.Assert;
import org.junit.Test;

public class removePieceTest {
    Mill board = new MillImpl();
    BoardEngine engine = new BoardEngineImpl();
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                        good Tests                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void goodTest1() throws FieldStatusException, PhaseException {
        board.clearBoard();

        engine.setGamePhase(1);
        board.setPiece(3, 2, 1);

        int[][] board= new int [7][7];
        board[3][1]=0;

        this.board.removePiece(3,2, 2);
        Assert.assertArrayEquals(board, this.board.getBoard());
    }
    @Test
    public void goodTest2() throws FieldStatusException, PhaseException {
        board.clearBoard();

        engine.setGamePhase(1);
        board.setPiece(2, 4, 2);

        int[][] board= new int [7][7];
        board[2][4]=0;

        this.board.removePiece(2,4, 1);
        Assert.assertArrayEquals(board, this.board.getBoard());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                        Edge Tests                                                     //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void onEdgeTest() throws FieldStatusException, PhaseException {
        board.clearBoard();

        engine.setGamePhase(1);
        board.setPiece(6, 3, 2);

        int[][] board= new int [7][7];
        board[6][3]=0;

        this.board.removePiece(6,3, 1);
        Assert.assertArrayEquals(board, this.board.getBoard());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         Bad Tests                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test (expected = FieldStatusException.class)
    public void badTest_FieldEmpty() throws FieldStatusException {
        board.clearBoard();

        board.removePiece(5,4,1);
    }
    @Test (expected = FieldStatusException.class)
    public void badTest_YourStone() throws FieldStatusException, PhaseException {
        board.clearBoard();
        engine.setGamePhase(1);
        board.setPiece(4,4,2);

        board.removePiece(4,4, 2);
    }
}
