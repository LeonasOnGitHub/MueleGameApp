package mill;

public class BoardEngineImpl implements BoardEngine {

    private int playerMark = 0;
    private int gamePhase = 0;
    private int tokensUntilGamePhase2 =  18;
    private int winner = 0;

    @Override
    public void changePLayerOnTurn() {

        switch (playerMark) {
            case 0:
                this.playerMark = 1;
                break;
            case 1:
                this.playerMark = 2;
                break;
            case 2:
                this.playerMark = 1;
                break;
            }
    }


    @Override
    public void setGamePhase(int phase) {
        this.gamePhase = phase;
    }

    @Override
    public void endOfGame(int playerWins) {
        this.gamePhase=3;
        this.winner=playerWins;
    }

    @Override
    public int getPlayerMark() {
        return playerMark;
    }

    @Override
    public int getGamePhase() {
        return gamePhase;
    }

    public void countDownTokensUntilGamePhase2() {
        this.tokensUntilGamePhase2--;
    }

    public int getTokensUntilGamePhase2() {
        return tokensUntilGamePhase2;
    }

    public int getWinner() {
        return winner;
    }



}
