package Mill;

public interface Mill {
    /**
     * Phase 1 set piece on the Board
     * @param xCoord
     * @param yCoord
     * @param playerMarc
     * @throws InputException
     */
    void setPiece(int xCoord, int yCoord, int playerMarc) throws FieldStatusException;

    /**
     * Phase 2 move piece on the board
     * @param xCoordS
     * @param yCoordS
     * @param xCoordD
     * @param yCoordD
     * @param playerMarc
     */
    void movePiece(int xCoordS, int yCoordS, int xCoordD, int yCoordD,int playerMarc) throws FieldStatusException, MovementExeption;

    /**
     * Phase 3 jump on the board
     * @param xCoordS
     * @param yCoordS
     * @param xCoordD
     * @param yCoordD
     * @param playerMarc
     */
    void jumpPiece(int xCoordS, int yCoordS, int xCoordD, int yCoordD,int playerMarc) throws FieldStatusException;

    /**
     * Remove piece from board
     * @param xCoord
     * @param yCoord
     * @param playerMarc
     * @throws FieldStatusException
     */
    void removePiece(int xCoord, int yCoord, int playerMarc) throws FieldStatusException;
    /**
     *
     * @return the current state of the board
     */
    int[][] getBoard();

    /**
     * Clears the board (2D array = 0)
     */
    void clearBoard();
}
