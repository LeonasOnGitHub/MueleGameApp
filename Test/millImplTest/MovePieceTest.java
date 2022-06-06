package millImplTest;

import mill.*;
import org.junit.Assert;
import org.junit.Test;

public class MovePieceTest {
//TODO translate
    Mill board = new MillImpl();
    BoardEngine engine = new BoardEngineImpl();
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         Gut Tests                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void gutTest1() throws FieldStatusException, MovementExeption, PhaseException {
        board.clearBoard();

        engine.setGamePhase(1);
        board.setPiece(3, 2, 1);

        int[][] board= new int [7][7];
        board[2][2]=1;

        engine.setGamePhase(2);
        this.board.movePiece(3,2,2,2,1);
        Assert.assertArrayEquals(board, this.board.getBoard());
    }
    @Test
    public void gutTest2() throws FieldStatusException, MovementExeption, PhaseException {
        board.clearBoard();

        engine.setGamePhase(1);
        board.setPiece(4, 4, 2);

        int[][] board= new int [7][7];
        board[4][3]=2;

        engine.setGamePhase(2);
        this.board.movePiece(4,4,4,3,2);
        Assert.assertArrayEquals(board, this.board.getBoard());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         Rand Tests                                                     //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void vomRandTest() throws FieldStatusException, MovementExeption, PhaseException {
        board.clearBoard();

        engine.setGamePhase(1);
        board.setPiece(6, 3, 2);

        int[][] board= new int [7][7];
        board[5][3]=2;

        engine.setGamePhase(2);
        this.board.movePiece(6,3,5,3,2);
        Assert.assertArrayEquals(board, this.board.getBoard());
    }
    @Test
    public void amRandTest() throws FieldStatusException, MovementExeption, PhaseException {
        board.clearBoard();

        engine.setGamePhase(1);
        board.setPiece(6, 6, 1);

        int[][] board= new int [7][7];
        board[3][6]=1;

        engine.setGamePhase(2);
        this.board.movePiece(6,6,3,6,1);
        Assert.assertArrayEquals(board, this.board.getBoard());
    }
    @Test
    public void zumRandTest() throws FieldStatusException, MovementExeption, PhaseException {
        board.clearBoard();

        engine.setGamePhase(1);
        board.setPiece(3, 5, 1);

        int[][] board= new int [7][7];
        board[3][6]=1;

        engine.setGamePhase(2);
        this.board.movePiece(3,5,3,6,1);
        Assert.assertArrayEquals(board, this.board.getBoard());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         Schlecht Tests                                                 //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test (expected = FieldStatusException.class)
    public void schlechtTestFeldLeer() throws FieldStatusException, MovementExeption {
        board.clearBoard();

        engine.setGamePhase(2);
        board.movePiece(3,4,4,4,1);
    }
    @Test (expected = FieldStatusException.class)
    public void schlechtTestFeldBesetzt() throws FieldStatusException, MovementExeption, PhaseException {
        board.clearBoard();

        engine.setGamePhase(1);
        board.setPiece(4,4,1);
        board.setPiece(3,4,2);

        engine.setGamePhase(2);
        board.movePiece(3,4,4,4,2);
    }
    @Test (expected = FieldStatusException.class)
    public void schlechtTestNichtDeinStein() throws FieldStatusException, MovementExeption, PhaseException {
        board.clearBoard();

        engine.setGamePhase(1);
        board.setPiece(3,4,2);

        engine.setGamePhase(2);
        board.movePiece(3,4,4,4,1);
    }
    @Test (expected = MovementExeption.class)
    public void schlechtTestEntfernung() throws FieldStatusException, MovementExeption, PhaseException {
        board.clearBoard();

        engine.setGamePhase(1);
        board.setPiece(3,4,1);

        engine.setGamePhase(2);
        board.movePiece(3,4,3,6,1);
    }
}

