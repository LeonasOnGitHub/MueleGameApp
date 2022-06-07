package mill;

public interface BoardEngine {

    /**
     * switches the player mark back and forth
     */
    void changePLayerOnTurn();

    /**
     * sets the game phase
     * @param turnPhaseTo
     */
    void setGamePhase(int turnPhaseTo);

    /**
     * declares the winner
     * @param playerWins
     */
    void endOfGame(int playerWins);

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

    /**
     *
     * @return the number of tokens until the set phase is over
     */
    int getTokensUntilGamePhase2();

    /**
     * counts the number of tokens until the set phase is over down
     */
    void countDownTokensUntilGamePhase2();

    /**
     *
     * @return the winner of the game
     */
    public int getWinner();
}
