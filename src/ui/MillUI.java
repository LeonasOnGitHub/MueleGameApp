package ui;

import mill.*;

import java.io.*;
import java.util.StringTokenizer;

public class MillUI {
    private static int[][] controllArray = new int[7][7];
    private static Mill board = new MillImpl();
    private static BoardEngine engine = new BoardEngineImpl();
    private static final String PRINT = "print";
    private static final String EXIT = "exit";
    private static final String CONNECT = "connect";
    private static final String OPEN = "open";
    private static final String SET = "set";
    private static final String MOVE = "move";
    private static final String JUMP = "jump";
    private static final String REMOVE = "remove";
    private static String[] playerNames= new String[2];
    private final PrintStream outStream;
    private final String playerName;
    private final BufferedReader inBufferedReader;
    private  int yCoordS;
    private int xCoordD;
    private int xCoordS;
    private int yCoordD;



    public static void main (String[] args) {

        System.out.println("Welcome to Mill!");

        //prepare the game
        getPLayerNames();
        engine.setGamePhase(1);
        controllArray=board.defineVoid(controllArray);



        MillUI userCmd = new MillUI(playerNames[0], System.out, System.in);
        userCmd.printUsage();
        userCmd.runCommandLoop();
    }

    private static void getPLayerNames() {
        playerNames[0] = "Thomas";
        playerNames[1] = "Phillip";
    }

    public MillUI(String playerName, PrintStream os, InputStream is) {
        this.playerName = playerName;
        this.outStream = os;
        this.inBufferedReader = new BufferedReader(new InputStreamReader(is));
    }


    public void printUsage() {
        StringBuilder b = new StringBuilder();

        b.append("\n");
        b.append("\n");
        b.append("valid commands:");
        b.append("\n");
        b.append(CONNECT);
        b.append(".. connect as tcp client");
        b.append("\n");
        b.append(OPEN);
        b.append(".. open port become tcp server");
        b.append("\n");
        b.append(PRINT);
        b.append(".. print board");
        b.append("\n");
        b.append(SET);
        b.append(".. set a piece with SET (0-6) (A-G)");
        b.append("\n");
        b.append(MOVE);
        b.append(".. move a piece with MOVE (0-6) (A-G) (0-6) (A-G)");
        b.append("\n");
        b.append(JUMP);
        b.append(".. jump with a piece with JUMP (0-6) (A-G) (0-6) (A-G)");
        b.append("\n");
        b.append(EXIT);
        b.append(".. exit");

        this.outStream.println(b.toString());
    }
    public void runCommandLoop() {
        boolean again = true;

        while (again) {
            boolean rememberCommand = true;
            String cmdLineString = null;

            try {
                // read user input
                cmdLineString = inBufferedReader.readLine();

                // finish that loop if less than nothing came in
                if (cmdLineString == null) break;

                // trim whitespaces on both sides
                cmdLineString = cmdLineString.trim();

                // extract command
                int spaceIndex = cmdLineString.indexOf(' ');
                spaceIndex = spaceIndex != -1 ? spaceIndex : cmdLineString.length();

                // got command string
                String commandString = cmdLineString.substring(0, spaceIndex);

                // extract parameters string - can be empty
                String parameterString = cmdLineString.substring(spaceIndex);
                parameterString = parameterString.trim();

                //splits the parameterString to an int (yCoord) and a String (xCoord)
                StringTokenizer st = new StringTokenizer(parameterString);
                String sCoordinateS = st.nextToken();
                int iCoordinateS = Integer.parseInt(st.nextToken());
                //check and split a second time if the string has source and destination in it
                if (commandString.toLowerCase() == MOVE || commandString.toLowerCase() == JUMP){
                    String sCoordinateD = st.nextToken();
                    int iCoordinateD = Integer.parseInt(st.nextToken());
                    // merges the String to an int and cheks if the coordinates are on the board
                    this.xCoordD = mergeStringX(sCoordinateD);
                    this.yCoordD = checkY(iCoordinateD);
                }

                // merges the String to an int and cheks if the coordinates are on the board
                this.xCoordS = mergeStringX(sCoordinateS);
                checkY(iCoordinateS);

                // start command loop
                switch (commandString) {
                    case PRINT:
                        this.doPrint();
                        break;
                    case CONNECT:
                        this.doConnect(parameterString);
                        break;
                    case OPEN:
                        this.doOpen();
                        break;
                    case SET:
                        this.doSet();
                        // end of turn
                        this.doPrint();
                        this.engine.changePLayerOnTurn();
                        break;
                    case MOVE:
                        this.doMove();
                        // end of turn
                        this.doPrint();
                        this.engine.changePLayerOnTurn();
                        break;
                    case JUMP:
                        this.doJump();
                        // end of turn
                        this.doPrint();
                        this.engine.changePLayerOnTurn();
                        break;
                    case REMOVE:
                        this.doRemove();
                        // end of turn
                        this.checkIfGameOver();
                        this.doPrint();
                        this.engine.changePLayerOnTurn();
                        break;
                    case "q": // convenience
                    case EXIT:
                        again = false; this.doExit(); break; // end loop

                    default:
                        this.outStream.println("unknown command:" + cmdLineString);
                        this.printUsage();
                        rememberCommand = false;
                        break;
                }
            } catch (IOException ex) {
                this.outStream.println("cannot read from input stream - fatal, give up");
                try {
                    this.doExit();
                } catch (IOException e) {
                    // ignore
                }
            } catch (StatusException ex) {
                this.outStream.println("wrong status: " + ex.getLocalizedMessage());
            } catch (PhaseException ex) {
                this.outStream.println("phase exception: you are not in the right phase to do that" );
            } catch (RuntimeException ex) {
                this.outStream.println("runtime problems: " + ex.getLocalizedMessage());
            }catch (InputException ex) {
                this.outStream.println("wrong input " + ex.getLocalizedMessage());
            } catch (FieldStatusException e) {
                this.outStream.println("wrong field " +e.getLocalizedMessage());
            }
        }
    }

    private void doRemove() throws PhaseException {
        if (!board.canRemove){throw new PhaseException();}

    }

    private void doJump() throws PhaseException {
        if (engine.getGamePhase()!=2){throw new PhaseException();}
    }

    private void doMove() throws PhaseException {
        if (engine.getGamePhase()!=2){throw new PhaseException();}

    }

    private void doExit ()  throws IOException {
    }

    private void doSet () throws StatusException, PhaseException, InputException, FieldStatusException {
        if (engine.getGamePhase()!=1){throw new PhaseException();}
        checkStatusConnection();

        board.setPiece(this.xCoordS, this.yCoordS, engine.getPlayerMark());

        //end of turn:
        //checks if the set-phase is over
        if (engine.getTokensUntilGamePhase2() == 0){
            engine.getGamePhase();
        } else {
            engine.countDownTokensUntilGamePhase2();
        }
    }
    
    private void doOpen() {
    }

    private void doConnect(String parameterString) {
    }

    private void doPrint() {

        if (engine.getGamePhase() == 3) {
            if (engine.getPlayerMark() == engine.getWinner()) {
                System.out.println("YOU WON!");
            } else {
                System.out.println("YOU LOST!");
            }//TODO das doppelte eingabe problem lösen
        } else { // if player on turn = engine.getPlayerMark
            // your turn
        }//else {
        //please wait }

    }

    private void checkIfGameOver(){
        if (engine.getPlayerMark()==1){
            if (board.numberOfPlayerTokens(2)<3){
                engine.endOfGame(1);
            }
        } else {
            if (board.numberOfPlayerTokens(1)<3) {
                engine.endOfGame(2);
            }
        }
    }
    private int mergeStringX(String xCoord) throws InputException {
        int x=0;
        switch (xCoord){
            case "A":
                x=0;
                break;
            case "B":
                x=1;
                break;
            case "C":
                x=2;
                break;
            case "D":
                x=3;
                break;
            case "E":
                x=4;
                break;
            case "F":
                x=5;
                break;
            case "G":
                x=6;
                break;
            default:
                throw new InputException(" for the X coordinate");
        }
        return x;
    }
    private int checkY(int yCoord) throws InputException {
        if (yCoord<0 || yCoord>6){
            throw new InputException(" for the Y coordinate");
        }
        return yCoord;
    }
    private void checkVoid(int xCoord, int yCoord) throws InputException{
        for (int i = 0; i < controllArray.length; i++) {
            for (int j = 0; j < controllArray.length; j++) {
                if (controllArray[xCoord][yCoord] == -1) {
                    throw new InputException("! this position does not exist");
                }
            }
        }
    }

    private void checkStatusConnection() {

    }

}
