package setPieceTest;

import Mill.MillImpl;
import Mill.Mill;
import org.junit.Assert;
import org.junit.Test;

public class SetPieceTest {

    @Test
    public void phase1GutTest1(){
        int [][] board = new int[5][5];
        board[2][4]=1;

        Mill m1 = new MillImpl();
        int [][] boardTest =m1.setPiece(1, 2, 4, 1);

        Assert.assertArrayEquals(board, boardTest);


    }
}
