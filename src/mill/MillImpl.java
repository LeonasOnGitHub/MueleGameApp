package mill;

public class MillImpl implements Mill {
    private static int[][] board = new int[7][7];
    private boolean canRemove;


    public MillImpl() {
        defineVoid(board);
    }

    @Override
    public void setPiece(int xCoord, int yCoord, int playerMark) throws FieldStatusException, PhaseException {

        if (isMoveLegit(xCoord, yCoord, 1, playerMark)) {
            this.board[xCoord][yCoord] = playerMark;
        }
        if(closedAMill(xCoord, yCoord, playerMark)){
            this.canRemove =true;
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
                        if (xCoordS + 1 + i == xCoordD || xCoordS - 1 - i == xCoordD) { //checks if the field (minus or plus the void fields) are side by side and breaks the loop if so
                            break;
                        } else if (xCoordS < xCoordD && xCoordS + 1 + i < board.length && board[xCoordS + 1 + i][yCoordS] == -1) { //checks if the field next to the field before is void (in plus direction) and let th loop carry on

                        } else if (xCoordS > xCoordD && xCoordS - 1 - i >= 0 && board[xCoordS - 1 - i][yCoordS] == -1) { //checks if the field next to the field before is void (in minus direction) and let th loop carry on

                        } else {
                            throw new MovementExeption(); // if  D is too far away from S throw Exception
                        }
                    }

                } else if (yCoordS != yCoordD) {
                    for (int i = 0; i <= maxSpacesBetweenFields; i++) {
                        if (yCoordS + 1 + i == yCoordD || yCoordS - 1 - i == yCoordD) {
                            break;
                        } else if (yCoordS < yCoordD && yCoordS + 1 + i < board.length && board[xCoordS][yCoordS + 1 + i] == -1) {

                        } else if (yCoordS > yCoordD && yCoordS - 1 - i >= 0 && board[xCoordS][yCoordS - 1 - i] == -1) {

                        } else {
                            throw new MovementExeption();
                        }
                    }
                }
                this.board[xCoordS][yCoordS] = 0;
                this.board[xCoordD][yCoordD] = playerMark;
            }
        } else {
            throw new FieldStatusException("The stone you are trying to move is not yours!");
        }
        if(closedAMill(xCoordD, yCoordD, playerMark)){
            canRemove =true;
        }
    }

    @Override
    public void jumpPiece(int xCoordS, int yCoordS, int xCoordD, int yCoordD, int playerMark) throws FieldStatusException, PhaseException {
        if (isYourStone(xCoordS, yCoordS, playerMark)) {
            if (isMoveLegit(xCoordD, yCoordD, 3, playerMark)) {

                this.board[xCoordS][yCoordS] = 0;
                this.board[xCoordD][yCoordD] = playerMark;
            }
        } else {
            throw new FieldStatusException("The stone you are trying to move is not yours!");
        }
        if(closedAMill(xCoordD, yCoordD, playerMark)){
            canRemove =true;
        }
    }

    @Override
    public void removePiece(int xCoord, int yCoord, int playerMark) throws FieldStatusException {
        if (isYourStone(xCoord, yCoord, playerMark)) {
            throw new FieldStatusException("this is your stone you are trying to remove!");
        } else if (this.board[xCoord][yCoord] == 0) {
            throw new FieldStatusException("this position is empty");
        }
        this.board[xCoord][yCoord] = 0;


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
            System.out.println(this.board[xCoord][yCoord]);
            throw new FieldStatusException("Position already occupied! Please choose a different one");
        }

        return result;
    }

    private int checkPlayerPhase(int palyerMark, int controllInt) {
        int result = 1;

        if (controllInt > 1) {
            int numberOfPlayerTokens = numberOfPlayerTokens(palyerMark);
            if (numberOfPlayerTokens > 3) {
                result = 2;
            } else if (numberOfPlayerTokens == 3) {
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
            result = false;
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
                if (i == 0 && j == 1 || i == 1 && j == 0 || i == 3 && j == 3 || i == 2 && j == 0 || i == 0 && j == 2 || i == 0 && j == 4 || i == 4 && j == 0 || i == 0 && j == 5 || i == 5 && j == 0 || i == 1 && j == 2 || i == 2 && j == 1 || i == 1 && j == 4 || i == 4 && j == 1 || i == 1 && j == 6 || i == 6 && j == 1 || i == 2 && j == 5 || i == 5 && j == 2 || i == 2 && j == 6 || i == 6 && j == 2 || i == 5 && j == 4 || i == 4 && j == 5 || i == 4 && j == 6 || i == 6 && j == 4 || i == 5 && j == 6 || i == 6 && j == 5) {
                    array[i][j] = -1;
                }
            }
        }
        return array;
    }

    @Override
    public boolean closedAMill(int xCoord, int yCoord, int palyerMark) {

        boolean closedAMill = false;
        //init all possible mill arrays
        int[][] mills36 = new int[3][2];
        int[][] mills35 = new int[3][2];
        int[][] mills34 = new int[3][2];
        int[][] mills32 = new int[3][2];
        int[][] mills31 = new int[3][2];
        int[][] mills30 = new int[3][2];
        int[][] mills03 = new int[3][2];
        int[][] mills13 = new int[3][2];
        int[][] mills23 = new int[3][2];
        int[][] mills43 = new int[3][2];
        int[][] mills53 = new int[3][2];
        int[][] mills63 = new int[3][2];
        int[][] mills331 = new int[3][2];
        int[][] mills332 = new int[3][2];
        int[][] mills133 = new int[3][2];
        int[][] mills233 = new int[3][2];

        //fill mill arrays with the mill cords
        mills36[0][0] = 0;
        mills36[0][1] = 6;
        mills36[1][0] = 3;
        mills36[1][1] = 6;
        mills36[2][0] = 6;
        mills36[2][1] = 6;
        mills35[0][0] = 1;
        mills35[0][1] = 5;
        mills35[1][0] = 3;
        mills35[1][1] = 5;
        mills35[2][0] = 5;
        mills35[2][1] = 5;
        mills34[0][0] = 2;
        mills34[0][1] = 4;
        mills34[1][0] = 3;
        mills34[1][1] = 4;
        mills34[2][0] = 4;
        mills34[2][1] = 4;
        mills32[0][0] = 2;
        mills32[0][1] = 2;
        mills32[1][0] = 3;
        mills32[1][1] = 2;
        mills32[2][0] = 4;
        mills32[2][1] = 2;
        mills31[0][0] = 1;
        mills31[0][1] = 1;
        mills31[1][0] = 3;
        mills31[1][1] = 1;
        mills31[2][0] = 5;
        mills31[2][1] = 1;
        mills30[0][0] = 0;
        mills30[0][1] = 0;
        mills30[1][0] = 3;
        mills30[1][1] = 0;
        mills30[2][0] = 6;
        mills30[2][1] = 0;
        mills03[0][0] = 0;
        mills03[0][1] = 0;
        mills03[1][0] = 0;
        mills03[1][1] = 3;
        mills03[2][0] = 0;
        mills03[2][1] = 6;
        mills13[0][0] = 1;
        mills13[0][1] = 1;
        mills13[1][0] = 1;
        mills13[1][1] = 3;
        mills13[2][0] = 1;
        mills13[2][1] = 5;
        mills23[0][0] = 2;
        mills23[0][1] = 2;
        mills23[1][0] = 2;
        mills23[1][1] = 3;
        mills23[2][0] = 2;
        mills23[2][1] = 4;
        mills43[0][0] = 4;
        mills43[0][1] = 2;
        mills43[1][0] = 4;
        mills43[1][1] = 3;
        mills43[2][0] = 4;
        mills43[2][1] = 4;
        mills53[0][0] = 5;
        mills53[0][1] = 1;
        mills53[1][0] = 5;
        mills53[1][1] = 3;
        mills53[2][0] = 5;
        mills53[2][1] = 5;
        mills63[0][0] = 6;
        mills63[0][1] = 0;
        mills63[1][0] = 6;
        mills63[1][1] = 3;
        mills63[2][0] = 6;
        mills63[2][1] = 6;
        mills133[0][0] = 3;
        mills133[0][1] = 0;
        mills133[1][0] = 3;
        mills133[1][1] = 1;
        mills133[2][0] = 3;
        mills133[2][1] = 2;
        mills233[0][0] = 3;
        mills233[0][1] = 4;
        mills233[1][0] = 3;
        mills233[1][1] = 5;
        mills233[2][0] = 3;
        mills233[2][1] = 6;
        mills331[0][0] = 0;
        mills331[0][1] = 3;
        mills331[1][0] = 1;
        mills331[1][1] = 3;
        mills331[2][0] = 2;
        mills331[2][1] = 3;
        mills332[0][0] = 4;
        mills332[0][1] = 3;
        mills332[1][0] = 5;
        mills332[1][1] = 3;
        mills332[2][0] = 6;
        mills332[2][1] = 3;

        int[][][] arrayOfMills = new int[][][]{mills03, mills13, mills23, mills43, mills53, mills63, mills30, mills31, mills32, mills34, mills35, mills36, mills331, mills332, mills133, mills233};

        //goes throw all possible mills and checks in which one the moved token is
        for (int[][] arrayOfMill : arrayOfMills) {
            for (int i = 0; i < 3; i++) {
                //controls if the moved stone created a mill at the specific possible Mill j
                if (xCoord == arrayOfMill[i][0] && yCoord == arrayOfMill[i][1]) {
                    if (board[arrayOfMill[0][0]][arrayOfMill[0][1]] == palyerMark && board[arrayOfMill[1][0]][arrayOfMill[1][1]] == palyerMark && board[arrayOfMill[2][0]][arrayOfMill[2][1]] == palyerMark) {
                        closedAMill = true;
                        break;
                    }
                }
            }
        }

        return closedAMill;
    }

    @Override
    public boolean getCanRemove() {
        return this.canRemove;
    }

    @Override
    public void setCanremove() {
        this.canRemove = false;
    }

}


