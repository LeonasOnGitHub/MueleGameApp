package mill;

public interface BoardEngine {
    int playerMark = 0;
    int gamePhase = 0;

    /**
     *
     */
    void changePLayerOnTurn();

    /**
     * sets the game phase
     * @param turnPhaseTo
     */
    void setGamePhase(int turnPhaseTo);

    /**
     * declares the winner
     */
    void endOfGame();

    /**
     *
     * @return the player mark of the player who is on the move
     */
    int getPlayerMark();

    /**
     *
     * @return the game phase
     */
    int getGamePhase();

}
