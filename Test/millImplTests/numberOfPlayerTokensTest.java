package millImplTests;

import mill.*;
import org.junit.Assert;
import org.junit.Test;

public class numberOfPlayerTokensTest {
    Mill board = new MillImpl();
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                        good Tests                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void goodTest1() throws FieldStatusException, PhaseException {
        board.clearBoard();
        int numberOfTokens = 3;

        board.setPiece(5,5,1);
        board.setPiece(4,2,1);
        board.setPiece(3,2,1);

        int numberOfPlayerTokensB = board.numberOfPlayerTokens(1);
        Assert.assertEquals(numberOfTokens, numberOfPlayerTokensB);

    }
    @Test
    public void goodTest2() throws FieldStatusException, PhaseException {
        board.clearBoard();
        int numberOfTokens = 3;

        board.setPiece(3,1,2);
        board.setPiece(1,5,2);
        board.setPiece(6,3,2);

        int numberOfPlayerTokensB = board.numberOfPlayerTokens(2);
        Assert.assertEquals(numberOfTokens, numberOfPlayerTokensB);

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                        Edge Tests                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void edgeTest1() throws FieldStatusException, PhaseException {
        board.clearBoard();
        int numberOfTokens = 4;


        board.setPiece(0,0,2);
        board.setPiece(6,6,2);
        board.setPiece(6,0,2);
        board.setPiece(0,6,2);

        int numberOfPlayerTokensB = board.numberOfPlayerTokens(2);
        Assert.assertEquals(numberOfTokens, numberOfPlayerTokensB);

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         Bad Tests                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
