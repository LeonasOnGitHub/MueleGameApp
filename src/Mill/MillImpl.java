package Mill;

public class MillImpl implements Mill {
    private static int [][] board = new int[5][5];

    @Override
    public int[][] setPiece( int xCoord, int yCoord, int playerMarc) throws InputException {

        if (this.board[xCoord][yCoord] == 0){
           throw new InputException("There is a player marc on this field already! Please choose a different field");
       }

       this.board[xCoord][yCoord] = playerMarc;

        return board;
    }


    @Override
    public int[][] movePiece(int xCoordS, int yCoordS, int xCoordD, int yCoordD, int playerMarc) {
        return board;
    }

    @Override
    public int[][] jumpPiece(int xCoordS, int yCoordS, int xCoordD, int yCoordD, int playerMarc) {
        return board;
    }

    public int[][] getBoard() {
        return board;
    }

    @Override
    public void clearBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = 0;
            }
        }
    }

}
