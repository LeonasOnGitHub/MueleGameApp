package Mill;

public class MillImpl implements Mill {
    private static int [][] Board = new int[5][5];

    @Override
    public int[][] setPiece(int phase, int xCoord, int yCoord, int playerMarc) {
       switch (phase){
           case 1:
                Board[xCoord][yCoord]=playerMarc;
               break;
           case 2:
               break;
           case 3:
               break;
           case 4:
               break;
           case 5:
               break;
           default:

               break;
       }
        return Board;
    }
}
