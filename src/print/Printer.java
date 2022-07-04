package print;

public interface Printer {
    /**
     * prints the current state of the board
     * @param board
     */
    void printBoard(int [][] board);

    /**
     * prints the game rules and how to input your move
     */
    void printRules();

    /**
     * prints both winner and loser to both pLayers
     * @param winner
     */
    void printEndOfGame(int winner);
}
