package millImplTest;

import mill.*;
import org.junit.Assert;
import org.junit.Test;

public class MovePieceTest {
//TODO translate
    Mill game = new MillImpl();
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         Gut Tests                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void gutTest1() throws FieldStatusException, MovementExeption, PhaseException {
        game.clearBoard();

        game.setPiece(3, 2, 1);
        game.setPiece(4, 4, 1);
        game.setPiece(2, 3, 1);
        game.setPiece(1, 1, 1);

        int[][] board= new int [7][7];
        board = game.defineVoid(board);
        board[2][2]=1;

        this.game.movePiece(3,2,2,2,1);
        Assert.assertArrayEquals(board, this.game.getBoard());
    }
    @Test
    public void gutTest2() throws FieldStatusException, MovementExeption, PhaseException {
        game.clearBoard();

        game.setPiece(4, 4, 2);

        int[][] board= new int [7][7];
        board = game.defineVoid(board);
        board[4][3]=2;

        this.game.movePiece(4,4,4,3,2);
        Assert.assertArrayEquals(board, this.game.getBoard());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         Rand Tests                                                     //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void vomRandTest() throws FieldStatusException, MovementExeption, PhaseException {
        game.clearBoard();

        game.setPiece(6, 3, 2);

        int[][] board= new int [7][7];
        board = game.defineVoid(board);
        board[5][3]=2;

        this.game.movePiece(6,3,5,3,2);
        Assert.assertArrayEquals(board, this.game.getBoard());
    }
    @Test
    public void amRandTest() throws FieldStatusException, MovementExeption, PhaseException {
        game.clearBoard();

        game.setPiece(6, 6, 1);

        int[][] board= new int [7][7];
        board = game.defineVoid(board);
        board[3][6]=1;

        this.game.movePiece(6,6,3,6,1);
        Assert.assertArrayEquals(board, this.game.getBoard());
    }
    @Test
    public void zumRandTest() throws FieldStatusException, MovementExeption, PhaseException {
        game.clearBoard();

        game.setPiece(3, 5, 1);

        int[][] board= new int [7][7];
        board = game.defineVoid(board);
        board[3][6]=1;

        this.game.movePiece(3,5,3,6,1);
        Assert.assertArrayEquals(board, this.game.getBoard());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         Schlecht Tests                                                 //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test (expected = FieldStatusException.class)
    public void schlechtTestFeldLeer() throws FieldStatusException, MovementExeption, PhaseException {
        game.clearBoard();

        game.movePiece(3,4,4,4,1);
    }
    @Test (expected = FieldStatusException.class)
    public void schlechtTestFeldBesetzt() throws FieldStatusException, MovementExeption, PhaseException {
        game.clearBoard();

        game.setPiece(4,4,1);
        game.setPiece(3,4,2);

        game.movePiece(3,4,4,4,2);
    }
    @Test (expected = FieldStatusException.class)
    public void schlechtTestNichtDeinStein() throws FieldStatusException, MovementExeption, PhaseException {
        game.clearBoard();

        game.setPiece(3,4,2);

        game.movePiece(3,4,4,4,1);
    }
    @Test (expected = MovementExeption.class)
    public void schlechtTestEntfernung() throws FieldStatusException, MovementExeption, PhaseException {
        game.clearBoard();

        game.setPiece(3,4,1);

        game.movePiece(3,4,3,6,1);
    }
}

