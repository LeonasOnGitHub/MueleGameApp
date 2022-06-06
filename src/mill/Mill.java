package mill;

public interface Mill {
    enum FIELD_STATUS{
        VOID, //0
        NOT_SET, //-1
        PLAYER_A, //1
        PLAYER_B,//2
    }
    /**
     * Phase 1 set piece on the Board
     * @param xCoord
     * @param yCoord
     * @param playerMark
     * @throws FieldStatusException
     */
    void setPiece(int xCoord, int yCoord, int playerMark) throws FieldStatusException, PhaseException;

    /**
     * Phase 2 move piece on the board
     * @param xCoordS
     * @param yCoordS
     * @param xCoordD
     * @param yCoordD
     * @param playerMark
     * @throws FieldStatusException
     * @throws MovementExeption
     */
    void movePiece(int xCoordS, int yCoordS, int xCoordD, int yCoordD,int playerMark) throws FieldStatusException, MovementExeption, PhaseException;

    /**
     * Phase 3 jump on the board
     * @param xCoordS
     * @param yCoordS
     * @param xCoordD
     * @param yCoordD
     * @param playerMark
     * @throws FieldStatusException
     */
    void jumpPiece(int xCoordS, int yCoordS, int xCoordD, int yCoordD,int playerMark) throws FieldStatusException;

    /**
     * Remove piece from board
     * @param xCoord
     * @param yCoord
     * @param playerMark
     * @throws FieldStatusException
     */
    void removePiece(int xCoord, int yCoord, int playerMark) throws FieldStatusException;

    /**
     *
     * @return the current state of the board
     */
    int[][] getBoard();

    /**
     * Counts the number of player marks
     * @param playerMark
     * @return the current number of tokens from the playing player on the board
     */
    int numberOfPlayerTokens(int playerMark);

    /**
     * Checks if a player is in the right game phase to make this move and if the move is compliant with the rules
     * @param xCoord
     * @param yCoord
     * @param controllInt
     * @param playerMark
     * @return whether the move is legit or not
     */
    boolean isMoveLegit(int xCoord, int yCoord, int controllInt, int playerMark) throws PhaseException, FieldStatusException;

    /**
     * Clears the board (2D array = 0)
     */
    void clearBoard();

    /**
     * fills board with -1 on nonselectable  fields
     * @param array
     * @return
     */
    int[][] defineVoid(int array[][]);

}
