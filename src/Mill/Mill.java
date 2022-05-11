package Mill;

public interface Mill {

    int[][] setPiece(int xCoord, int yCoord, int playerMarc) throws InputException;

    int[][] movePiece(int xCoordS, int yCoordS, int xCoordD, int yCoordD,int playerMarc);

    int[][] jumpPiece(int xCoordS, int yCoordS, int xCoordD, int yCoordD,int playerMarc);

    int[][] getBoard();

    void clearBoard();
}
