package mill;

public class MillImpl implements Mill {
    private static int[][] board = new int[7][7];

    public MillImpl() {
        defineVoid(board);
    }

    @Override
    public void setPiece(int xCoord, int yCoord, int playerMark) throws FieldStatusException, PhaseException {

        if (isMoveLegit(xCoord, yCoord, 1, playerMark)) {
            this.board[xCoord][yCoord] = playerMark;
        }
    }

    @Override
    public void movePiece(int xCoordS, int yCoordS, int xCoordD, int yCoordD, int playerMark) throws FieldStatusException, MovementExeption, PhaseException {
        int maxSpacesBetweenFields = 2;
        if (isYourStone(xCoordS, yCoordS, playerMark)) {
            if (isMoveLegit(xCoordD, yCoordD, 2, playerMark)) {

                // checks if the fields are side by side
                if (xCoordS != xCoordD) {   //checks if movement is on the axis
                    for (int i = 0; i <= maxSpacesBetweenFields; i++) { //counts actual void fields between S and D
                        if (xCoordS+1+i == xCoordD || xCoordS-1-i == xCoordD){ //checks if the field (minus or plus the void fields) are side by side and breaks the loop if so
                            break;
                        } else if (xCoordS < xCoordD && xCoordS+1+i< board.length && board[xCoordS+1+i][yCoordS] == -1) { //checks if the field next to the field before is void (in plus direction) and let th loop carry on

                        } else if (xCoordS > xCoordD && xCoordS-1-i>=0 && board[xCoordS-1-i][yCoordS] == -1) { //checks if the field next to the field before is void (in minus direction) and let th loop carry on

                        }else {
                            throw new MovementExeption(); // if the D is too far away from S throw Exception
                        }
                    }

                }else if (yCoordS != yCoordD){
                    for (int i = 0; i <= maxSpacesBetweenFields; i++) {
                        if (yCoordS+1+i == yCoordD || yCoordS-1-i == yCoordD){
                            break;
                        } else if (yCoordS < yCoordD && yCoordS+1+i< board.length && board[xCoordS][yCoordS+1+i] == -1) {

                        } else if (yCoordS > yCoordD && yCoordS-1-i>=0 && board[xCoordS][yCoordS-1-i] == -1 ) {

                        } else {
                            throw new MovementExeption();
                        }
                    }
                }
                this.board[xCoordS][yCoordS] = 0;
                this.board[xCoordD][yCoordD] = playerMark;
            }
        }
    }

    @Override
    public void jumpPiece(int xCoordS, int yCoordS, int xCoordD, int yCoordD, int playerMark) throws FieldStatusException {

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
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (this.board[i][j] == playerMark) {
                    tokenCount++;
                }
            }
        }
        return tokenCount;
    }

    @Override
    public boolean isMoveLegit(int xCoord, int yCoord, int controllInt, int playerMark) throws PhaseException, FieldStatusException {
        boolean result = true;


        if (checkPlayerPhase(playerMark, controllInt) != controllInt) {
            throw new PhaseException();
        } else if (this.board[xCoord][yCoord] != 0) {
            throw new FieldStatusException("Position already occupied! Please choose a different one");
        }

        return result;
    }

    private int checkPlayerPhase(int palyerMark, int controllInt) {
        int result = 1;
        int numberOfPlayerTokens = numberOfPlayerTokens(palyerMark);

        if (controllInt > 1) {
            if (numberOfPlayerTokens > 3) {
                result = 2;
            } else if (numberOfPlayerTokens(palyerMark) == 3) {
                result = 3;
            } else {
                result = 4;
            }
        }
        return result;
    }

    private boolean isYourStone(int xCoord, int yCoord, int playerMark) throws FieldStatusException {
        boolean result = true;

        if (playerMark != this.board[xCoord][yCoord]) {
            throw new FieldStatusException("The stone you are trying to move is not yours!");
        }

        return result;
    }

    @Override
    public void clearBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != -1) {
                    board[i][j] = 0;
                }
            }
        }
    }

    @Override
    public int[][] defineVoid(int array[][]) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (i == 0 && j == 1 || i == 1 && j == 0 || i == 3 && j == 3 || i == 2 && j == 0 || i == 0 && j == 2 || i == 0 && j == 3 || i == 3 && j == 0 || i == 0 && j == 4 || i == 4 && j == 0 || i == 0 && j == 5 || i == 5 && j == 0 || i == 1 && j == 2 || i == 2 && j == 1 || i == 1 && j == 4 || i == 4 && j == 1 || i == 1 && j == 6 || i == 6 && j == 1 || i == 2 && j == 5 || i == 5 && j == 2 || i == 2 && j == 6 || i == 6 && j == 2 || i == 5 && j == 4 || i == 4 && j == 5 || i == 4 && j == 6 || i == 6 && j == 4 || i == 5 && j == 6 || i == 6 && j == 5) {
                    array[i][j] = -1;
                }
            }
        }
        return array;
    }
}


