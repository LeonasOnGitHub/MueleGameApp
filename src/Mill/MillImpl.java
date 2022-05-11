package Mill;

public class MillImpl implements Mill {
    private static int [][] board = new int[5][5];

    @Override
    public void setPiece( int xCoord, int yCoord, int playerMarc) throws FieldStatusException {

        if (this.board[xCoord][yCoord] != 0){
           throw new FieldStatusException("There is a player marc on this field already! Please choose a different field");
       }

       this.board[xCoord][yCoord] = playerMarc;


    }


    @Override
    public void movePiece(int xCoordS, int yCoordS, int xCoordD, int yCoordD, int playerMarc) {

    }

    @Override
    public void jumpPiece(int xCoordS, int yCoordS, int xCoordD, int yCoordD, int playerMarc) {

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
