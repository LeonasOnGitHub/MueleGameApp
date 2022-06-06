package mill;

public class MillImpl implements Mill {
    private static int [][] board = new int[7][7];


    @Override
    public void setPiece( int xCoord, int yCoord, int playerMark) throws FieldStatusException, PhaseException {

        if (isMoveLegit(xCoord,yCoord,1,playerMark)) {
            this.board[xCoord][yCoord] = playerMark;
        }
    }
    @Override
    public void movePiece(int xCoordS, int yCoordS, int xCoordD, int yCoordD, int playerMark) throws FieldStatusException, MovementExeption {

    }

    @Override
    public void jumpPiece(int xCoordS, int yCoordS, int xCoordD, int yCoordD, int playerMark) throws FieldStatusException{

    }

    @Override
    public void removePiece(int xCoord, int yCoord, int playerMarc) throws FieldStatusException {

    }

    public int[][] getBoard() {
        return board;
    }

    @Override
    public int numberOfPlayerTokens(int playerMark) {
        int tokenCount = 0;
        for (int i = 0; i< board.length; i++){
            for (int j = 0; i< board.length; i++){
                if (this.board[i][j] == playerMark){
                    tokenCount++;
                }
            }
        }
        return tokenCount;
    }

    @Override
    public boolean isMoveLegit(int xCoord, int yCoord, int controllInt, int playerMark) throws PhaseException, FieldStatusException {
        boolean result = true;

        if(BoardEngineImpl.gamePhase != controllInt){
            throw new PhaseException();
        } else if (checkPlayerPhase(playerMark)!=controllInt){
            throw new PhaseException();
        } else if (this.board[xCoord][yCoord] != 0){
            throw new FieldStatusException("Position already occupied! Please choose a different one");
        }

        return result;
    }

    private int checkPlayerPhase(int palyerMark){
        int result=2;
        if (numberOfPlayerTokens(palyerMark)==3){
            result=3;
        }else if (numberOfPlayerTokens(palyerMark)<3){
            result=4;
        }
        return result;
    }
    private boolean isYourStone(int xCoord, int yCoord, int playerMark) throws FieldStatusException{
        boolean result = true;

        if(playerMark != this.board[xCoord][yCoord]){
            throw new FieldStatusException("The stone you are trying to move is not yours! ");
        }

        return result;
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
