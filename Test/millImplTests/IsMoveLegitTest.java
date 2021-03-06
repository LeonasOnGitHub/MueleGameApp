package millImplTests;

import mill.*;
import org.junit.Assert;
import org.junit.Test;

public class IsMoveLegitTest {
    Mill board = new MillImpl();
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                        good Tests                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void goodTest1() throws FieldStatusException, PhaseException {
        board.clearBoard();

        boolean move = board.isMoveLegit(4,4,1, 1);
        Assert.assertEquals(true, move);
    }
    @Test
    public void goodTest2() throws FieldStatusException, PhaseException {
        board.clearBoard();

        board.setPiece(1,3,1);
        board.setPiece(1,1,1);
        board.setPiece(1,5,1);
        board.setPiece(5,3,1);

        boolean move = board.isMoveLegit(3,1,2,1);
        Assert.assertEquals(true, move);
    }
    @Test
    public void goodTest3() throws FieldStatusException, PhaseException {
        board.clearBoard();

        board.setPiece(1,3,1);
        board.setPiece(1,1,1);
        board.setPiece(1,5,1);

        boolean move = board.isMoveLegit(3,6,3,1);
        Assert.assertEquals(true, move);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         Bad Tests                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test (expected = PhaseException.class)
    public void badTestWrongGamePhase() throws FieldStatusException, PhaseException {
        board.clearBoard();

        boolean move = board.isMoveLegit(3,6,3,1);
    }
    @Test (expected = PhaseException.class)
    public void badTestNotInJumpPhase() throws FieldStatusException, PhaseException {
        board.clearBoard();

        board.setPiece(1,3,1);
        board.setPiece(1,1,1);
        board.setPiece(1,5,1);
        board.setPiece(5,3,1);

        board.isMoveLegit(3,6,3,1);
    }
    @Test (expected = FieldStatusException.class)
    public void badTestFieldNotEmpty() throws FieldStatusException, PhaseException {
        board.clearBoard();

        board.setPiece(3,6,1);
        board.setPiece(4,4,1);
        board.setPiece(6,6,1);

        board.isMoveLegit(3,6,3,1);
    }
}
